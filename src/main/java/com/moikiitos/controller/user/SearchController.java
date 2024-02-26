package com.moikiitos.controller.user;

import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.dto.BlogInfoDto;
import com.moikiitos.service.dto.UserInfoDto;
import com.moikiitos.service.login.LoginService;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import com.moikiitos.consts.UserReturnCode;
import com.moikiitos.service.user.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/search")
@RestController
@Slf4j
public class SearchController {
    @Autowired
    UserService userService;

    @PrintUrlAnno
    @PostMapping("/search")
    public BaseResult search(@RequestBody Map<String, Object> requestMap) {

        String searchStr = (String) requestMap.get("searchStr");

        log.debug("searchStr = " + searchStr);
        //do login
        User user = userService.queryUser(searchStr);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userId(user.getId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
        return new WebResult(WebResult.RESULT_SUCCESS, "search user success", userInfoDto);
    }

}
