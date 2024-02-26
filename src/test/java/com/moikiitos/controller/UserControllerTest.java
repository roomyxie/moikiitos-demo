package com.moikiitos.controller;

import com.moikiitos.consts.UserReturnCode;
import com.moikiitos.service.login.LoginService;
import com.moikiitos.service.result.BaseResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
        UserReturnCode res = loginService.register("12345678", "469224359@qq.com", "nihao");
        System.out.println(res.getMessage());
    }

    @Test
    public void registerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/user/logout")).andDo(print());
        // .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}

