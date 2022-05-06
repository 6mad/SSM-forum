package com.hubin.forum.api.service;

import com.hubin.forum.api.response.message.MessagePageResponse;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.message.MessagePageRequest;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
public interface MessageApiService {

    ResultModel<PageResponseModel<MessagePageResponse>> page(PageRequestModel<MessagePageRequest> request);

    ResultModel markIsRead(Long messageId);

    ResultModel<Long> countUnRead();

}
