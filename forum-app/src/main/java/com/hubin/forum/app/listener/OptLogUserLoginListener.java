package com.hubin.forum.app.listener;

import org.springframework.stereotype.Component;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.OptLog;
import com.hubin.forum.domain.repository.OptLogRepository;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/4
 * @desc
 **/
@Component
public class OptLogUserLoginListener extends EventBus.EventHandler<OptLog> {

    @Resource
    private OptLogRepository optLogRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.USER_LOGIN;
    }

    @Override
    public void onMessage(OptLog optLog) {
        optLogRepository.save(optLog);
    }
}
