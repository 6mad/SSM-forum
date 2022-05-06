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
import com.hubin.forum.api.request.tag.TagCreateRequest;
import com.hubin.forum.api.request.tag.TagPageRequest;
import com.hubin.forum.api.response.tag.TagPageResponse;
import com.hubin.forum.api.service.TagApiService;
import com.hubin.forum.common.constant.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
@RestController
@RequestMapping("/admin-rest/tag")
public class AdminRestTagController {

    @Resource
    private TagApiService tagApiService;

    @PostMapping("/page")
    public ResultModel<PageResponseModel<TagPageResponse>> page(@RequestBody PageRequestModel<TagPageRequest> pageRequestModel
            , HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return tagApiService.page(pageRequestModel);
    }

    @PostMapping("/audit-state")
    public ResultModel auditState(@RequestBody AdminBooleanRequest booleanRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return tagApiService.auditState(booleanRequest);
    }

    @PostMapping("/add")
    public ResultModel add(@RequestBody TagCreateRequest request) {
        return tagApiService.create(request);
    }

}
