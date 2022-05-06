package com.hubin.forum.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hubin.forum.common.enums.FollowedTypeEn;

/**
 * @author Hubin
 * @create 2021/11/20
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow extends BaseEntity {

    private Long followed;

    private FollowedTypeEn followedType;

    private Long follower;

}
