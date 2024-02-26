package com.moikiitos.controller.feed;

import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.consts.BlogReturnCode;
import com.moikiitos.service.dto.BlogInfoDto;
import com.moikiitos.service.feed.FeedService;
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

@RequestMapping("/feed")
@RestController
@Slf4j
public class FeedController {

    @Autowired
    FeedService feedService;

    @PrintUrlAnno
    @PostMapping("/list")
    public BaseResult queryBlog(@RequestBody Map<String, Object> map) {

        String type = (String) map.get("type");
        int page = (Integer) map.get("page");
        int count = (Integer) map.get("count");

        log.debug("queryBlog....");
        // log.debug("userId = " + request.getHeader("userId"));
        List<BlogInfoDto> blogInfoDtos = feedService.queryBlog(type, 1L, page, count);

        return new WebResult(BlogReturnCode.BLOG_QUERY_SUCCESS.getCode(),
                BlogReturnCode.BLOG_QUERY_SUCCESS.getMessage(), blogInfoDtos);
    }

}
