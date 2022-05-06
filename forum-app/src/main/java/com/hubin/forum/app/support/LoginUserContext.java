package com.hubin.forum.app.support;

import com.hubin.forum.domain.entity.User;

public class LoginUserContext {

    /**
     * 当前登录用户
     */
    private static final ThreadLocal<User> CURRENT_LOGIN_USER = new ThreadLocal<>();

    /**
     * 当前登录用户登录凭证 token
     */
    private static final ThreadLocal<String> CURRENT_USER_LOGIN_TOKEN = new ThreadLocal<>();

    /**
     * 保存当前登录用户
     * @param loginUser
     */
    public static void setUser(User loginUser) {
        CURRENT_LOGIN_USER.set(loginUser);
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getUser() {
        return CURRENT_LOGIN_USER.get();
    }

    /**
     * 保存当前登录凭证
     * @param token
     */
    public static void setToken(String token) {
        CURRENT_USER_LOGIN_TOKEN.set(token);
    }

    /**
     * 获取当前登录凭证
     * @return
     */
    public static String getToken() {
        return CURRENT_USER_LOGIN_TOKEN.get();
    }

    public static void removeAll() {
        CURRENT_LOGIN_USER.remove();
        CURRENT_USER_LOGIN_TOKEN.remove();
    }

    private LoginUserContext() {}
}