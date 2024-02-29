package com.moikiitos.controller.user;

import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.dto.UserInfoDto;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import com.moikiitos.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/search")
@RestController
@Slf4j
public class SearchController {
    @Autowired
    UserService userService;

    @PrintUrlAnno
    @GetMapping("/searchUser")
    public BaseResult search(@RequestParam String searchStr) {

        log.debug("searchStr = " + searchStr);
        //do query
        User user = userService.queryUser(searchStr);
        if (user == null) {
            return new WebResult(WebResult.RESULT_FAIL, "Don't find user");
        }
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userId(user.getUserId())
                .realName(user.getRealName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
        return new WebResult(WebResult.RESULT_SUCCESS, "Search user success", userInfoDto);
    }

    @PrintUrlAnno
    @GetMapping("/searchUserById")
    public BaseResult searchUserById(@RequestParam Long userId) {

        log.debug("search = " + userId);
        //do query
        User user = userService.queryUserById(userId);
        if (user == null) {
            return new WebResult(WebResult.RESULT_FAIL, "Don't find user");
        }
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userId(user.getUserId())
                .realName(user.getRealName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
        return new WebResult(WebResult.RESULT_SUCCESS, "Search user success", userInfoDto);
    }

}
