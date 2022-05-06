package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.Faq;
import com.hubin.forum.domain.entity.Tag;
import com.hubin.forum.domain.repository.TagRepository;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 2021/11/24
 * @desc
 **/
@Component
public class FaqCreateListener extends EventBus.EventHandler<Faq> {

    @Resource
    private TagRepository tagRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.FAQ_CREATE;
    }

    @Override
    public void onMessage(Faq faq) {
        Set<Long> tagIds = faq.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        tagRepository.increaseRefCount(tagIds);
    }
}
