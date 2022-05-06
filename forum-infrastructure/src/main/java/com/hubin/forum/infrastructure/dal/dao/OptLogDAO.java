package com.hubin.forum.infrastructure.dal.dao;

import com.hubin.forum.infrastructure.dal.dataobject.OptLogDO;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/4
 * @desc
 **/
public interface OptLogDAO {

    void insert(OptLogDO optLogDO);

    List<OptLogDO> query(OptLogDO optLogDO);
}
