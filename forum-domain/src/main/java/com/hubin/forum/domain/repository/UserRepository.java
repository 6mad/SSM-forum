package com.hubin.forum.domain.repository;

import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Follow;
import com.hubin.forum.domain.entity.User;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/7/31
 * @desc
 **/
public interface UserRepository {

    void save(User user);

    User get(Long id);

    User getByEmail(String email);

    void update(User user);

    List<User> queryByIds(List<Long> ids);

    PageResult<User> page(PageRequest<User> pageRequest);

    void follow(Long followed, Long id);

    PageResult<User> pageFollower(PageRequest<Long> longPageRequest);

    PageResult<User> pageFans(PageRequest<Long> longPageRequest);

    Follow getFollow(Long followed, Long follower);

    void cancelFollow(Long followId);

    PageResult<User> pageActive(PageRequest pageRequest);
}
