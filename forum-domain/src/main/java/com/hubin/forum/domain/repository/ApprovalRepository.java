package com.hubin.forum.domain.repository;

import com.hubin.forum.domain.entity.Approval;

/**
 * @author Hubin
 * @create 2021/12/1
 * @desc
 **/
public interface ApprovalRepository {

    void save(Approval approval);

    void delete(Long approvalId);

    Approval get(Long postsId, Long userId);

}
