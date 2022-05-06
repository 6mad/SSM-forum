package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.enums.SearchTypeEn;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.Faq;
import com.hubin.forum.domain.entity.Search;
import com.hubin.forum.domain.service.SearchService;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Component
public class SearchFaqCreateListener extends EventBus.EventHandler<Faq> {

    @Resource
    private SearchService searchService;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.FAQ_CREATE;
    }

    @Override
    public void onMessage(Faq faq) {
        searchService.deleteByPostsId(faq.getId());

        searchService.save(Search.builder()
                .content(faq.getMarkdownContent())
                .entityId(faq.getId())
                .title(faq.getTitle())
                .type(SearchTypeEn.POSTS)
                .build());
    }
}
