package com.moikiitos.util;

import jakarta.servlet.http.HttpServletRequest;

public class UserUtil {
    public static Long getUserId(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return userId;
    }

    public static String getNickName(HttpServletRequest request) {
        String nickName = (String) request.getAttribute("nickName");
        return nickName;
    }
}
