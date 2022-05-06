package com.hubin.forum.infrastructure.github;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.support.CheckUtil;
import com.hubin.forum.common.support.GlobalViewConfig;
import com.hubin.forum.common.support.LogUtil;
import com.hubin.forum.domain.service.GithubService;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 22/2/12
 * @desc
 **/
@Slf4j
@Data
@ConfigurationProperties(prefix = "custom-config.oauth.github")
@Component
public class GithubServiceImpl implements GithubService {

    private String clientSecret;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private GlobalViewConfig globalViewConfig;

    @Override
    public JSONObject getUserInfo(String code) {
        try {
            String token = getAccessToken(code);
            return getUser(token);
        } catch (Exception e) {
            CheckUtil.isTrue(true, ErrorCodeEn.GITHUB_OAUTH_ERROR);
        }
        return null;
    }

    private JSONObject getUser(String token) {
        String url = "https://api.github.com/user";

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("accept", "application/json");
        headers.add("Authorization", "token " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        LogUtil.info(log, "github getUser url={}, httpEntity={}", url, JSON.toJSONString(httpEntity));
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        String userBody = responseEntity.getBody();
        LogUtil.info(log, "github getUser url={}, userBody={}", url, userBody);

        return JSON.parseObject(userBody);
    }

    private String getAccessToken(String code) {
        String url = "https://github.com/login/oauth/access_token?client_id=" + globalViewConfig.getGithubClientId() + "&client_secret=" + clientSecret + "&code=" + code;

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("accept", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        LogUtil.info(log, "github getAccessToken url={}, httpEntity={}", url, JSON.toJSONString(httpEntity));
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        String tokenBody = responseEntity.getBody();
        LogUtil.info(log, "github getAccessToken url={}, tokenBody={}", url, tokenBody);
        JSONObject jsonObject = JSON.parseObject(tokenBody);

        // {"access_token":"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","token_type":"bearer","scope":"user"}
        return jsonObject.getString("access_token");
    }
}
