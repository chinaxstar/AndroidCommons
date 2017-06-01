package xstar.com.library.commons.javacommons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: xstar
 * @since: 2017-05-25.
 */

public class Parser {
    public static <T extends Number> T number(String num) {
        Double temp = 0D;
        if (Empty.notEmpty(num)) {
            num = num.trim();
            try {
                temp = Double.parseDouble(num);
            } catch (Exception e) {
                Print.console(e);
            }
        }
        return (T) temp;
    }

    public static Calendar time(String pattern, String dateStr) {
        if (Empty.notEmpty(pattern) && Empty.notEmpty(dateStr)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                Date date = simpleDateFormat.parse(dateStr);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                return calendar;
            } catch (ParseException e) {
                Print.console(e);
            }
        }
        return null;
    }
}
