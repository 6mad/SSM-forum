package com.hubin.forum.infrastructure.dal.dao;

import com.hubin.forum.infrastructure.dal.dataobject.ConfigDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
public interface ConfigDAO {

    /**
     * 插入
     * @param configDO
     */
    void insert(ConfigDO configDO);

    /**
     * 更新
     * @param configDO
     */
    void update(ConfigDO configDO);

    /**
     * 查询
     * @param id
     * @return
     */
    ConfigDO get(Long id);

    /**
     * 查询
     * @param configDO
     * @return
     */
    List<ConfigDO> query(ConfigDO configDO);

    /**
     * 查询可用
     * @param types
     * @param nowDate
     * @return
     */
    List<ConfigDO> queryAvailable(@Param("types") Set<String> types,
                                  @Param("nowDate") Date nowDate,
                                  @Param("state") String state);

}
