package com.hubin.forum.facade.validator;

import com.hubin.forum.api.request.comment.CommentCreateRequest;
import com.hubin.forum.common.support.CheckUtil;

/**
 * @author Hubin
 * @create 2021/11/6
 * @desc
 **/
public class CommentValidator {

    public static void create(CommentCreateRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getPostsId(), "postsId");
        CheckUtil.checkParamToast(request.getContent(), "content");
    }
}
