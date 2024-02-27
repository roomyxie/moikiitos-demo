package com.moikiitos.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.moikiitos.controller.vo.BlogPostReq;
import com.moikiitos.service.feed.BlogService;
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
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class BlogControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Autowired
    BlogService blogService;


    @Test
    public void postTest() throws Exception {
       // blogService.submit("test", 5L);

        BlogPostReq req = new BlogPostReq();
        req.setUserId(2L);
        req.setContent("test1");
        String reqData = JSON.toJSONString(req);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                .post("/blog/post")
                                .requestAttr("userId", 2L)
                                .contentType(MediaType.APPLICATION_JSON_UTF8).content(reqData)
                ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(result.getResponse());
    }
}
