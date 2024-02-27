package com.moikiitos.controller.blog;

import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.common.enums.BlogReturnCode;
import com.moikiitos.controller.vo.BlogPostReq;
import com.moikiitos.service.dto.BlogInfoDto;
import com.moikiitos.service.feed.BlogService;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/blog")
@RestController
@Slf4j
public class BlogController {

    @Autowired
    BlogService blogService;

    @PrintUrlAnno
    @PostMapping("/list")
    public BaseResult queryBlog(@RequestBody Map<String, Object> map) {

        String type = (String) map.get("type");
        int page = (Integer) map.get("page");
        int count = (Integer) map.get("count");

        log.debug("queryBlog....");
        // log.debug("userId = " + request.getHeader("userId"));
        List<BlogInfoDto> blogInfoDtos = blogService.queryBlog(type, 1L, page, count);

        return new WebResult(BlogReturnCode.BLOG_QUERY_SUCCESS.getCode(),
                BlogReturnCode.BLOG_QUERY_SUCCESS.getMessage(), blogInfoDtos);
    }

    @PrintUrlAnno
    @PostMapping("/post")
    public BaseResult postBlog(@RequestBody BlogPostReq req) {

        log.debug("postBlog....");
        // log.debug("userId = " + request.getHeader("userId"));
        blogService.submit(req.getContent(), req.getUserId());

        return new WebResult(BlogReturnCode.BLOG_SUBMIT_SUCCESS.getCode(),
                BlogReturnCode.BLOG_SUBMIT_SUCCESS.getMessage());
    }

}
