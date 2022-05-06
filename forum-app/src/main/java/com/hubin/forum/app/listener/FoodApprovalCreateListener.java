package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.app.support.Pair;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.UserFood;
import com.hubin.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/3
 * @desc
 **/
@Component
public class FoodApprovalCreateListener extends EventBus.EventHandler<Pair<Long>> {

    @Resource
    private UserFoodRepository userFoodRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.APPROVAL_CREATE;
    }

    @Override
    public void onMessage(Pair<Long> pair) {
        Long userId = pair.getValue0();
        Long postsId = pair.getValue1();

        List<UserFood> userFoods = Arrays.asList(UserFood.builder()
                .postsId(postsId)
                .userId(userId)
                .build());

        userFoodRepository.batchSave(userFoods);
    }
}
