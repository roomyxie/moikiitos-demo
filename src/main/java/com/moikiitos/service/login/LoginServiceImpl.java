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
        String oldPassword = "";
        log.debug("原始密码 = " + password);

        if (UserRegexUtil.isMobile(name)) {
            user = userMapper.selectByPhone(name);
        } else if (UserRegexUtil.isEmail(name)) {
            user = userMapper.selectByEmail(name);
        }

        //帐号未注册
        if (user == null) {
            return new BaseResult(UserReturnCode.ACCOUNT_NOT_REGISTER.getCode(), UserReturnCode.ACCOUNT_NOT_REGISTER.getMessage());
        }


        String random = user.getSalt();
        //hash encrypt
        String resultPassword = SecurityUtil.md5Hash(password, random, 3).toString();


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
    public UserReturnCode register(String name, String password) {
        User user = null;
        log.debug("{}registering.....", name);

        //check the name whether registered
        if (UserRegexUtil.isMobile(name)) {
            if (userMapper.selectIdByPhone(name) != null) {
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setPhoneNum(name);
            log.debug("The phone ({}) registering", name);

        } else if (UserRegexUtil.isEmail(name)) {
            if (userMapper.selectIdByEmail(name) != null) {
                return UserReturnCode.ACCOUNT_EXIST;
            }
            user = new User();
            user.setEmail(name);
            log.debug("The email({}) registering", name);
        }
        log.debug(user.toString());


        //encrypt password by salt
        String random = new Random().nextInt(999999) + "";
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
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
