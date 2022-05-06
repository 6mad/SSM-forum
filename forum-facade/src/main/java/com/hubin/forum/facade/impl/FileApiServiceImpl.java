package com.hubin.forum.facade.impl;

import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.file.FileUploadImgRequest;
import com.hubin.forum.api.service.FileApiService;
import com.hubin.forum.app.manager.FileManager;
import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.FileValidator;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/11/23
 * @desc
 **/
@Service
public class FileApiServiceImpl implements FileApiService {

    @Resource
    private FileManager fileManager;

    @Override
    public ResultModel<String> uploadImg(FileUploadImgRequest request) {
        FileValidator.uploadImg(request);

        return ResultModelUtil.success(fileManager.uploadImg(request));
    }
}
