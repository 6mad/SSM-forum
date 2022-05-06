package com.hubin.forum.portal.controller.admin;

import com.hubin.forum.portal.support.WebUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.config.ConfigAddRequest;
import com.hubin.forum.api.request.config.ConfigPageRequest;
import com.hubin.forum.api.request.config.ConfigUpdateRequest;
import com.hubin.forum.api.response.config.ConfigResponse;
import com.hubin.forum.api.service.ConfigApiService;
import com.hubin.forum.common.constant.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
@RestController
@RequestMapping("/admin-rest/config")
public class AdminRestConfigController {

    @Resource
    private ConfigApiService configApiService;

    @PostMapping("/add")
    public ResultModel add(@RequestBody ConfigAddRequest addRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return configApiService.add(addRequest);
    }

    @PostMapping("/update")
    public ResultModel update(@RequestBody ConfigUpdateRequest updateRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return configApiService.update(updateRequest);
    }

    @PostMapping("/state")
    public ResultModel state(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return configApiService.state(booleanRequest);
    }

    @PostMapping("/page")
    public ResultModel<PageResponseModel<ConfigResponse>> page(@RequestBody PageRequestModel<ConfigPageRequest> pageRequestModel, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return configApiService.page(pageRequestModel);
    }

}
