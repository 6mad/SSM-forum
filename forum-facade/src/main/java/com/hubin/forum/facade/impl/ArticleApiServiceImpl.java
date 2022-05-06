package com.hubin.forum.facade.impl;

import com.hubin.forum.api.request.article.*;
import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.ArticleValidator;
import com.hubin.forum.facade.validator.PageRequestModelValidator;
import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.response.article.ArticleInfoResponse;
import com.hubin.forum.api.response.article.ArticleQueryTypesResponse;
import com.hubin.forum.api.response.article.ArticleUserPageResponse;
import com.hubin.forum.api.service.ArticleApiService;
import com.hubin.forum.app.manager.ArticleManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
@Service
public class ArticleApiServiceImpl implements ArticleApiService {

    @Resource
    private ArticleManager articleManager;

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryAllTypes() {
        return ResultModelUtil.success(articleManager.queryAllTypes());
    }

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryAdminTypes() {
        return ResultModelUtil.success(articleManager.queryAdminTypes());
    }

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryEditArticleTypes() {
        return ResultModelUtil.success(articleManager.queryEditArticleTypes());
    }

    @Override
    public ResultModel addType(ArticleAddTypeRequest request) {
        ArticleValidator.addType(request);

        articleManager.addType(request);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<Long> saveArticle(ArticleSaveArticleRequest request) {
        ArticleValidator.saveArticle(request);

        return ResultModelUtil.success(articleManager.saveArticle(request));
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> userPage(PageRequestModel<ArticleUserPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.userPage(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> authorPage(PageRequestModel<ArticleAuthorPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.authorPage(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> adminPage(PageRequestModel<ArticleAdminPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.adminPage(pageRequestModel));
    }

    @Override
    public ResultModel<ArticleInfoResponse> info(Long id) {

        return ResultModelUtil.success(articleManager.info(id));
    }

    @Override
    public ResultModel adminTop(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.adminTop(booleanRequest);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel adminOfficial(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.adminOfficial(booleanRequest);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel adminMarrow(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.adminMarrow(booleanRequest);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<PageResponseModel<ArticleQueryTypesResponse>> typePage(PageRequestModel<ArticleAdminTypePageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.typePage(pageRequestModel));
    }

    @Override
    public ResultModel typeAuditState(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.typeAuditState(booleanRequest);

        return ResultModelUtil.success();
    }
}
