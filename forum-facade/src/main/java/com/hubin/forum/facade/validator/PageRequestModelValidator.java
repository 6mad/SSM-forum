package com.hubin.forum.facade.validator;

import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.common.support.CheckUtil;

/**
 * @author Hubin
 * @create 22/2/1220/9/9
 * @desc
 **/
public class PageRequestModelValidator {

    public static void validator(PageRequestModel pageRequestModel) {
        CheckUtil.checkParamToast(pageRequestModel, "pageRequestModel");
        CheckUtil.checkParamToast(pageRequestModel.getPageNo(), "pageNo");
        CheckUtil.checkParamToast(pageRequestModel.getPageSize(), "pageSize");
        CheckUtil.checkParamToast(pageRequestModel.getFilter(), "filter");
    }

}
