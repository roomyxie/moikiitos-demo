package com.moikiitos.service.user;

import com.moikiitos.common.enums.RelationReturnCode;
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
    public RelationReturnCode follow(Long userId, Long followeeId) {

        //check whether followed
        List<Relation> users = relationMapper.selectAllByFollowerIdAndFolloweeId(userId, followeeId);
        if (!users.isEmpty()) {
            return RelationReturnCode.FOLLOW_FAIL_ALREADY;
        }

        Relation relation = new Relation();
        relation.setFollowerid(userId);
        relation.setFolloweeid(followeeId);
        //insert data
        int result = relationMapper.insert(relation);
        if (result <= 0) {
            return RelationReturnCode.FOLLOW_FAIL;
        }

        return RelationReturnCode.FOLLOW_SUCCESS;
    }

    @Override
    public RelationReturnCode unfollow(Long followerId, Long followeeId) {
        //check unfollow
        int result = relationMapper.unfollow(followerId, followeeId);
        if (result <= 0) {
            return RelationReturnCode.UN_FOLLOW_FAIL;
        }

        return RelationReturnCode.UN_FOLLOW_SUCCESS;
    }


    @Override
    public List<User> listFollower(Long followerId) {
        List<Long> usedIds = relationMapper.selectAllByFollowerId(followerId);
        if (usedIds != null && !usedIds.isEmpty()) {
            return userMapper.selectUsersInfo(usedIds);
        }

        return null;
    }

    @Override
    public List<User> listFollowee(Long followerId) {
        List<Long> usedIds = relationMapper.selectAllByFolloweeId(followerId);
        if (usedIds != null && !usedIds.isEmpty()) {
            return userMapper.selectUsersInfo(usedIds);
        }
        return null;
    }
}
