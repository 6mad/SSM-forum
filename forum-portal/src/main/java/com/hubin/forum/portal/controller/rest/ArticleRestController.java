package com.hubin.forum.portal.controller.rest;

import org.springframework.web.bind.annotation.*;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.article.ArticleSaveArticleRequest;
import com.hubin.forum.api.response.article.ArticleInfoResponse;
import com.hubin.forum.api.response.article.ArticleQueryTypesResponse;
import com.hubin.forum.api.service.ArticleApiService;
import com.hubin.forum.common.constant.Constant;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
@RestController
@RequestMapping("/article-rest")
public class ArticleRestController {

    @Resource
    private ArticleApiService articleApiService;

    @PostMapping("/save")
    public ResultModel<Long> save(@RequestBody ArticleSaveArticleRequest articleRequest, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.saveArticle(articleRequest);
    }

    @PostMapping("/{id}")
    public ResultModel<ArticleInfoResponse> get(@PathVariable("id") Long id, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));
        return articleApiService.info(id);
    }

    @PostMapping("/editArticleTypes")
    public ResultModel<List<ArticleQueryTypesResponse>> getAllType(HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return articleApiService.queryEditArticleTypes();
    }
}
