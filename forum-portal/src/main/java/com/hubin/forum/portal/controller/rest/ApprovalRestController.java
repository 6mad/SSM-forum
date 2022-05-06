package com.hubin.forum.portal.controller.rest;

import org.springframework.web.bind.annotation.*;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.service.ApprovalApiService;
import com.hubin.forum.common.constant.Constant;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hubin
 * @create 2021/12/1
 * @desc
 **/
@RestController
@RequestMapping("/approval-rest")
public class ApprovalRestController {

    @Resource
    private ApprovalApiService approvalApiService;

    @PostMapping("/create/{postsId}")
    public ResultModel create(@PathVariable Long postsId, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return approvalApiService.create(postsId);
    }

    @PostMapping("/delete/{postsId}")
    public ResultModel delete(@PathVariable Long postsId, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return approvalApiService.delete(postsId);
    }
}
