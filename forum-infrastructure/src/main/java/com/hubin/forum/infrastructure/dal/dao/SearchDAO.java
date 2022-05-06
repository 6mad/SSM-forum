package com.hubin.forum.infrastructure.dal.dao;

import com.hubin.forum.infrastructure.dal.dataobject.SearchDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
public interface SearchDAO {

    void insert(SearchDO searchDO);

    void delete(@Param("type") String type, @Param("entityId") Long entityId);

    List<SearchDO> query(SearchDO searchDO);
}
