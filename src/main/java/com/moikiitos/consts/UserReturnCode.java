package com.moikiitos.consts;

import com.moikiitos.consts.ReturnCode;

/**
 * function description
 *
 * @author xiekuan
 * @Description return to front code
 * @date 2/24/24
 */
public enum UserReturnCode implements ReturnCode {


    //空参数 0
    ERROR_PARAM(0, "invalid parameter"),

    //验证码 100 - 109
    VALIDATE_CODE_SEND_SUCCESS(100, "The verification code send successfully"),
    VALIDATE_CODE_SEND_FAIL(101, "The verification code send failed"),
    VALIDATE_CODE_CHECK_PASS(102, "The verification code check passed"),
    VALIDATE_CODE_CHECK_FAIL(103, "The verification code check fails"),
    //login
    LOGIN_SUCCESS(1000, "Login successfully"),
    LOGIN_FAIL(1001, "Login failed"),
    LOGIN_PASSWORD_ERR(1002, "Login failed, password error"),
    LOGIN_LOCK_ACCOUNT(1003, "Login failed, account has been locked"),
    LOGIN_PASSWORD_ERR_MORE(1004, "Login failed，too many password input error"),
    LOGIN_UNKNOW_ACCOUT(1007, "Account does not exist"),
    ACCOUT_UNLOGIN(1008, "Account does not login"),

    //logout
    LOGOUT_SUCCESS(1015, "The user logout successfully"),
    LOGOUT_FAIL(1016, "The user logout failed"),

    //register
    ACCOUNT_EXIST(1030, "The user register failed, the account has exist"),
    NAME_EXIST(1031, "The user name has being exist"),
    PHONE_NUM_EXIST(1032, "The phone number has being registered"),
    EMAIL_EXIST(1033, "The email has being registered"),
    REGISTER_SUCCESS(1035, "Register successfully"),
    REGISTER_FAIL(1036, "Register failed"),
    REGISTER_GET_VALIDATE_CODE_SUCCESS(1037, "Get the verification code successfully"),


    ACCOUNT_REGISTER(1038, "The account has been registered"),
    ACCOUNT_NOT_REGISTER(1039, "The account not register"),
    SEND_VALIDATE_CODE_SUCCESS(1040, "Get verification code success"),
    SEND_VALIDATE_CODE_FAIL(1041, "Get verification code failed"),

    //format
    FORMAT_PHONE_NUM_ERR(1060, "The phone number format error"),
    FORMAT_EMAIL_ERR(1061, "The email format error"),


    //INFO 1080-2010
    INFO_RESET_PASSWORD_SUCCESS(1080, "Reset password success"),
    INFO_RESET_PASSWORD_FAIL(1081, "Reset password failed"),
    QUERY_USER_INFO_SUCCESS(1082, "Describe user info successfully"),
    QUERY_USER_INFO_FAIL(1083, "Describe user info failed"),
    INFO_RESET_SUCCESS(1086, "Reset message success"),

    //用户关系
    FOLLOW_SUCCESS(3010, "Follow successfully"),
    FOLLOW_FAIL(3011, "Follow failed"),

    UN_FOLLOW_SUCCESS(3012, "Cancel follow successfully"),
    UN_FOLLOW_FAIL(3013, "Cancel follow failed"),

    REMOVE_FOLLOWER_SUCCESS(3014, "Remove fans successfully"),
    REMOVE_FOLLOWER_FAIL(3015, "Remove fans failed"),

    LIST_FOLLOWER_SUCCESS(3016, "Get fans list successfully"),
    LIST_FOLLOWER_FAIL(3017, "Get fans list failed"),

    LIST_FOLLOWEE_SUCCESS(3018, "Get the follower successfully"),
    LIST_FOLLOWEE_FAIL(3019, "Get the follower failed"),

    ;


    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    UserReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


}
