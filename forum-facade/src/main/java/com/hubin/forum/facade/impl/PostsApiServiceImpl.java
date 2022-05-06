package com.hubin.forum.facade.impl;

import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.service.PostsApiService;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.app.manager.PostsManager;
import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.ArticleValidator;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/11/25
 * @desc
 **/
@Service
public class PostsApiServiceImpl implements PostsApiService {

    @Resource
    private PostsManager postsManager;

    @Override
    public ResultModel delete(Long id) {
        postsManager.delete(id);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<PageResponseModel<PostsVO>> pagePostsFood(PageRequestModel pageRequestModel) {
        return ResultModelUtil.success(postsManager.pagePostsFood(pageRequestModel));
    }

    @Override
    public ResultModel auditState(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        postsManager.auditState(booleanRequest);

        return ResultModelUtil.success();
    }

}
