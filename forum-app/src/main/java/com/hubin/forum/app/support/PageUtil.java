package com.hubin.forum.app.support;

import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;

import java.util.List;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
public class PageUtil {

    public static <T> PageRequest<T> buildPageRequest(PageRequestModel pageRequestModel, T filter) {
        return PageRequest.build(pageRequestModel.getPageNo(), pageRequestModel.getPageSize(), filter);
    }

    public static PageRequest buildPageRequest(PageRequestModel pageRequestModel) {
        return PageRequest.build(pageRequestModel.getPageNo(), pageRequestModel.getPageSize());
    }

    public static <T> PageResponseModel<T> buildPageResponseModel(PageResult pageResult, List<T> list) {
        PageResponseModel<T> pageResponseModel = new PageResponseModel<>();
        pageResponseModel.setSize(pageResult.getSize());
        pageResponseModel.setTotal(pageResult.getTotal());
        pageResponseModel.setList(list);

        return pageResponseModel;
    }


}
