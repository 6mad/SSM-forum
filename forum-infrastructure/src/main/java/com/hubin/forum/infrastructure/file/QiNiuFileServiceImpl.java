package com.hubin.forum.infrastructure.file;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.support.CheckUtil;
import com.hubin.forum.common.support.LogUtil;
import com.hubin.forum.domain.service.FileService;

/**
 * @author Hubin
 * @create 2021/11/23
 * @desc
 **/
@Slf4j
@Data
@ConfigurationProperties(prefix = "custom-config.upload-file.qiniu")
@Component
public class QiNiuFileServiceImpl implements FileService {

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private String accessDomain;

    @Override
    public String uploadImg(byte[] base64, String key) {
        try {
            // 需要注意  Region.huanan() 表示的是华南地区， 空间开了那个地区就填那个地区。
            Configuration cfg = new Configuration(Region.huanan());

            UploadManager uploadManager = new UploadManager(cfg);

            String token = Auth.create(accessKey, secretKey).uploadToken(bucketName, key);
            CheckUtil.isEmpty(token, ErrorCodeEn.FILE_UPLOAD_FAIL);

            Response res = uploadManager.put(base64, key, token);
            CheckUtil.isFalse(res.isOK(), ErrorCodeEn.FILE_UPLOAD_FAIL);

            return accessDomain + res.jsonToObject(Ret.class).key;
        } catch (QiniuException e) {
            LogUtil.info(log, e, "上传文件异常，e.msg={}", e.getMessage());
            CheckUtil.isTrue(true, ErrorCodeEn.FILE_UPLOAD_FAIL);
        }
        return null;
    }

    @Data
    class Ret {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }

}
