package com.moikiitos.common.enums;

/**
 * function description
 *
 * @author xiekuan
 * @Description user status
 * @date 2/24/24
 */
public enum EmailStatusCode implements ReturnCode {


    EMAIL_STATUS_ACTIVE(0, "active"),

    EMAIL_STATUS_DELETE(1, "delete"),

    EMAIL_STATUS_FREEZE(2, "freeze"),

    ;


    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    EmailStatusCode(Integer code, String message) {
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
