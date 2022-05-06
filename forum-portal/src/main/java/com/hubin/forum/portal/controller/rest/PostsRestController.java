package com.hubin.forum.portal.controller.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.service.PostsApiService;
import com.hubin.forum.common.constant.Constant;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hubin
 * @create 2021/11/25
 * @desc
 **/
@RestController
@RequestMapping("/posts-rest")
public class PostsRestController {

    @Resource
    private PostsApiService postsApiService;

    @PostMapping("/delete/{id}")
    public ResultModel delete(@PathVariable("id") Long id, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return postsApiService.delete(id);
    }

}
