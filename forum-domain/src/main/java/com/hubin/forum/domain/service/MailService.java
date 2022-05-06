package com.hubin.forum.domain.service;

import com.hubin.forum.domain.entity.Message;

/**
 * @author Hubin
 * @create 2021/12/7
 * @desc
 **/
public interface MailService {

    void sendHtml(Message mailMessage);

    void sendText(Message mailMessage);

}
