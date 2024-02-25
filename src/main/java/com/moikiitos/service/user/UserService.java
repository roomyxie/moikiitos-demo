package com.moikiitos.service.user;

import com.moikiitos.dao.model.User;

import java.util.List;

public interface UserService {
    /**
     * function description
     *
     * @author xiekuan
     * @Description queryUser
     * @date 02/24/24
     * @param:
     * @return:
     */
    List<User> queryUser(String queryString);
}
