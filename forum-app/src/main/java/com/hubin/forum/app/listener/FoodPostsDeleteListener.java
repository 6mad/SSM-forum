package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.BasePosts;
import com.hubin.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/4
 * @desc
 **/
@Component
public class FoodPostsDeleteListener extends EventBus.EventHandler<BasePosts> {

    @Resource
    private UserFoodRepository userFoodRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.POSTS_DELETE;
    }

    @Override
    public void onMessage(BasePosts basePosts) {
        userFoodRepository.deleteByPostsId(basePosts.getId());
    }
}
