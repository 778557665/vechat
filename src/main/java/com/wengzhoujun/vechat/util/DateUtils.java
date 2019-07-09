package com.wengzhoujun.vechat.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2019/6/27.
 *
 * @author WengZhoujun
 */
public class DateUtils {

    public static DateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date strToDate(String dateStr) {
        try {
            return yyyyMMddHHmmss.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到几天后的时间
     *
     * @param day
     * @return
     */
    public static Date getDateAfter(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到几天前的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateBefore(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到多少秒之后的时间
     *
     * @param date
     * @param jwtValidTime
     * @return
     */
    public static Date createBySecond(Date date, int jwtValidTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, jwtValidTime);
        return calendar.getTime();
    }

}
