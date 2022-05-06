package com.hubin.forum.facade.support;

import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.common.enums.ErrorCodeEn;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
public class ResultModelUtil {

    public static ResultModel success() {
        return new ResultModel();
    }

    public static <T> ResultModel<T> success(T data) {
        ResultModel<T> resultModel = new ResultModel<T>();
        resultModel.setData(data);

        return resultModel;
    }

    public static ResultModel fail(ErrorCodeEn errorCode) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode.getCode());
        resultModel.setMessage(errorCode.getMessage());
        resultModel.setSuccess(Boolean.FALSE);

        return resultModel;
    }

    public static ResultModel fail(Integer code, String message) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(code);
        resultModel.setMessage(message);
        resultModel.setSuccess(Boolean.FALSE);

        return resultModel;
    }

}
