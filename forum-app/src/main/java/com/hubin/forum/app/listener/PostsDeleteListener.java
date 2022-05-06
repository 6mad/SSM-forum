package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.enums.PostsCategoryEn;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.BasePosts;
import com.hubin.forum.domain.repository.ArticleTypeRepository;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/11/25
 * @desc
 **/
@Component
public class PostsDeleteListener extends EventBus.EventHandler<BasePosts> {

    @Resource
    private ArticleTypeRepository articleTypeRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.POSTS_DELETE;
    }

    @Override
    public void onMessage(BasePosts basePosts) {
        // 文章类别引用数减
        if (PostsCategoryEn.ARTICLE.equals(basePosts.getCategory())) {
            articleTypeRepository.decreaseRefCount(basePosts.getTypeId());
        }
    }
}
