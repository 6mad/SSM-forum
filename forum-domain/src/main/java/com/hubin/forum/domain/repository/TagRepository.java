package com.hubin.forum.domain.repository;

import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Posts;
import com.hubin.forum.domain.entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/7/31
 * @desc
 **/
public interface TagRepository {

    void save(Tag tag);

    List<Tag> query(Tag tag);

    List<Tag> queryByIds(Set<Long> ids);

    List<Tag> queryByState(AuditStateEn auditState);

    void deletePostsMapping(Long articleId);

    void increaseRefCount(Set<Long> ids);

    void decreaseRefCount(Set<Long> ids);

    Tag getByNameAndState(String name, AuditStateEn pass);

    PageResult<Posts> pagePosts(PageRequest<Long> longPageRequest);

    PageResult<Posts> pagePostsByTagIds(PageRequest<Set<Long>> pageRequest);

    PageResult<Tag> page(PageRequest<Tag> tagPageRequest);

    Tag get(Long id);

    void update(Tag tag);
}
