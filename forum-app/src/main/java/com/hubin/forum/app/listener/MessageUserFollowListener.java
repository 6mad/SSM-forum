package com.hubin.forum.app.listener;

import com.hubin.forum.app.support.Pair;
import com.hubin.forum.common.enums.*;
import org.springframework.stereotype.Component;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.Message;
import com.hubin.forum.domain.entity.value.IdValue;
import com.hubin.forum.domain.repository.MessageRepository;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/5
 * @desc
 **/
@Component
public class MessageUserFollowListener extends EventBus.EventHandler<Pair<Long>> {

    @Resource
    private MessageRepository messageRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.USER_FOLLOW;
    }

    @Override
    public void onMessage(Pair<Long> pair) {
        Long followed = pair.getValue0();
        Long follower = pair.getValue1();

        messageRepository.save(Message.builder()
                .channel(MessageChannelEn.STATION_LETTER)
                .type(MessageTypeEn.FOLLOW_USER)
                .receiver(IdValue.builder()
                        .id(followed.toString())
                        .type(IdValueTypeEn.USER_ID)
                        .build())
                .read(MessageReadEn.NO)
                .contentType(MessageContentTypeEn.TEXT)
                .content("")
                .sender(IdValue.builder()
                        .id(follower.toString())
                        .type(IdValueTypeEn.USER_ID)
                        .build())
                .title("")
                .build());
    }
}
