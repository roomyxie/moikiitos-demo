package com.moikiitos.controller.blog;

import com.alibaba.fastjson.JSON;
import com.moikiitos.common.Constants;
import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.common.enums.BlogReturnCode;
import com.moikiitos.controller.vo.BlogListReq;
import com.moikiitos.controller.vo.BlogPostReq;
import com.moikiitos.service.dto.BlogInfoDto;
import com.moikiitos.service.feed.BlogService;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
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
    public BaseResult queryBlog(@RequestBody BlogListReq req) {

        String type = req.getType();
        int page = req.getPage();
        int count = req.getCount();
        if (Strings.isEmpty(type)) {
            type = Constants.BLOG_PUBLIC;
        }
        if (page == 0) {
            page = 1;
        }
        if (count == 0) {
            count = Integer.MAX_VALUE;
        }

        log.debug("queryBlog....");
        List<BlogInfoDto> blogInfoDtos = blogService.queryBlog(type, req.getCurrentUserId(), req.getSearchUserId(), page, count);

        return new WebResult(BlogReturnCode.BLOG_QUERY_SUCCESS.getCode(),
                BlogReturnCode.BLOG_QUERY_SUCCESS.getMessage(), blogInfoDtos);
    }

    @PrintUrlAnno
    @PostMapping("/post")
    public BaseResult postBlog(@RequestBody BlogPostReq req) {

        //error check
        if (req.getUserId() == null || Strings.isEmpty(req.getContent())) {
            return new WebResult(BlogReturnCode.ERROR_PARAM.getCode(),
                    BlogReturnCode.ERROR_PARAM.getMessage());
        }
        log.debug("postBlog:" + JSON.toJSON(req));
        blogService.submit(req.getContent(), req.getUserId());

        return new WebResult(BlogReturnCode.BLOG_SUBMIT_SUCCESS.getCode(),
                BlogReturnCode.BLOG_SUBMIT_SUCCESS.getMessage());
    }

}
