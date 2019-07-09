package com.wengzhoujun.vechat.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created on 2019/6/19.
 *
 * @author WengZhoujun
 */
public class IpUtils {
    public IpUtils() {
    }

    public static String getRealIp(ServletRequest req) {
        return getRealIp((HttpServletRequest) req);
    }

    public static String getRealIp(HttpServletRequest req) {
        String xff = req.getHeader("x-forwarded-for");
        if (xff == null || xff.isEmpty()) {
            xff = req.getHeader("X-Real-IP");
        }

        if (xff == null || xff.isEmpty()) {
            xff = req.getRemoteAddr();
        }

        if (xff != null && xff.contains(",")) {
            xff = xff.split(",")[0].trim();
            if ((xff == null || xff.isEmpty()) && xff.split(",").length > 1) {
                xff = xff.split(",")[1].trim();
            }
        }

        return xff;
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException var1) {
            var1.printStackTrace();
            return null;
        }
    }

    public static String getCustomHostName() {
        String hostName = getHostName();
        return hostName == null ? "_dev" : "_" + hostName;
    }

}
