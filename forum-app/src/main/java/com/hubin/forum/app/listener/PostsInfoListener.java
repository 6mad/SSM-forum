package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.BasePosts;
import com.hubin.forum.domain.repository.PostsRepository;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/11/10
 * @desc
 **/
@Component
public class PostsInfoListener extends EventBus.EventHandler<BasePosts> {

    @Resource
    private PostsRepository postsRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.POSTS_INFO;
    }

    @Override
    public void onMessage(BasePosts posts) {
        postsRepository.increaseViews(posts.getId(), posts.getUpdateAt());
    }
}
