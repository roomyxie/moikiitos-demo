package com.moikiitos.service.user;

import com.moikiitos.consts.RelationReturnCode;
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
    public RelationReturnCode follow(long userId, long folleeId);

    /**
     * function description
     *
     * @author xiekuan
     * @Description unfollow
     * @date 02/24/24
     * @param:
     * @return:
     */
    public RelationReturnCode unfollow(long userId, long folleeId);


    /**
     * function description
     *
     * @author xiekuan
     * @Description remove follow
     * @date 02/24/24
     * @param:
     * @return:
     */
    public RelationReturnCode removeFollower(long userId, long follerId);

    /**
     * function description
     *
     * @author xiekuan
     * @Description follow
     * @date 02/24/24
     * @param:
     * @return:
     */
    public List<User> listFollower(long userId);

    /**
     * function description
     *
     * @author xiekuan
     * @Description list followee
     * @date 02/24/24
     * @param:
     * @return:
     */
    public List<User> listFollowee(long userId);
}
