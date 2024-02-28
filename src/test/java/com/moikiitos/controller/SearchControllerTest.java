package com.moikiitos.controller;

import com.moikiitos.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class SearchControllerTest {

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
    UserService userService;


    @Test
    public void queryUserControllerTest() {
        User res = userService.queryUser("323456789");
        System.out.println(res.toString());
    }

    @Test
    public void queryUserServiceTest() throws Exception {


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/search/searchUser")
                        .requestAttr("userId", 3L)
                        .param("searchStr", "323456789"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        log.info("\nresult:signature status=[{}]，\nresult = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());


    }
}

