package com.moikiitos.mapper;

import com.moikiitos.model.Relation;
import com.moikiitos.model.RelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationMapper {
    long countByExample(RelationExample example);

    int deleteByExample(RelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Relation record);

    int insertSelective(Relation record);

    List<Relation> selectByExample(RelationExample example);

    Relation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Relation record, @Param("example") RelationExample example);

    int updateByExample(@Param("record") Relation record, @Param("example") RelationExample example);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);
}