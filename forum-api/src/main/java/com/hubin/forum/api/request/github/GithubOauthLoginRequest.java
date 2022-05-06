package com.hubin.forum.api.request.github;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hubin.forum.api.request.user.UserBaseLoginRequest;

import java.io.Serializable;

/**
 * @author Hubin
 * @create 22/2/12
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubOauthLoginRequest extends UserBaseLoginRequest implements Serializable {

    private String code;
}
