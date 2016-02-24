package com.xstar.javacommons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xstar on 2016/2/24.
 */
public class DateUtils {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    static String datePatten = "yyyy-MM-dd HH:mm:ss";
    static String timePatten = "HH:mm:ss";
    static String dayPatten = "yyyy-MM-dd";

    public static String format(String pattern, Date date) {
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }

    public static String formatDate(Date date) {
        return format(datePatten, date);
    }

    public static String formatDay(Date date) {
        return format(dayPatten, date);
    }

    public static String formatTime(Date date) {
        return format(timePatten, date);
    }
}
