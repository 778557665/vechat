package com.wengzhoujun.vechat.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2019/7/8.
 *
 * @author WengZhoujun
 */
public class CommonUtil {

    public static boolean isAjax(HttpServletRequest request) {
        boolean isAjaxRequest = false;
        if (!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
