package com.hubin.forum.app.listener;

import com.hubin.forum.common.enums.IdValueTypeEn;
import com.hubin.forum.common.enums.MessageChannelEn;
import com.hubin.forum.common.enums.MessageContentTypeEn;
import com.hubin.forum.common.enums.MessageTypeEn;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.Message;
import com.hubin.forum.domain.entity.User;
import com.hubin.forum.domain.entity.value.IdValue;
import com.hubin.forum.domain.service.MessageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserRegisterListener extends EventBus.EventHandler<User> {

    @Resource
    private MessageService messageService;

    @Override
    public EventBus.Topic topic() {return EventBus.Topic.USER_REGISTER;}

    @Override
    public void onMessage(User user) {
        // 发送激活邮件
        messageService.send(Message.builder()
                .channel(MessageChannelEn.MAIL)
                .type(MessageTypeEn.EMAIL_ATCTIVE)
                .sender(IdValue.builder()
                        .id(null)
                        .type(IdValueTypeEn.EMAIL)
                        .build())
                .receiver(IdValue.builder()
                        .id(user.getEmail())
                        .type(IdValueTypeEn.EMAIL)
                        .build())
                .title(user.getNickname()+",Welcome register")
                .content("<html><h1>我是激活邮件.....</h1><p>不过尴尬的是激活部分还没写好<br/>所以....这封邮件仅仅是测试" +
                        "<br/><a href='http://www.binghub.xyz'>前往应用首页</a></p>")
                .contentType(MessageContentTypeEn.HTML)
                .build());
    }
}
