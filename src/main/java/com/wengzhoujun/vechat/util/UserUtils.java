package com.wengzhoujun.vechat.util;


import com.wengzhoujun.vechat.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created on 2019/6/19.
 *
 * @author WengZhoujun
 */
public class UserUtils {

    public static User initUser(String phone, HttpServletRequest request){
        User user = new User(phone, phone.substring(phone.length() - 4, phone.length()), User.StatusEnum.NORMAL.getCode(), IpUtils.getRealIp(request));
        return user;
    }
}
