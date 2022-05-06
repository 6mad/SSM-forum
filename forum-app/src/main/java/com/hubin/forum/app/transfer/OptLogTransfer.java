package com.hubin.forum.app.transfer;

import org.springframework.util.ObjectUtils;
import com.hubin.forum.api.request.user.UserOptLogPageRequest;
import com.hubin.forum.common.enums.OptLogTypeEn;
import com.hubin.forum.domain.entity.OptLog;

/**
 * @author Hubin
 * @create 2021/12/9
 * @desc
 **/
public class OptLogTransfer {

    public static OptLog toOptLog(UserOptLogPageRequest request) {
        return OptLog.builder()
                .operatorId(request.getOperatorId())
                .type(ObjectUtils.isEmpty(request.getType()) ? null : OptLogTypeEn.getEntity(request.getType()))
                .build();
    }

}
