package com.hubin.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubin.forum.infrastructure.dal.dao.ConfigDAO;
import com.hubin.forum.infrastructure.dal.dataobject.ConfigDO;
import com.hubin.forum.infrastructure.transfer.ConfigTransfer;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Config;
import com.hubin.forum.domain.repository.ConfigRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

    @Resource
    private ConfigDAO configDAO;

    @Override
    public void save(Config config) {
        ConfigDO configDO = ConfigTransfer.toConfigDO(config);
        configDO.initBase();

        configDAO.insert(configDO);

        config.setId(configDO.getId());
    }

    @Override
    public Config get(Long id) {
        return ConfigTransfer.toConfig(configDAO.get(id));
    }

    @Override
    public void update(Config config) {
        configDAO.update(ConfigTransfer.toConfigDO(config));
    }

    @Override
    public List<Config> queryAvailable(Set<String> types) {
        return ConfigTransfer.toConfigs(configDAO.queryAvailable(types, new Date(), AuditStateEn.PASS.getValue()));
    }

    @Override
    public PageResult<Config> page(PageRequest<Config> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        List<ConfigDO> configDOList = configDAO.query(ConfigTransfer.toConfigDO(pageRequest.getFilter()));

        PageInfo<ConfigDO> pageInfo = new PageInfo<>(configDOList);
        if (ObjectUtils.isEmpty(configDOList)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), ConfigTransfer.toConfigs(configDOList));
    }
}
