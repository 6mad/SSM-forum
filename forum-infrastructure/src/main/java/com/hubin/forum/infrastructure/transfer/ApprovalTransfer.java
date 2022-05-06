package com.hubin.forum.infrastructure.transfer;

import com.hubin.forum.common.enums.FollowedTypeEn;
import com.hubin.forum.domain.entity.Approval;
import com.hubin.forum.infrastructure.dal.dataobject.UserFollowDO;

/**
 * @author Hubin
 * @create 2021/12/1
 * @desc
 **/
public class ApprovalTransfer {

    public static UserFollowDO toUserFollowDO(Approval approval) {
        return UserFollowDO.builder()
                .followedType(FollowedTypeEn.POSTS.getValue())
                .followed(approval.getPostsId())
                .follower(approval.getUserId())
                .build();
    }

    public static Approval toApproval(UserFollowDO userFollowDO) {
        Approval approval = Approval.builder()
                .userId(userFollowDO.getFollower())
                .postsId(userFollowDO.getFollowed())
                .build();
        approval.setCreateAt(userFollowDO.getCreateAt());
        approval.setId(userFollowDO.getId());
        approval.setUpdateAt(userFollowDO.getUpdateAt());

        return approval;
    }
}
