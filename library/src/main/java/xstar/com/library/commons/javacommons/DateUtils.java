package xstar.com.library.commons.javacommons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static Calendar parse(String pattern, String dateStr) throws ParseException {
        simpleDateFormat.applyPattern(pattern);
        Date date = simpleDateFormat.parse(dateStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static Calendar createCalendar() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c;
    }

    public static Calendar createCalendar(long mils) {
        Calendar c = createCalendar();
        c.setTimeInMillis(c.getTimeInMillis() + mils);
        return c;
    }


}
