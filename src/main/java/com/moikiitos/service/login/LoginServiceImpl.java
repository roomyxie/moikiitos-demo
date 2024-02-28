package com.moikiitos.service.login;

import com.moikiitos.dao.mapper.UserMapper;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.dto.UserInfoDto;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.util.SecurityUtil;
import com.moikiitos.util.UserRegexUtil;
import com.moikiitos.common.enums.UserReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public BaseResult login(String name, String password) {
        User user = null;

        //get password
        if (UserRegexUtil.isEmail(name)) {
            user = userMapper.selectByEmail(name);
        } else {
            user = userMapper.selectByRealName(name);
        }

        //not register
        if (user == null) {
            return new BaseResult(UserReturnCode.ACCOUNT_NOT_REGISTER.getCode(), UserReturnCode.ACCOUNT_NOT_REGISTER.getMessage());
        }


        String random = user.getSalt();
        //hash encrypt
        String resultPassword = SecurityUtil.md5Hash(password, random, 3);


        if (user.getLoginPassword().equals(resultPassword)) {
            log.debug("{}-password check pass, login success");
        }

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();

        return new BaseResult(UserReturnCode.LOGIN_SUCCESS.getCode(), UserReturnCode.LOGIN_SUCCESS.getMessage(), userInfoDto);
    }

    @Override
    public UserReturnCode logout() {
        return UserReturnCode.LOGOUT_SUCCESS;
    }

    @Override
    public UserReturnCode register(String name, String email, String password) {
        User user = new User();
        log.debug("{}registering.....", name);
        if (userMapper.selectByRealName(name) != null) {
            return UserReturnCode.ACCOUNT_EXIST;
        }
        user.setRealName(name);
        //check the name whether registered
        if (UserRegexUtil.isEmail(email)) {
            if (userMapper.selectByEmail(email) != null) {
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user.setEmail(email);
            log.debug("The email({}) registering", name);
        } else {
            return UserReturnCode.FORMAT_EMAIL_ERR;
        }
        log.debug(user.toString());


        //encrypt password by salt
        String random = new Random().nextInt(999999) + "";
        //salt
        String resultPassword = SecurityUtil.md5Hash(password, random, 3);
        log.debug("Encrypt the password by MD5 = " + resultPassword);


        user.setLoginPassword(resultPassword);
        user.setSalt(random);
        user.setRegisterTime(new Date());
        user.setNickName("u" + name);
        log.debug(user.toString());

        if (userMapper.insert(user) != 0) {
            return UserReturnCode.REGISTER_SUCCESS;
        }

        return UserReturnCode.REGISTER_FAIL;
    }
}
