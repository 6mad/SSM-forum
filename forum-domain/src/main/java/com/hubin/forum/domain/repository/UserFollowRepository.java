package com.hubin.forum.domain.repository;

import com.hubin.forum.common.enums.FollowedTypeEn;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/3
 * @desc
 **/
public interface UserFollowRepository {

    List<Long> getAllFollowerIds(Long follower, FollowedTypeEn type);
}
