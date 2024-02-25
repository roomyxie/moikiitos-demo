package com.moikiitos.dao.mapper;

import com.moikiitos.dao.model.UserDelete;
import com.moikiitos.dao.model.UserDeleteExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserDeleteMapper {
    long countByExample(UserDeleteExample example);

    int deleteByExample(UserDeleteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDelete record);

    int insertSelective(UserDelete record);

    List<UserDelete> selectByExample(UserDeleteExample example);

    UserDelete selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDelete record, @Param("example") UserDeleteExample example);

    int updateByExample(@Param("record") UserDelete record, @Param("example") UserDeleteExample example);

    int updateByPrimaryKeySelective(UserDelete record);

    int updateByPrimaryKey(UserDelete record);
}