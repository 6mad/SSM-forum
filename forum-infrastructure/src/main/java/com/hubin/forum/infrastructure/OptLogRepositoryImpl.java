package com.hubin.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubin.forum.infrastructure.dal.dao.OptLogDAO;
import com.hubin.forum.infrastructure.dal.dataobject.OptLogDO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.common.enums.OptLogTypeEn;
import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.OptLog;
import com.hubin.forum.domain.repository.OptLogRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 22/2/1220/10/22
 * @desc
 **/
@Repository
public class OptLogRepositoryImpl implements OptLogRepository {

    @Resource
    private OptLogDAO optLogDAO;

    @Override
    public void save(OptLog optLog) {
        OptLogDO optLogDO = OptLogDO.builder()
                .content(optLog.getContent())
                .operatorId(optLog.getOperatorId())
                .type(optLog.getType().getValue())
                .build();
        optLogDO.initBase();

        optLogDAO.insert(optLogDO);
    }

    @Override
    public PageResult<OptLog> page(PageRequest<OptLog> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        OptLog optLog = pageRequest.getFilter();
        OptLogDO optLogDO = OptLogDO.builder()
                .operatorId(optLog.getOperatorId())
                .type(ObjectUtils.isEmpty(optLog.getType()) ? null : optLog.getType().getValue())
                .build();
        List<OptLogDO> optLogDOS = optLogDAO.query(optLogDO);
        PageInfo<OptLogDO> pageInfo = new PageInfo<>(optLogDOS);

        if (ObjectUtils.isEmpty(optLogDOS)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), optLogDOS.stream().map(optLogDO1 -> {
            OptLog optLog1 =  OptLog.builder()
                    .type(OptLogTypeEn.getEntity(optLogDO1.getType()))
                    .operatorId(optLogDO1.getOperatorId())
                    .content(optLogDO1.getContent())
                    .build();
            optLog1.setCreateAt(optLogDO1.getCreateAt());
            optLog1.setUpdateAt(optLogDO1.getUpdateAt());
            optLog1.setId(optLogDO1.getId());
            return optLog1;
        }).collect(Collectors.toList()));
    }
}
