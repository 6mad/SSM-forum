package com.hubin.forum.domain.repository;

import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Article;
import com.hubin.forum.domain.entity.value.PostsPageQueryValue;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
public interface ArticleRepository {

    void save(Article article);

    Article get(Long id);

    void update(Article article);

    PageResult<Article> page(Integer pageNo, Integer pageSize, PostsPageQueryValue pageQueryValue);
}
