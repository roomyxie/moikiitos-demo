package com.moikiitos.service.user;

import com.moikiitos.consts.RelationReturnCode;
import com.moikiitos.dao.mapper.RelationMapper;
import com.moikiitos.dao.mapper.UserMapper;
import com.moikiitos.dao.model.Relation;
import com.moikiitos.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    RelationMapper relationMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public RelationReturnCode follow(long userId, long followeeId) {
        Relation relation = new Relation();
        relation.setFollowerid(userId);
        relation.setFolloweeid(followeeId);
        Integer result = relationMapper.insert(relation);
        if (result == null) {
            return RelationReturnCode.FOLLOW_FAIL;
        }

        return RelationReturnCode.FOLLOW_SUCCESS;
    }

    @Override
    public RelationReturnCode unfollow(long userId, long followeeId) {
        Integer result = relationMapper.unfollow(userId, followeeId);
        if (result == null) {
            return RelationReturnCode.UN_FOLLOW_FAIL;
        }

        return RelationReturnCode.UN_FOLLOW_SUCCESS;
    }

    @Override
    public RelationReturnCode removeFollower(long userId, long followerId) {
        Integer result = relationMapper.unfollow(followerId, userId);
        if (result == null) {
            return RelationReturnCode.REMOVE_FOLLOWER_FAIL;
        }

        return RelationReturnCode.REMOVE_FOLLOWER_SUCCESS;
    }

    @Override
    public List<User> listFollower(long followerId) {
        List<Long> usedIds = relationMapper.selectAllByFollowerId(followerId);
        if (usedIds != null) {
            return userMapper.selectUsersInfo(usedIds);
        }
        return null;
    }

    @Override
    public List<User> listFollowee(long followerId) {
        List<Long> usedIds = relationMapper.selectAllByFollowerId(followerId);
        if (usedIds != null) {
            return userMapper.selectUsersInfo(usedIds);
        }
        return null;
    }
}
