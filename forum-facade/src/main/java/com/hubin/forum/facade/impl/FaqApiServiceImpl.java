package com.hubin.forum.facade.impl;

import com.hubin.forum.api.request.faq.*;
import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.FaqValidator;
import com.hubin.forum.facade.validator.PageRequestModelValidator;
import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.response.faq.FaqHotsResponse;
import com.hubin.forum.api.response.faq.FaqInfoResponse;
import com.hubin.forum.api.response.faq.FaqUserPageResponse;
import com.hubin.forum.api.service.FaqApiService;
import com.hubin.forum.app.manager.FaqManager;
import com.hubin.forum.app.support.IsLogin;
import com.hubin.forum.common.enums.UserRoleEn;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/11/1
 * @desc
 **/
@Service
public class FaqApiServiceImpl implements FaqApiService {

    @Resource
    private FaqManager faqManager;

    @Override
    public ResultModel<Long> saveFaq(FaqSaveFaqRequest request) {
        FaqValidator.saveFaq(request);

        return ResultModelUtil.success(faqManager.saveFaq(request));
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    @Override
    public ResultModel<PageResponseModel<FaqUserPageResponse>> adminPage(PageRequestModel<FaqAdminPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(faqManager.adminPage(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<FaqUserPageResponse>> userPage(PageRequestModel<FaqUserPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(faqManager.userPage(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<FaqUserPageResponse>> authorPage(PageRequestModel<FaqAuthorPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(faqManager.authorPage(pageRequestModel));
    }

    @Override
    public ResultModel<FaqInfoResponse> info(Long id) {
        return ResultModelUtil.success(faqManager.info(id));
    }

    @Override
    public ResultModel<List<FaqHotsResponse>> hots(int size) {
        return ResultModelUtil.success(faqManager.hots(size));
    }

    @Override
    public ResultModel solution(FaqSolutionRequest request) {
        FaqValidator.solution(request);

        faqManager.solution(request);

        return ResultModelUtil.success();
    }
}
