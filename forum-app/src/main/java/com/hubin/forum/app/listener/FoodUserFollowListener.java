package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.app.support.Pair;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.UserFood;
import com.hubin.forum.domain.repository.PostsRepository;
import com.hubin.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 22/2/1220/11/19
 * @desc
 **/
@Component
public class FoodUserFollowListener extends EventBus.EventHandler<Pair<Long>> {

    @Resource
    private PostsRepository postsRepository;

    @Resource
    private UserFoodRepository userFoodRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.USER_FOLLOW;
    }

    @Override
    public void onMessage(Pair<Long> pair) {
        Long followed = pair.getValue0();
        Long follower = pair.getValue1();

        List<Long> postsIds = postsRepository.getAllIdByAuthorId(followed);
        if (ObjectUtils.isEmpty(postsIds)) {
            return;
        }

        List<UserFood> userFoods = postsIds.stream().map(postsId -> {
            return UserFood.builder()
                    .postsId(postsId)
                    .userId(follower)
                    .build();
        }).collect(Collectors.toList());

        userFoodRepository.batchSave(userFoods);
    }
}
