package com.moikiitos.common.enums;

public enum RelationReturnCode implements ReturnCode {
    //empty 0
    ERROR_PARAM(0, "Input params error "),

    FOLLOW_SUCCESS(10, "Follow success"),
    FOLLOW_FAIL(11, "Follow failed"),

    UN_FOLLOW_SUCCESS(12, "Cancel follow success"),
    UN_FOLLOW_FAIL(13, "Cancel follow failed"),

    REMOVE_FOLLOWER_SUCCESS(14, "Remove follower success"),
    REMOVE_FOLLOWER_FAIL(15, "Remove follower failed"),

    LIST_FOLLOWER_SUCCESS(16, "Get follower list success"),
    LIST_FOLLOWER_FAIL(17, "Get follower list failed"),

    LIST_FOLLOWEE_SUCCESS(18, "Get followee list success"),
    LIST_FOLLOWEE_FAIL(19, "Get followee list failed"),
    ;

    private Integer code;
    private String message;

    RelationReturnCode(Integer code, String message) {
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
