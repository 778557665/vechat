package com.wengzhoujun.vechat.constants;


/**
 * 常量
 * @author Exrickx
 */
public interface CommonConstant {

    /**
     * 用户默认头像
     */
    String USER_DEFAULT_AVATAR = "https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png";

    /**
     * 用户正常状态
     */
    Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户禁用状态
     */
    Integer USER_STATUS_LOCK = -1;

    /**
     * 限流标识
     */
    String LIMIT_ALL = "VECHAT_LIMIT_ALL";

    String SSO_ACCESS_URL = "/user/common/needLogin";

}
