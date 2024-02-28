package com.moikiitos.service.feed;

import com.moikiitos.common.Constants;
import com.moikiitos.dao.mapper.BlogMapper;
import com.moikiitos.dao.mapper.UserMapper;
import com.moikiitos.dao.model.Blog;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.dto.BlogInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<BlogInfoDto> queryBlog(String type, long userId, int page, int count) {
        List<BlogInfoDto> blogInfoDtos = new LinkedList<>();
        log.debug("type ={},userId = {},page={},count={}", type, userId, page, count);
        List<Blog> blogs = blogMapper.selectByTypeAndUserId(type, userId, (page - 1) * count, count);
        log.debug("blogs = " + blogs);
        for (Blog blog : blogs) {

            User user = userMapper.selectUserInfo(blog.getUserId());
            BlogInfoDto dto = BlogInfoDto.builder()
                    .userId(user.getUserId())
                    .blogId(blog.getBlogId())
                    .nickName(user.getNickName())
                    .publishTime(blog.getPublishTime())
                    .content(blog.getContent())
                    .build();
            blogInfoDtos.add(dto);

        }
        return blogInfoDtos;

    }


    @Override
    public void submit(String content, Long userId) {

        //写入数据库
        Blog blog = new Blog();
        blog.setContent(content);
        blog.setCreateTime(new Date());
        blog.setPublishTime(new Date());
        blog.setType(Constants.BLOG_PUBLIC);
        blog.setUserId(userId);
        long blogId = blogMapper.insert(blog);
        log.debug("blogId = {}", blogId);
    }
}
