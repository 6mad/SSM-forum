package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.app.support.Pair;
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
public class SearchArticleUpdateListener  extends EventBus.EventHandler<Pair<Article>> {

    @Resource
    private SearchService searchService;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.ARTICLE_UPDATE;
    }

    @Override
    public void onMessage(Pair<Article> pair) {
        Article newArticle = pair.getValue1();

        searchService.deleteByPostsId(newArticle.getId());

        searchService.save(Search.builder()
                .content(newArticle.getMarkdownContent())
                .entityId(newArticle.getId())
                .title(newArticle.getTitle())
                .type(SearchTypeEn.POSTS)
                .build());
    }
}
