package com.moikiitos.service.feed;

import com.moikiitos.service.dto.BlogInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    @Override
    public List<BlogInfoDto> queryBlog(String type, long userId, int page, int count) {
        return null;
    }

    @Override
    public BlogInfoDto queryBlog(Long blogId, Long userId) {
        return null;
    }

    @Override
    public void submit(MultipartHttpServletRequest multiRequest) {

    }

    @Override
    public long collect(long blogId, long userId) {
        return 0;
    }

    @Override
    public long repost(long blogId, long userId, String content) {
        return 0;
    }

    @Override
    public long comment(long blogId, long userId, String content) {
        return 0;
    }

    @Override
    public long like(long blogId, long userId, String type) {
        return 0;
    }
}
