package com.moikiitos.controller.login;

import com.alibaba.fastjson.JSON;
import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.common.enums.RelationReturnCode;
import com.moikiitos.common.enums.ReturnCode;
import com.moikiitos.controller.vo.BlogFollowReq;
import com.moikiitos.controller.vo.BlogUnFollowReq;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import com.moikiitos.service.user.UserRelationService;
import com.moikiitos.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/relation")
@Slf4j
public class UserRelationController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRelationService userRelationService;

    @Autowired
    UserService userService;


    /**
     * function description
     *
     * @author xiekuan
     * @Description follow Id
     * @date 2/26/24
     * @param:
     * @return:
     */
    @PrintUrlAnno
    @PostMapping("/follow")
    public BaseResult follow(@RequestBody BlogFollowReq req) {
        Long currentUserId = req.getUserId();
        if (currentUserId == null) {
            return new WebResult(RelationReturnCode.FOLLOW_FAIL.getCode(), RelationReturnCode.FOLLOW_FAIL.getMessage());
        }
        Long followeeId = req.getFolloweeId();
        if (followeeId == null) {
            return new WebResult(RelationReturnCode.FOLLOW_FAIL.getCode(), RelationReturnCode.FOLLOW_FAIL.getMessage());
        }

        if (currentUserId.equals(followeeId)) {
            return new WebResult(RelationReturnCode.FOLLOW_SAME_FAIL.getCode(), RelationReturnCode.FOLLOW_SAME_FAIL.getMessage());
        }
        log.debug("follow req: " + JSON.toJSON(req));


        ReturnCode returnCode = userRelationService.follow(currentUserId, followeeId);
        return new WebResult(returnCode.getCode(), returnCode.getMessage());
    }

    /**
     * function description
     *
     * @author xiekuan
     * @Description unfollow
     * @date 02/26/24
     * @param:
     * @return:
     */

    @PrintUrlAnno
    @PostMapping("/unfollow")
    public BaseResult unfollow(@RequestBody BlogUnFollowReq req) {
        Long currentUserId = req.getUserId();
        if (currentUserId == null) {
            return new WebResult(RelationReturnCode.UN_FOLLOW_FAIL.getCode(), RelationReturnCode.UN_FOLLOW_FAIL.getMessage());
        }
        Long followerId = req.getFollowerId();
        if (followerId == null) {
            return new WebResult(RelationReturnCode.UN_FOLLOW_FAIL.getCode(), RelationReturnCode.UN_FOLLOW_FAIL.getMessage());
        }

        if (currentUserId.equals(followerId)) {
            return new WebResult(RelationReturnCode.UN_FOLLOW_SAME_FAIL.getCode(), RelationReturnCode.UN_FOLLOW_SAME_FAIL.getMessage());
        }
        log.debug("unfollow req: " + JSON.toJSON(req));


        ReturnCode returnCode = userRelationService.unfollow(currentUserId, followerId);
        return new WebResult(returnCode.getCode(), returnCode.getMessage());


    }

    /**
     * function description
     *
     * @author xiekuan
     * @Description get follower list
     * @date 02/26/24
     * @param:
     * @return:
     */
    @PrintUrlAnno
    @GetMapping("/follower/list")
    public BaseResult listFollower(@RequestParam Long userId) {
        if (userId == null) {
            return new WebResult(WebResult.RESULT_FAIL, "get followee list failed, userId empty");
        }
        log.debug("listFollower userId: " + userId);

        List<User> users = userRelationService.listFollower(userId);

        return new WebResult(WebResult.RESULT_SUCCESS, "get follower list success", users);
    }

    /**
     * function description
     *
     * @author xiekuan
     * @Description get list of followee
     * @date 2/26/24
     * @param:
     * @return:
     */
    @PrintUrlAnno
    @GetMapping("/followee/list")
    public BaseResult listFollowee(@RequestParam Long userId) {
        log.debug("listFollowee userId: " + userId);

        if (userId == null) {
            return new WebResult(WebResult.RESULT_FAIL, "get followee list failed, userId empty");
        }

        List<User> users = userRelationService.listFollowee(userId);

        return new WebResult(WebResult.RESULT_SUCCESS, "get followee list success", users);
    }
}
