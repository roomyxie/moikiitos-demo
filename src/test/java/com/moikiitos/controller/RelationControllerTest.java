package com.moikiitos.controller;

import com.moikiitos.common.enums.RelationReturnCode;
import com.moikiitos.service.user.UserRelationService;
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

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class RelationControllerTest {

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
    UserRelationService userRelationService;


    @Test
    public void followTest() {
        RelationReturnCode res = userRelationService.follow(2L, 5L);
        System.out.println(res.getMessage());
    }

    @Test
    public void unfollowTest() {
        RelationReturnCode res = userRelationService.unfollow(2L, 3L);
        System.out.println(res.getMessage());
    }

    @Test
    public void listFollowerTest() {
        List<User> res = userRelationService.listFollower(2L);
        System.out.println(res);
    }

    @Test
    public void listFollowerControllerTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/relation/follower/list")
                        .requestAttr("userId", 2L)
                        .param("userId", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        log.info("\nresult:signature status=[{}]，\nresult = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());
    }

    @Test
    public void listFolloweeTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/relation/followee/list")
                        .requestAttr("userId", 2L)
                        .param("userId", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        log.info("\nresult:signature status=[{}]，\nresult = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());
    }

}

