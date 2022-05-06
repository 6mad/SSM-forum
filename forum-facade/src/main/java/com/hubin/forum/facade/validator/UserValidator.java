package com.hubin.forum.facade.validator;

import com.hubin.forum.api.request.user.*;
import com.hubin.forum.common.support.CheckUtil;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
public class UserValidator {

    public static void register(UserRegisterRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getEmail(), "email");
        CheckUtil.checkParamToast(request.getNickname(), "nickname");
        CheckUtil.checkParamToast(request.getPassword(), "password");
    }

    public static void emailLogin(UserEmailLoginRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getEmail(), "email");
        CheckUtil.checkParamToast(request.getPassword(), "password");
    }

    public static void updateInfo(UserUpdateInfoRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getEmail(), "email");
        CheckUtil.checkParamToast(request.getNickname(), "nickname");
        CheckUtil.checkParamToast(request.getSignature(), "signature");
    }

    public static void updatePwd(UserUpdatePwdRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getOldPassword(), "oldPassword");
        CheckUtil.checkParamToast(request.getNewPassword(), "newPassword");
    }

    public static void updateRole(UserUpdateRoleRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getUserId(), "userId");
        CheckUtil.checkParamToast(request.getRole(), "role");
    }

    public static void adminPage(UserAdminPageRequest request) {

    }

    public static void logout(UserTokenLogoutRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getToken(), "token");
    }
}
