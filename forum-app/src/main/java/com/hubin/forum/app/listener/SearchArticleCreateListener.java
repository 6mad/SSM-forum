package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.enums.SearchTypeEn;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.Article;
import com.hubin.forum.domain.entity.Search;
import com.hubin.forum.domain.service.SearchService;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Component
public class SearchArticleCreateListener extends EventBus.EventHandler<Article> {

    @Resource
    private SearchService searchService;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.ARTICLE_CREATE;
    }

    @Override
    public void onMessage(Article article) {
        searchService.deleteByPostsId(article.getId());

        searchService.save(Search.builder()
                .content(article.getMarkdownContent())
                .entityId(article.getId())
                .title(article.getTitle())
                .type(SearchTypeEn.POSTS)
                .build());
    }
}
