package com.moikiitos.service.user;

import com.moikiitos.dao.mapper.UserMapper;
import com.moikiitos.dao.model.User;
import com.moikiitos.util.UserRegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUser(String queryString) {

        boolean isEmail = UserRegexUtil.isEmail(queryString);
        User user = null;
        if (isEmail) {
            user = userMapper.selectByEmail(queryString);
        } else {
            user = userMapper.selectByRealName(queryString);
        }

        return user;
    }
}
