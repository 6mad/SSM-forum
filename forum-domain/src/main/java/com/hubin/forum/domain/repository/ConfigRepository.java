package com.hubin.forum.domain.repository;

import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Config;

import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
public interface ConfigRepository {

    /**
     * 保存
     * @param config
     */
    void save(Config config);

    /**
     * 查询
     * @param id
     * @return
     */
    Config get(Long id);

    /**
     * 更新
     * @param config
     */
    void update(Config config);

    /**
     * 根据类型查询可用
     * @param types
     * @return
     */
    List<Config> queryAvailable(Set<String> types);

    /**
     * 分页查询
     * @param configPageRequest
     * @return
     */
    PageResult<Config> page(PageRequest<Config> configPageRequest);
}
