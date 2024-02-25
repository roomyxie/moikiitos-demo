package com.moikiitos;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class LoginControllerTest {

    @Before
    public void setupMockMvc() throws Exception {
       // mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void logout() {
    }

    @Autowired
    LoginService loginService;


    @Test
    public void query1() {
        BaseResult res = loginService.login("12345678", "nihao");
        System.out.println(res.getMessage());
    }
}

