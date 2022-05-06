package com.hubin.forum.api.service;

import com.hubin.forum.api.request.article.*;
import com.hubin.forum.api.response.article.ArticleInfoResponse;
import com.hubin.forum.api.response.article.ArticleQueryTypesResponse;
import com.hubin.forum.api.response.article.ArticleUserPageResponse;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
public interface ArticleApiService {

    ResultModel<List<ArticleQueryTypesResponse>> queryAllTypes();

    ResultModel<List<ArticleQueryTypesResponse>> queryAdminTypes();

    ResultModel<PageResponseModel<ArticleUserPageResponse>> adminPage(PageRequestModel<ArticleAdminPageRequest> pageRequestModel);

    ResultModel<List<ArticleQueryTypesResponse>> queryEditArticleTypes();

    ResultModel addType(ArticleAddTypeRequest request);

    ResultModel<Long> saveArticle(ArticleSaveArticleRequest request);

    ResultModel<PageResponseModel<ArticleUserPageResponse>> userPage(PageRequestModel<ArticleUserPageRequest> pageRequestModel);

    ResultModel<PageResponseModel<ArticleUserPageResponse>> authorPage(PageRequestModel<ArticleAuthorPageRequest> pageRequestModel);

    ResultModel<ArticleInfoResponse> info(Long id);

    ResultModel adminTop(AdminBooleanRequest booleanRequest);

    ResultModel adminOfficial(AdminBooleanRequest booleanRequest);

    ResultModel adminMarrow(AdminBooleanRequest booleanRequest);

    ResultModel<PageResponseModel<ArticleQueryTypesResponse>> typePage(PageRequestModel<ArticleAdminTypePageRequest> pageRequestModel);

    ResultModel typeAuditState(AdminBooleanRequest booleanRequest);
}
