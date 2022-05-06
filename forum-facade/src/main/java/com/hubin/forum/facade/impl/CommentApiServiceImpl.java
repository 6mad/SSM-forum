package com.hubin.forum.facade.impl;

import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.comment.CommentCreateRequest;
import com.hubin.forum.api.response.comment.CommentPageResponse;
import com.hubin.forum.api.service.CommentApiService;
import com.hubin.forum.app.manager.CommentManager;
import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.CommentValidator;
import com.hubin.forum.facade.validator.PageRequestModelValidator;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/11/6
 * @desc
 **/
@Service
public class CommentApiServiceImpl implements CommentApiService {

    @Resource
    private CommentManager commentManager;

    @Override
    public ResultModel create(CommentCreateRequest request) {
        CommentValidator.create(request);

        commentManager.create(request);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<PageResponseModel<CommentPageResponse>> page(PageRequestModel<Long> pageRequest) {
        PageRequestModelValidator.validator(pageRequest);

        return ResultModelUtil.success(commentManager.page(pageRequest));
    }
}
