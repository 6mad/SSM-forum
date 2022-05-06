package com.hubin.forum.facade.validator;

import com.hubin.forum.api.request.tag.TagCreateRequest;
import com.hubin.forum.common.support.CheckUtil;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
public class TagValidator {

    public static void create(TagCreateRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getName(), "name");
        CheckUtil.checkParamToast(request.getGroupName(), "groupName");
        CheckUtil.checkParamToast(request.getDescription(), "description");
    }

}
