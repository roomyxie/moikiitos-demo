package com.moikiitos.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

    private Long userId;

    private String nickName;

    private String realName;

    private String email;
}
