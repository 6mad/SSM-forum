package com.hubin.forum.api.service;

import com.hubin.forum.api.response.comment.CommentPageResponse;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.comment.CommentCreateRequest;

/**
 * @author Hubin
 * @create 2021/11/6
 * @desc
 **/
public interface CommentApiService {

    ResultModel create(CommentCreateRequest request);

    ResultModel<PageResponseModel<CommentPageResponse>> page(PageRequestModel<Long> pageRequest);

}
