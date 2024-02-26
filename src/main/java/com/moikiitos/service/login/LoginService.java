package com.moikiitos.service.login;

import com.moikiitos.service.result.BaseResult;
import com.moikiitos.consts.UserReturnCode;

/**
 * function description
 *
 * @author xiekuan
 * @Description user login interface
 * @date 2/24/24
 */
public interface LoginService {


    /**
     * function description
     *
     * @author xiekuan
     * @Description login by email or phone number
     * @date 2/24/24
     * @param:
     * @return:
     */
    BaseResult login(String name, String password);

    /**
     * function description
     *
     * @author xiekuan
     * @Description logout
     * @date 2/24/24
     * @param:
     * @return:
     */
    UserReturnCode logout();

    /**
     * function description
     *
     * @author xiekuan
     * @Description register by phone or email
     * @date 2/24/24
     * @param:
     * @return:
     */
    UserReturnCode register(String name, String email, String password);
}
