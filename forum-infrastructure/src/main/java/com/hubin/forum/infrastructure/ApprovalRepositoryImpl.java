package com.hubin.forum.infrastructure;

import com.hubin.forum.infrastructure.dal.dao.UserFollowDAO;
import com.hubin.forum.infrastructure.dal.dataobject.UserFollowDO;
import com.hubin.forum.infrastructure.transfer.ApprovalTransfer;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.common.enums.FollowedTypeEn;
import com.hubin.forum.domain.entity.Approval;
import com.hubin.forum.domain.repository.ApprovalRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/1
 * @desc
 **/
@Repository
public class ApprovalRepositoryImpl implements ApprovalRepository {

    @Resource
    private UserFollowDAO userFollowDAO;

    @Override
    public void save(Approval approval) {
        UserFollowDO userFollowDO = ApprovalTransfer.toUserFollowDO(approval);
        userFollowDO.initBase();

        userFollowDAO.insert(userFollowDO);
    }

    @Override
    public void delete(Long approvalId) {
        userFollowDAO.delete(approvalId);
    }

    @Override
    public Approval get(Long postsId, Long userId) {
        List<UserFollowDO> userFollowDOS = userFollowDAO.query(UserFollowDO.builder()
                .follower(userId)
                .followed(postsId)
                .followedType(FollowedTypeEn.POSTS.getValue())
                .build());
        if (ObjectUtils.isEmpty(userFollowDOS)) {
            return null;
        }

        return ApprovalTransfer.toApproval(userFollowDOS.get(0));
    }
}
