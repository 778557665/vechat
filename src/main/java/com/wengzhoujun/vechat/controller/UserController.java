package com.wengzhoujun.vechat.controller;

import com.wengzhoujun.vechat.domain.JwtClaims;
import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.entity.User;
import com.wengzhoujun.vechat.enums.ErrorCode;
import com.wengzhoujun.vechat.service.UserService;
import com.wengzhoujun.vechat.util.JwtTokenUtil;
import com.wengzhoujun.vechat.util.PasswordUtil;
import com.wengzhoujun.vechat.util.ResponseUtil;
import com.wengzhoujun.vechat.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2019/7/8.
 *
 * @author WengZhoujun
 */
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "通过手机号注册")
    @PostMapping("signUpByPhone")
    public Result signUpByPhone(HttpServletRequest request,
                                @RequestParam String phone,
                                @RequestParam String password) throws Exception {
        User user = UserUtils.initUser(phone, request);
        String salt = PasswordUtil.salt(phone);
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encode(salt, password));
        userService.save(user);
        return ResponseUtil.ok();
    }

    @ApiOperation(value = "通过手机号登录")
    @PostMapping("signInByPhone")
    public Result signInByPhone(@RequestParam String phone,
                                @RequestParam String password) throws Exception {
        User user = userService.findByPhone(phone);
        if (null == user) {
            return ResponseUtil.error(ErrorCode.USER_NOT_PRESENCE);
        }
        if (!PasswordUtil.isPasswordValid(user.getPassword(), password, user.getSalt())) {
            return ResponseUtil.error(ErrorCode.WRONG_PHONE_OR_WRONG_PASSWORD);
        }
        String token = JwtTokenUtil.generateToken(new JwtClaims(user.getId(), user.getNickname()));
        return ResponseUtil.ok(token);
    }

}
