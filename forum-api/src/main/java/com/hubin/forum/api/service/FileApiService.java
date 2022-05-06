package com.hubin.forum.api.service;

import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.file.FileUploadImgRequest;

/**
 * @author Hubin
 * @create 2021/11/23
 * @desc
 **/
public interface FileApiService {

    ResultModel<String> uploadImg(FileUploadImgRequest request);

}
