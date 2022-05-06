package com.hubin.forum.portal.controller.admin;

import com.hubin.forum.portal.support.WebUtil;
import org.springframework.web.bind.annotation.*;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.article.ArticleAddTypeRequest;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.article.ArticleAdminPageRequest;
import com.hubin.forum.api.request.article.ArticleAdminTypePageRequest;
import com.hubin.forum.api.response.article.ArticleQueryTypesResponse;
import com.hubin.forum.api.response.article.ArticleUserPageResponse;
import com.hubin.forum.api.service.ArticleApiService;
import com.hubin.forum.api.service.PostsApiService;
import com.hubin.forum.common.constant.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
@RestController
@RequestMapping("/admin-rest/article")
public class AdminRestArticleController {

    @Resource
    private ArticleApiService articleApiService;

    @Resource
    private PostsApiService postsApiService;

    @PostMapping("/all-type")
    public ResultModel<List<ArticleQueryTypesResponse>> allAdminTypes(HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.queryAdminTypes();
    }

    @PostMapping("/page")
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> page(@RequestBody PageRequestModel<ArticleAdminPageRequest> pageRequestModel
            , HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.adminPage(pageRequestModel);
    }

    @PostMapping("/top")
    public ResultModel top(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.adminTop(booleanRequest);
    }

    @PostMapping("/official")
    public ResultModel official(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.adminOfficial(booleanRequest);
    }

    @PostMapping("/marrow")
    public ResultModel marrow(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.adminMarrow(booleanRequest);
    }

    @PostMapping("/audit-state")
    public ResultModel auditState(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return postsApiService.auditState(booleanRequest);
    }

    @PostMapping("/type-page")
    public ResultModel<PageResponseModel<ArticleQueryTypesResponse>> typePage(@RequestBody PageRequestModel<ArticleAdminTypePageRequest> pageRequestModel, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.typePage(pageRequestModel);
    }

    @PostMapping("/type-audit-state")
    public ResultModel typeAuditState(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.typeAuditState(booleanRequest);
    }

    @PostMapping("/type-add")
    public ResultModel addType(@RequestBody ArticleAddTypeRequest articleAddTypeRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.addType(articleAddTypeRequest);
    }

}
