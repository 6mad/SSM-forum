package com.hubin.forum.portal.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.comment.CommentCreateRequest;
import com.hubin.forum.api.service.CommentApiService;
import com.hubin.forum.common.constant.Constant;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hubin
 * @create 2021/11/17
 * @desc
 **/
@RestController
@RequestMapping("/comment-rest")
public class CommentRestController {

    @Resource
    private CommentApiService commentApiService;

    @PostMapping("/create")
    public ResultModel create(@RequestBody CommentCreateRequest createRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return commentApiService.create(createRequest);
    }

}
