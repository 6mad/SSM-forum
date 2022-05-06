package com.hubin.forum.domain.repository;

import com.hubin.forum.common.enums.MessageTypeEn;
import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Message;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/10/22
 * @desc
 **/
public interface MessageRepository {

    void save(Message message);

    Message get(Long id);

    PageResult<Message> page(PageRequest<Message> pageRequest);

    void updateToRead(Message message);

    Long countUnRead(Long receiver);

    void deleteInTypesAndTitle(List<MessageTypeEn> typeEns, String title);
}
