package com.moikiitos.controller.user;

import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.service.login.UserService;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import com.moikiitos.util.UserReturnCode;
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
    UserService userService;

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
        return userService.login(loginName, loginPassword);
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
        String registerNameType = (String) requestMap.get("registerNameType");
        String verificationCode = (String) requestMap.get("verificationCode");
        String registerPassword = (String) requestMap.get("registerPassword");

        log.debug("registerName = " + registerName + "  registerNameType = " + registerNameType + "  verificationCode = " + verificationCode + "  registerPassword = " + registerPassword);

        //do register
        UserReturnCode returnCode = userService.register(registerName, registerPassword);
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
        BaseResult result = null;
        UserReturnCode code = userService.logout();
        result = new WebResult(code.getCode(), code.getMessage());
        return result;
    }

}
