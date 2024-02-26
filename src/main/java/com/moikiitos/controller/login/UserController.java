package com.moikiitos.controller.login;

import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.consts.UserReturnCode;
import com.moikiitos.service.login.LoginService;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResult login(@RequestBody Map<String, Object> requestMap) {

        BaseResult result = null;

        String loginName = (String) requestMap.get("loginName");
        String loginNameType = (String) requestMap.get("loginNameType");
        String loginPassword = (String) requestMap.get("loginPassword");


        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginNameType) || StringUtils.isEmpty(loginPassword)) {
            result = new WebResult(UserReturnCode.ERROR_PARAM.getCode(), UserReturnCode.ERROR_PARAM.getMessage());
            return result;
        }

        log.debug("loginName = " + loginName + "  loginNameType = " + loginNameType + "  loginPassword = " + loginPassword);
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
    public BaseResult register(@RequestBody Map<String, Object> requestMap) {

        BaseResult result = null;

        String registerName = (String) requestMap.get("registerName");
        String email = (String) requestMap.get("email");
        String registerPassword = (String) requestMap.get("registerPassword");

        log.debug("registerName = " + registerName + "  registerPassword = " + registerPassword);

        //do register
        UserReturnCode returnCode = loginService.register(registerName, email, registerPassword);
        result = new WebResult(returnCode.getCode(), returnCode.getMessage());
        return result;
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
    @PostMapping("/logout")
    public BaseResult logout() {
        System.out.println("logout");
        BaseResult result = null;
        UserReturnCode code = loginService.logout();
        result = new WebResult(code.getCode(), code.getMessage());
        return result;
    }

}
