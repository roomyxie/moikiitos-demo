package com.moikiitos.service.feed;

import com.moikiitos.common.Constants;
import com.moikiitos.dao.mapper.BlogMapper;
import com.moikiitos.dao.mapper.UserMapper;
import com.moikiitos.dao.model.Blog;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.dto.BlogInfoDto;
import com.moikiitos.service.user.UserRelationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.management.relation.RelationService;
import java.util.*;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRelationService userRelationService;

    @Override
    public List<BlogInfoDto> queryBlog(String type, long currentUserId, long searchUserId, int page, int count) {
        List<BlogInfoDto> blogInfoDtos = new LinkedList<>();
        log.debug("type ={}, searchUserId = {}, searchUserId = {},page={},count={}", type, currentUserId, searchUserId, page, count);
        List<Blog> blogs = blogMapper.selectByTypeAndUserId(type, searchUserId, (page - 1) * count, count);
        log.debug("blogs = " + blogs);

        List<User> users = userRelationService.listFollower(currentUserId);

        List<Long> userIds = null;
        if (!CollectionUtils.isEmpty(users)) {
            userIds = convert2UserIds(users);
        }
        for (Blog blog : blogs) {

            User user = userMapper.selectUserInfo(blog.getUserId());
            BlogInfoDto dto = BlogInfoDto.builder()
                    .userId(user.getUserId())
                    .blogId(blog.getBlogId())
                    .nickName(user.getNickName())
                    .realName(user.getRealName())
                    .publishTime(blog.getPublishTime())
                    .content(blog.getContent())
                    .build();
            if (!CollectionUtils.isEmpty(userIds) && userIds.contains(user.getUserId())) {
                dto.setEnableFollow(false);
            }
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

    private List<Long> convert2UserIds(List<User> users) {
        List<Long> userIds = new ArrayList<>();
        for (User user : users) {
            userIds.add(user.getUserId());
        }
        return userIds;
    }
}
