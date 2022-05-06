package com.hubin.forum.portal.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.faq.FaqAdminPageRequest;
import com.hubin.forum.api.response.faq.FaqUserPageResponse;
import com.hubin.forum.api.service.FaqApiService;
import com.hubin.forum.api.service.PostsApiService;
import com.hubin.forum.common.constant.Constant;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hubin
 * @create 2021/10/27
 * @desc
 **/
@RestController
@RequestMapping("/admin-rest/faq")
public class AdminRestFaqController {

    @Resource
    private FaqApiService faqApiService;

    @Resource
    private PostsApiService postsApiService;

    @PostMapping("/page")
    public ResultModel<PageResponseModel<FaqUserPageResponse>> page(@RequestBody PageRequestModel<FaqAdminPageRequest> pageRequestModel
            , HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return faqApiService.adminPage(pageRequestModel);
    }

    @PostMapping("/audit-state")
    public ResultModel auditState(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return postsApiService.auditState(booleanRequest);
    }

}
