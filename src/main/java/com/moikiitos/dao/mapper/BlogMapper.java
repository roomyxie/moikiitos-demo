package com.moikiitos.dao.mapper;

import com.moikiitos.dao.model.Blog;
import com.moikiitos.dao.model.BlogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Long blogId);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Long blogId);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> selectByTypeAndUserId(@Param("type") String type,
                                     @Param("userId") long userId,
                                     @Param("start") int start,
                                     @Param("count") int count);
}