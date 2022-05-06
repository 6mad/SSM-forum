package com.hubin.forum.infrastructure;

import com.hubin.forum.infrastructure.dal.dao.UserFollowDAO;
import org.springframework.stereotype.Repository;
import com.hubin.forum.common.enums.FollowedTypeEn;
import com.hubin.forum.domain.repository.UserFollowRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/3
 * @desc
 **/
@Repository
public class UserFollowRepositoryImpl implements UserFollowRepository {

    @Resource
    private UserFollowDAO userFollowDAO;

    @Override
    public List<Long> getAllFollowerIds(Long follower, FollowedTypeEn type) {
        return userFollowDAO.getAllFollowerIds(follower, type.getValue());
    }
}
