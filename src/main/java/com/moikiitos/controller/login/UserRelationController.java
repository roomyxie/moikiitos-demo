package com.moikiitos.controller.login;

import com.moikiitos.common.PrintUrlAnno;
import com.moikiitos.common.enums.RelationReturnCode;
import com.moikiitos.common.enums.ReturnCode;
import com.moikiitos.dao.model.User;
import com.moikiitos.service.result.BaseResult;
import com.moikiitos.service.result.WebResult;
import com.moikiitos.service.user.UserRelationService;
import com.moikiitos.service.user.UserService;
import com.moikiitos.util.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/relation")
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
    public BaseResult follow(Long followeeId) {
        if (followeeId == null) {
            return new WebResult(RelationReturnCode.ERROR_PARAM.getCode(), RelationReturnCode.ERROR_PARAM.getMessage());
        }
        Long currentUserId = UserUtil.getUserId(request);
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
    public BaseResult unfollow(Long followeeId) {
        if (followeeId == null) {
            return new WebResult(RelationReturnCode.ERROR_PARAM.getCode(), RelationReturnCode.ERROR_PARAM.getMessage());
        }

        Long currentUserId = UserUtil.getUserId(request);
        ReturnCode returnCode = userRelationService.unfollow(currentUserId, followeeId);
        return new WebResult(WebResult.RESULT_SUCCESS, returnCode.getMessage());


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
        //Long currentUserId = UserUtil.getUserId(request);
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
        // Long currentUserId = UserUtil.getUserId(request);
        List<User> users = userRelationService.listFollowee(userId);

        return new WebResult(WebResult.RESULT_SUCCESS, "get followee list success", users);
    }
}
