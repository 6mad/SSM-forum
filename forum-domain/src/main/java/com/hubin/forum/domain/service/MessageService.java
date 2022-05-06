package com.hubin.forum.domain.service;

import com.hubin.forum.domain.entity.Message;

/**
 * @author Hubin
 * @create 2021/10/22
 * @desc
 **/
public interface MessageService {

    void send(Message message);

}
