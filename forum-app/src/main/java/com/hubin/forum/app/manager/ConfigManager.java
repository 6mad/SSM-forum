package com.hubin.forum.app.manager;

import com.hubin.forum.app.support.IsLogin;
import com.hubin.forum.app.support.LoginUserContext;
import com.hubin.forum.app.support.PageUtil;
import org.springframework.stereotype.Component;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.config.ConfigAddRequest;
import com.hubin.forum.api.request.config.ConfigPageRequest;
import com.hubin.forum.api.request.config.ConfigUpdateRequest;
import com.hubin.forum.api.response.config.ConfigResponse;
import com.hubin.forum.app.transfer.ConfigTransfer;
import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.enums.UserRoleEn;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.common.support.CheckUtil;
import com.hubin.forum.domain.entity.Config;
import com.hubin.forum.domain.repository.ConfigRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
@Component
public class ConfigManager {

    @Resource
    private ConfigRepository configRepository;

    @IsLogin(role = UserRoleEn.ADMIN)
    public void add(ConfigAddRequest request) {
        Config config = ConfigTransfer.toConfig(request);
        config.setCreator(LoginUserContext.getUser().getId());

        configRepository.save(config);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public void update(ConfigUpdateRequest request) {
        Config config = configRepository.get(request.getId());
        CheckUtil.isEmpty(config, ErrorCodeEn.CONFIG_NOT_EXIST);

        ConfigTransfer.update(config, request);
        configRepository.update(config);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public void state(AdminBooleanRequest request) {
        Config config = configRepository.get(request.getId());
        CheckUtil.isEmpty(config, ErrorCodeEn.CONFIG_NOT_EXIST);

        config.setState(request.getIs() ? AuditStateEn.PASS : AuditStateEn.REJECT);
        configRepository.update(config);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public PageResponseModel<ConfigResponse> page(PageRequestModel<ConfigPageRequest> pageRequestModel) {
        ConfigPageRequest request = pageRequestModel.getFilter();

        PageResult<Config> pageResult = configRepository.page(PageUtil.buildPageRequest(pageRequestModel, ConfigTransfer.toConfig(request)));

        return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), ConfigTransfer.toConfigResponses(pageResult.getList()));
    }

    public List<ConfigResponse> queryAvailable(Set<String> types) {
        return ConfigTransfer.toConfigResponses(configRepository.queryAvailable(types));
    }
}
