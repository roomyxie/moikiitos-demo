package com.moikiitos.controller.login;

import com.alibaba.fastjson.JSON;
import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.common.enums.UserReturnCode;
import com.moikiitos.controller.vo.UserLoginReq;
import com.moikiitos.controller.vo.UserRegisterReq;
import com.moikiitos.service.login.LoginService;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    LoginService loginService;

    /**
     * function description
     *
     * @author xiekuan
     * @Description login
     * @date 2/25/24
     * @param:
     * @return:
     */
    @PrintUrlAnno
    @PostMapping("/login")
    public BaseResult login(@RequestBody UserLoginReq req) {

        log.debug("login = " + JSON.toJSON(req));

        BaseResult result = null;

        String loginName = req.getName();
        String loginPassword = req.getPassword();


        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPassword)) {
            result = new WebResult(UserReturnCode.ERROR_PARAM.getCode(), UserReturnCode.ERROR_PARAM.getMessage());
            return result;
        }

        //do login
        return loginService.login(loginName, loginPassword);
    }

    /**
     * function description
     *
     * @author xiekuan
     * @Description register
     * @date 2/25/24
     * @param:
     * @return:
     */
    @PrintUrlAnno
    @PostMapping("/register")
    public BaseResult register(@RequestBody UserRegisterReq req) {

        log.debug("register req: " + JSON.toJSON(req));

        String registerName = req.getName();
        String email = req.getEmail();
        String registerPassword = req.getPassword();

        //do register
        UserReturnCode returnCode = loginService.register(registerName, email, registerPassword);
        return new WebResult(returnCode.getCode(), returnCode.getMessage());
    }

    /**
     * function description
     *
     * @author xiekuan
     * @Description logout
     * @date 2/24/24
     * @param:
     * @return:
     */
    @PrintUrlAnno
    @GetMapping("/logout")
    public BaseResult logout() {
        BaseResult result = null;
        UserReturnCode code = loginService.logout();
        result = new WebResult(code.getCode(), code.getMessage());
        return result;
    }

}
