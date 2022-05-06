package com.hubin.forum.infrastructure;

import org.springframework.stereotype.Service;
import com.hubin.forum.common.enums.MessageChannelEn;
import com.hubin.forum.common.enums.MessageContentTypeEn;
import com.hubin.forum.domain.entity.Message;
import com.hubin.forum.domain.repository.MessageRepository;
import com.hubin.forum.domain.service.MailService;
import com.hubin.forum.domain.service.MessageService;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/10/22
 * @desc
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepository messageRepository;

    @Resource
    private MailService mailService;

    @Override
    public void send(Message message) {
        // 邮件
        if (MessageChannelEn.MAIL.equals(message.getChannel())) {
            if (MessageContentTypeEn.HTML.equals(message.getContentType())) {
                mailService.sendHtml(message);
            }
            if (MessageContentTypeEn.TEXT.equals(message.getContentType())) {
                mailService.sendText(message);
            }
        }
        // 站内信
        if (MessageChannelEn.STATION_LETTER.equals(message.getChannel())) {
            // do nothing
            messageRepository.save(message);
        }
    }

}
