package com.hubin.forum.domain.repository;

import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Faq;
import com.hubin.forum.domain.entity.value.PostsPageQueryValue;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/11/1
 * @desc
 **/
public interface FaqRepository {

    void save(Faq faq);

    void update(Faq faq);

    void updateEntity(Faq faq);

    Faq get(Long id);

    PageResult<Faq> page(Integer pageNo, Integer pageSize, PostsPageQueryValue pageQueryValue);

    List<Faq> hots(int size);

}
