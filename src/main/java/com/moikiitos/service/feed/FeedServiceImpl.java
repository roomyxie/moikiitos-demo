package com.moikiitos.service.feed;

import com.moikiitos.dao.mapper.BlogMapper;
import com.moikiitos.dao.mapper.UserMapper;
import com.moikiitos.dao.model.Blog;
import com.moikiitos.dao.model.BlogExample;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.dto.BlogInfoDto;
import com.moikiitos.util.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<BlogInfoDto> queryBlog(String type, Long userId, int page, int count) {
        List<BlogInfoDto> blogInfoDtos = new LinkedList<>();
        log.debug("type ={},userId = {},page={},count={}", type, userId, page, count);
        List<Blog> blogs = blogMapper.selectByTypeAndUserId(type, userId, (page - 1) * count, count);
        log.debug("blogs = " + blogs);
        for (Blog blog : blogs) {

            User user = userMapper.selectUserInfo(blog.getUserId());
            BlogInfoDto dto = BlogInfoDto.builder()
                    .userId(userId)
                    .nickName(user.getNickName())
                    .publishTime(blog.getPublishTime())
                    .content(blog.getContent())
                    .build();
            blogInfoDtos.add(dto);

        }
        return blogInfoDtos;

    }


    @Override
    public void submit(MultipartHttpServletRequest multiRequest) {

        //博客内容
        String blogText = multiRequest.getParameter("blog-text");
        //博客类型
        String blogType = multiRequest.getParameter("blog-type");

        //写入数据库
        Blog blog = new Blog();
        blog.setContent(blogText);
        //  blog.setCreateTime(new Date());
        //  blog.setPublishTime(blog.getPublishTime());
        blog.setType(blogType);
        blog.setIsOriginal("true");
        blog.setUserId(UserUtil.getUserId(request));
        long blogId = blogMapper.insert(blog);

        log.debug("blogId = {}", blogId);
    }
}
