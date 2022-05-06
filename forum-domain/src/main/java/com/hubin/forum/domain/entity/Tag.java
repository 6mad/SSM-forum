package com.hubin.forum.domain.entity;

import lombok.*;
import com.hubin.forum.common.enums.AuditStateEn;

/**
 * @author Hubin
 * @create 2021/7/30
 * @desc 帖子标签
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity {

    private String groupName;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 引用统计
     */
    private Long refCount;

    /**
     * 创建人
     */
    private Long creatorId;

    /**
     * 审核状态
     */
    private AuditStateEn auditState;
}
