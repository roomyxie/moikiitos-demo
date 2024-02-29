package com.moikiitos.controller.vo;

import lombok.Data;

@Data
public class BlogListReq {
    public String type;

    public int page;

    public int count;

    public long currentUserId;

    public long searchUserId;

}
