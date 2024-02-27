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
        //do login
        User user = userService.queryUser(searchStr);
        if (user == null) {
            return new WebResult(WebResult.RESULT_FAIL, "Don't find user");
        }
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
        return new WebResult(WebResult.RESULT_SUCCESS, "Search user success", userInfoDto);
    }

}
