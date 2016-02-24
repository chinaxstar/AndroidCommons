package com.xstar.javacommons;

import android.test.InstrumentationTestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xstar on 2016/2/24.
 */
public class DateUtilsTest extends InstrumentationTestCase {
    public void testAll() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, 10, 22, 17, 53, 22);
        Date date = calendar.getTime();
        assertEquals(DateUtils.formatDate(date), "1999-11-22 17:53:22");
        assertEquals(DateUtils.formatDay(date), "1999-11-22");
        assertEquals(DateUtils.formatTime(date), "17:53:22");
        calendar.set(1999, 6, 4, 7, 5, 2);
        date=calendar.getTime();
        assertEquals(DateUtils.formatDate(date), "1999-07-04 07:05:02");
        assertEquals(DateUtils.formatDay(date), "1999-07-04");
        assertEquals(DateUtils.formatTime(date), "07:05:02");
        assertEquals(DateUtils.format("yyyy年M月d日 HH时mm分ss秒", date), "1999年7月4日 07时05分02秒");
    }
}
