package com.hubin.forum.facade.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.message.MessagePageRequest;
import com.hubin.forum.api.response.message.MessagePageResponse;
import com.hubin.forum.api.service.MessageApiService;
import com.hubin.forum.app.manager.MessageManager;
import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.MessageValidator;
import com.hubin.forum.facade.validator.PageRequestModelValidator;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/5
 * @desc
 **/
@Service
public class MessageApiServiceImpl implements MessageApiService {

    @Resource
    private MessageManager messageManager;

    @Override
    public ResultModel<PageResponseModel<MessagePageResponse>> page(PageRequestModel<MessagePageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);
        pageRequestModel.setFilter(JSON.parseObject(JSON.toJSONString(pageRequestModel.getFilter()), MessagePageRequest.class));
        MessageValidator.page(pageRequestModel.getFilter());

        return ResultModelUtil.success(messageManager.page(pageRequestModel));
    }

    @Override
    public ResultModel markIsRead(Long messageId) {
        messageManager.markIsRead(messageId);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<Long> countUnRead() {
        return ResultModelUtil.success(messageManager.countUnRead());
    }
}
