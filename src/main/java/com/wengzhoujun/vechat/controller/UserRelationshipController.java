package com.wengzhoujun.vechat.controller;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.entity.User;
import com.wengzhoujun.vechat.entity.UserRelationship;
import com.wengzhoujun.vechat.enums.ErrorCode;
import com.wengzhoujun.vechat.service.UserRelationshipService;
import com.wengzhoujun.vechat.service.UserService;
import com.wengzhoujun.vechat.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2019/7/11.
 *
 * @author WengZhoujun
 */
@Api(tags = "用户关系相关接口")
@RestController
@RequestMapping("/userRelationship")
public class UserRelationshipController {

    @Autowired
    private UserRelationshipService userRelationshipService;

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户好友列表")
    @GetMapping("/getFriendList")
    public Result getFriendList(Long userId) {
        return ResponseUtil.ok(userRelationshipService.findAllByUserId(userId));
    }

    @ApiOperation("加好友")
    @PostMapping("/addFriend")
    public Result addFriend(Long userId, String keyword) {
        User user = userService.findByKeyword(keyword);
        if (user == null) {
            return ResponseUtil.error(ErrorCode.DID_NOT_QUERY_THE_USER);
        }
        UserRelationship userRelationship = new UserRelationship(userId, user.getId(), UserRelationship.StatusEnum.NORMAL.getCode());
        userRelationshipService.save(userRelationship);
        return ResponseUtil.ok();
    }
}
