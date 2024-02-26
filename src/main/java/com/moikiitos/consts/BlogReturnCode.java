package com.moikiitos.consts;

public enum BlogReturnCode implements ReturnCode {

    //空参数 0
    ERROR_PARAM(0, "Input param error"),


    BLOG_QUERY_SUCCESS(10, "Describe blogs success"),
    BLOG_QUERY_FAIL(11, "Describe blogs failed"),
    BLOG_SUBMIT_SUCCESS(12, "Post blogs success"),
    BLOG_SUBMIT_FAIL(13, "Post blog failed");


    //代码
    private Integer code;
    //代码对应的信息
    private String message;

    BlogReturnCode(Integer code, String message) {
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
