package com.moikiitos.dao.mapper;

import com.moikiitos.dao.model.Relation;
import com.moikiitos.dao.model.RelationExample;

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

    /***
     * cancel followee
     * @param userId
     * @param followeeId
     * @return
     */
    int unfollow(@Param("followerId") long userId, @Param("followeeId") long followeeId);

    /***
     * select all by follower id
     * @param followerId
     * @return
     */
    List<Long> selectAllByFollowerId(@Param("followerId") long followerId);

    /***
     * select all by followee id
     * @param followeeId
     * @return
     */
    List<Long> selectAllByFolloweeId(@Param("followeeId") long followeeId);

}