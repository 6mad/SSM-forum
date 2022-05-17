package com.hubin.forum.app.listener;

import com.hubin.forum.common.enums.IdValueTypeEn;
import com.hubin.forum.common.enums.MessageContentTypeEn;
import com.hubin.forum.domain.entity.value.IdValue;
import org.springframework.stereotype.Component;
import com.hubin.forum.common.enums.MessageChannelEn;
import com.hubin.forum.common.enums.MessageTypeEn;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.Message;
import com.hubin.forum.domain.entity.User;
import com.hubin.forum.domain.service.MessageService;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/4
 * @desc
 **/
@Component
public class NotifyAdminUserRegisterListener extends EventBus.EventHandler<User> {

    @Resource
    private MessageService messageService;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.USER_REGISTER;
    }

    @Override
    public void onMessage(User user) {

        // 发送消息通知
        messageService.send(Message.builder()
                .channel(MessageChannelEn.MAIL)
                .type(MessageTypeEn.EMAIL_ATCTIVE)
                .sender(IdValue.builder()
                        .id(null)
                        .type(IdValueTypeEn.EMAIL)
                        .build())
                .receiver(IdValue.builder()
                        .id("SSM校园论坛 <1839224588@qq.com>")
                        .type(IdValueTypeEn.EMAIL)
                        .build())
                .title("有新用户注册啦")
                .content(user.toString())
                .contentType(MessageContentTypeEn.HTML)
                .build());
    }
}
