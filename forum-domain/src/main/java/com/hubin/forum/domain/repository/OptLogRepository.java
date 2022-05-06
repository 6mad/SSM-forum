package com.hubin.forum.domain.repository;

import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.OptLog;

/**
 * @author Hubin
 * @create 2021/10/20
 * @desc
 **/
public interface OptLogRepository {

    void save(OptLog optLog);

    PageResult<OptLog> page(PageRequest<OptLog> pageRequest);
}
