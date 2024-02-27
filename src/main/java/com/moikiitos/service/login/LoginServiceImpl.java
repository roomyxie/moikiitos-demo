package com.moikiitos.service.login;

import com.moikiitos.dao.mapper.UserMapper;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.util.SecurityUtil;
import com.moikiitos.util.UserRegexUtil;
import com.moikiitos.consts.UserReturnCode;
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
        if (UserRegexUtil.isMobile(name)) {
            user = userMapper.selectByRealName(name);
        } else if (UserRegexUtil.isEmail(name)) {
            user = userMapper.selectByEmail(name);
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

        return new BaseResult(UserReturnCode.LOGIN_SUCCESS.getCode(), UserReturnCode.LOGIN_SUCCESS.getMessage());
    }

    @Override
    public UserReturnCode logout() {
        return UserReturnCode.LOGOUT_SUCCESS;
    }

    @Override
    public UserReturnCode register(String name, String email, String password) {
        User user = null;
        log.debug("{}registering.....", name);
        if (userMapper.selectByRealName(name) != null) {
            return UserReturnCode.ACCOUNT_EXIST;
        }
        //check the name whether registered
        if (UserRegexUtil.isMobile(name)) {
            if (userMapper.selectByRealName(name) != null) {
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setRealName(name);
            log.debug("The phone ({}) registering", name);

        } else if (UserRegexUtil.isEmail(email)) {
            if (userMapper.selectIdByEmail(email) != null) {
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setEmail(email);
            log.debug("The email({}) registering", name);
        }
        assert user != null;
        log.debug(user.toString());


        //encrypt password by salt
        String random = new Random().nextInt(999999) + "";
        //salt
        String resultPassword = SecurityUtil.md5Hash(password, random, 3);
        log.debug("Encrypt the password by MD5 = " + resultPassword);


        user.setLoginPassword(resultPassword);
        user.setSalt(random);
        user.setRegisterTime(new Date());
        user.setNickName(name);
        log.debug(user.toString());

        if (userMapper.insert(user) != 0) {
            return UserReturnCode.REGISTER_SUCCESS;
        }

        return UserReturnCode.REGISTER_FAIL;
    }
}
