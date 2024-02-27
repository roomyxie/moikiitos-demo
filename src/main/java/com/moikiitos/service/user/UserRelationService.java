package com.moikiitos.service.user;

import com.moikiitos.common.enums.RelationReturnCode;
import com.moikiitos.dao.model.User;

import java.util.List;

public interface UserRelationService {
    /**
     * function description
     *
     * @author xiekuan
     * @Description follow
     * @date 02/24/24
     * @param:
     * @return:
     */
    public RelationReturnCode follow(Long userId, Long followeeId);

    /**
     * function description
     *
     * @author xiekuan
     * @Description unfollow
     * @date 02/24/24
     * @param:
     * @return:
     */
    public RelationReturnCode unfollow(Long userId, Long followeeId);


    /**
     * function description
     *
     * @author xiekuan
     * @Description follow
     * @date 02/24/24
     * @param:
     * @return:
     */
    public List<User> listFollower(Long userId);

    /**
     * function description
     *
     * @author xiekuan
     * @Description list followee
     * @date 02/24/24
     * @param:
     * @return:
     */
    public List<User> listFollowee(Long userId);
}
