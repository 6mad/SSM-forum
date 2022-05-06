package com.hubin.forum.app.listener;

import com.hubin.forum.app.support.Pair;
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
public class SearchFaqUpdateListener extends EventBus.EventHandler<Pair<Faq>> {

    @Resource
    private SearchService searchService;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.FAQ_UPDATE;
    }

    @Override
    public void onMessage(Pair<Faq> pair) {
        Faq newFaq = pair.getValue1();

        searchService.deleteByPostsId(newFaq.getId());

        searchService.save(Search.builder()
                .content(newFaq.getMarkdownContent())
                .entityId(newFaq.getId())
                .title(newFaq.getTitle())
                .type(SearchTypeEn.POSTS)
                .build());
    }
}
