package com.moikiitos.controller.vo;

import lombok.Data;

@Data
public class BlogUnFollowReq {
    Long userId;
    Long followerId;

}
