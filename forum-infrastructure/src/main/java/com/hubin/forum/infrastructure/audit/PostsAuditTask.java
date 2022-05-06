package com.hubin.forum.infrastructure.audit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.common.support.SafesUtil;
import com.hubin.forum.infrastructure.dal.dao.PostsDAO;
import com.hubin.forum.infrastructure.dal.dataobject.PostsDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/11/1
 * @desc 帖子自动审核通过
 **/
@Service
public class PostsAuditTask {

    @Resource
    private PostsDAO postsDAO;

    @Scheduled(cron = "0/2 * * * * ? ")
    public void task() {
        List<PostsDO> postsDOS = postsDAO.query(PostsDO.builder()
                .auditState(AuditStateEn.WAIT.getValue())
                .build());
        SafesUtil.ofList(postsDOS).forEach(postsDO -> {
            postsDO.setAuditState(AuditStateEn.PASS.getValue());
            postsDAO.update(postsDO);
        });
    }

}
