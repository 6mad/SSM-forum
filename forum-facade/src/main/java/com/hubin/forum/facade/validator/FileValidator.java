package com.hubin.forum.facade.validator;

import com.hubin.forum.api.request.file.FileUploadImgRequest;
import com.hubin.forum.common.support.CheckUtil;

/**
 * @author Hubin
 * @create 2021/11/23
 * @desc
 **/
public class FileValidator {

    public static void uploadImg(FileUploadImgRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getBase64(), "base64");
        CheckUtil.checkParamToast(request.getFileName(), "fileName");
    }
}
