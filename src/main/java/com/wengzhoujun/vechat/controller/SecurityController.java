package com.wengzhoujun.vechat.controller;

import cn.hutool.http.HttpUtil;
import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.enums.ErrorCode;
import com.wengzhoujun.vechat.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Exrickx
 */
@Slf4j
@RestController
@Api(tags = "Security相关接口")
@RequestMapping("/user/common")
public class SecurityController {

    @GetMapping(value = "/needLogin")
    @ApiOperation(value = "没有登录")
    public Result needLogin() {
        return ResponseUtil.error(ErrorCode.NEED_SIGN_IN);
    }

}
