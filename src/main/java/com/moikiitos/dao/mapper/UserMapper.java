package com.moikiitos.dao.mapper;

import com.moikiitos.dao.model.User;
import com.moikiitos.dao.model.UserExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    Long selectIdByEmail(String email);

    User selectByRealName(String name);

    User selectByEmail(String email);

    Integer updateInfoByPrimaryKey(Map<String, Object> map);

    User selectUserInfo(Long userId);

    List<User> selectUsersInfo(List<Long> userIds);
}