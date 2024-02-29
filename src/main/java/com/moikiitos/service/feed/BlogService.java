package com.moikiitos.service.feed;

import com.moikiitos.service.dto.BlogInfoDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BlogService {
    List<BlogInfoDto> queryBlog(String type, long currentUserId, long searchUserId, int page, int count);

    void submit(String content, Long userId);
}
