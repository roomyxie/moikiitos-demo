package com.moikiitos.service.user;

import com.moikiitos.dao.model.User;

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
    User queryUser(String queryString);

    /**
     * function description
     *
     * @author xiekuan
     * @Description queryUserById
     * @date 02/24/29
     * @param:
     * @return:
     */
    User queryUserById(long userId);
}
