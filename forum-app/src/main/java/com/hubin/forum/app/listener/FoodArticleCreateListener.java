package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.common.enums.FollowedTypeEn;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.Article;
import com.hubin.forum.domain.entity.UserFood;
import com.hubin.forum.domain.repository.UserFollowRepository;
import com.hubin.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 2021/12/3
 * @desc
 **/
@Component
public class FoodArticleCreateListener extends EventBus.EventHandler<Article> {

    @Resource
    private UserFollowRepository userFollowRepository;

    @Resource
    private UserFoodRepository userFoodRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.ARTICLE_CREATE;
    }

    @Override
    public void onMessage(Article article) {
        List<Long> followedIds = userFollowRepository.getAllFollowerIds(article.getAuthorId(), FollowedTypeEn.USER);
        if (ObjectUtils.isEmpty(followedIds)) {
            return;
        }

        List<UserFood> userFoods = followedIds.stream().map(userId -> {
            return UserFood.builder()
                    .postsId(article.getId())
                    .userId(userId)
                    .build();
        }).collect(Collectors.toList());

        userFoodRepository.batchSave(userFoods);
    }
}
