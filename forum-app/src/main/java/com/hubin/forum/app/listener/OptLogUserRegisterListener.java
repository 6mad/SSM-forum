package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.OptLog;
import com.hubin.forum.domain.entity.User;
import com.hubin.forum.domain.repository.OptLogRepository;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/10/22
 * @desc
 **/
@Component
public class OptLogUserRegisterListener extends EventBus.EventHandler<User> {

    @Resource
    private OptLogRepository optLogRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.USER_REGISTER;
    }

    @Override
    public void onMessage(User user) {

        // 保存操作记录
        optLogRepository.save(OptLog.createUserRegisterRecordLog(user.getId(), user));
    }
}
