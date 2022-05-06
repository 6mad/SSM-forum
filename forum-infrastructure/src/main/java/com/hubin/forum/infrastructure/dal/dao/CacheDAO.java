package com.hubin.forum.infrastructure.dal.dao;

import com.hubin.forum.infrastructure.dal.dataobject.CacheDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 22/2/1220/7/23
 * @desc
 **/
public interface CacheDAO {

    void insertBatch(List<CacheDO> cacheDOS);

    void insert(CacheDO cacheDO);

    List<CacheDO> getAll();

    void batchDeleteByKeys(@Param("keys") Set<String> keys);

    void updateByKey(@Param("key") String key, @Param("value") String value);

}