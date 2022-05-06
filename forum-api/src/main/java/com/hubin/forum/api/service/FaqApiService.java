package com.hubin.forum.api.service;

import com.hubin.forum.api.request.faq.*;
import com.hubin.forum.api.response.faq.FaqHotsResponse;
import com.hubin.forum.api.response.faq.FaqInfoResponse;
import com.hubin.forum.api.response.faq.FaqUserPageResponse;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/11/1
 * @desc
 **/
public interface FaqApiService {

    ResultModel<Long> saveFaq(FaqSaveFaqRequest request);

    ResultModel<PageResponseModel<FaqUserPageResponse>> adminPage(PageRequestModel<FaqAdminPageRequest> pageRequestModel);

    ResultModel<PageResponseModel<FaqUserPageResponse>> userPage(PageRequestModel<FaqUserPageRequest> pageRequestModel);

    ResultModel<PageResponseModel<FaqUserPageResponse>> authorPage(PageRequestModel<FaqAuthorPageRequest> pageRequestModel);

    ResultModel<FaqInfoResponse> info(Long id);

    ResultModel<List<FaqHotsResponse>> hots(int size);

    ResultModel solution(FaqSolutionRequest request);
}
