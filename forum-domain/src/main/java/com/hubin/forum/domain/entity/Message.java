package com.hubin.forum.domain.entity;

import com.hubin.forum.domain.entity.value.IdValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hubin.forum.common.enums.MessageChannelEn;
import com.hubin.forum.common.enums.MessageContentTypeEn;
import com.hubin.forum.common.enums.MessageReadEn;
import com.hubin.forum.common.enums.MessageTypeEn;

/**
 * @author Hubin
 * @create 2021/7/30
 * @desc 消息
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

    /**
     * 消息发送渠道
     */
    private MessageChannelEn channel;

    /**
     * 消息类型
     */
    private MessageTypeEn type;

    /**
     * 是否已读
     */
    private MessageReadEn read;

    /**
     * 触发人
     */
    private IdValue sender;

    /**
     * 收件人
     */
    private IdValue receiver;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容类型
     */
    private MessageContentTypeEn contentType;

    /**
     * 内容
     */
    private String content;

}
