package com.hubin.forum.app.manager;

import com.hubin.forum.app.support.IsLogin;
import org.springframework.stereotype.Component;
import com.hubin.forum.api.request.file.FileUploadImgRequest;
import com.hubin.forum.domain.service.FileService;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/11/23
 * @desc
 **/
@Component
public class FileManager {

    @Resource
    private FileService fileService;

    @IsLogin
    public String uploadImg(FileUploadImgRequest request) {
        return fileService.uploadImg(request.getBase64(), request.getFileName());
    }
}
