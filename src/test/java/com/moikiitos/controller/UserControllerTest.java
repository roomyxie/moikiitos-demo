package com.moikiitos.controller;

import com.alibaba.fastjson.JSON;
import com.moikiitos.common.enums.UserReturnCode;
import com.moikiitos.controller.vo.BlogPostReq;
import com.moikiitos.controller.vo.UserRegisterReq;
import com.moikiitos.service.login.LoginService;
import com.moikiitos.service.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void logout() {
    }

    @Autowired
    LoginService loginService;


    @Test
    public void registerServiceTest() {
        UserReturnCode res = loginService.register("423456789", "4692243519@qq.com", "nihao");
        System.out.println(res.getMessage());
    }

    @Test
    public void registerTest() throws Exception {
        UserRegisterReq req = new UserRegisterReq();
        req.setName("张三");
        req.setEmail("456333@qq.com");
        req.setPassword("123456");
        String reqData = JSON.toJSONString(req);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/user/register")
                .requestAttr("userId", 2L)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(reqData)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(result.getResponse());
    }

    @Test
    public void loginServiceTest() {
        BaseResult res = loginService.login("456333@qq.com", "123456");
        System.out.println(res.getMessage());
    }

    @Test
    public void loginControllerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/user/login")).andDo(print());
        // .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}

