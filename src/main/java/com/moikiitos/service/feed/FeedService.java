package com.moikiitos.service.feed;

import com.moikiitos.service.dto.BlogInfoDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface FeedService {
    List<BlogInfoDto> queryBlog(String type, long userId, int page, int count);

    BlogInfoDto queryBlog(Long blogId, Long userId);

    void submit(MultipartHttpServletRequest multiRequest);

    long collect(long blogId, long userId);

    long repost(long blogId, long userId, String content);

    long comment(long blogId, long userId, String content);

    long like(long blogId, long userId, String type);
}
