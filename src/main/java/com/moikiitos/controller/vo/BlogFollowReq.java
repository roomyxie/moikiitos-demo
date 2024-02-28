package com.moikiitos.controller.vo;

import lombok.Data;

@Data
public class BlogFollowReq {
    Long userId;
    Long followeeId;

}
