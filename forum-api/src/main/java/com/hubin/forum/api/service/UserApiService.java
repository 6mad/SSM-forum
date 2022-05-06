package com.hubin.forum.api.service;

import com.hubin.forum.api.request.user.*;
import com.hubin.forum.api.response.user.UserInfoResponse;
import com.hubin.forum.api.response.user.UserOptLogPageResponse;
import com.hubin.forum.api.response.user.UserPageResponse;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
public interface UserApiService {

    ResultModel enable(Long uid);

    ResultModel disable(Long uid);

    ResultModel follow(Long followed);

    ResultModel cancelFollow(Long followed);

    ResultModel<PageResponseModel<UserPageResponse>> pageFollower(PageRequestModel<Long> pageRequestModel);

    ResultModel<PageResponseModel<UserPageResponse>> pageFans(PageRequestModel<Long> pageRequestModel);

    ResultModel<Boolean> hasFollow(Long followed);

    ResultModel<UserInfoResponse> info(String token);

    ResultModel<UserInfoResponse> info(Long uid);

    ResultModel<String> register(UserRegisterRequest request);

    ResultModel<String> emailLogin(UserEmailLoginRequest request);

    ResultModel logout(UserTokenLogoutRequest request);

    ResultModel updateInfo(UserUpdateInfoRequest request);

    ResultModel updateHeadImg(String linkFilenameData);

    ResultModel updatePwd(UserUpdatePwdRequest request);

    ResultModel<PageResponseModel<UserPageResponse>> adminPage(PageRequestModel<UserAdminPageRequest> pageRequestModel);

    ResultModel<PageResponseModel<UserPageResponse>> pageActive(PageRequestModel pageRequestModel);

    ResultModel<PageResponseModel<UserOptLogPageResponse>> pageOptLog(PageRequestModel<UserOptLogPageRequest> pageRequestModel);

    ResultModel updateRole(AdminBooleanRequest booleanRequest);
}
