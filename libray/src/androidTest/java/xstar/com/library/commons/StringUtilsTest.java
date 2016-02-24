package xstar.com.library.commons;

import android.test.InstrumentationTestCase;

/**
 * Created by Administrator on 2015/7/28.
 */
public class StringUtilsTest extends InstrumentationTestCase{
    public void testIsEmpty() throws Exception{
        assertEquals(true, NumberUtils.isEmpty(null));
        assertEquals(true, NumberUtils.isEmpty(""));
        assertEquals(false, NumberUtils.isEmpty(" "));
        assertEquals(false, NumberUtils.isEmpty("sda"));
    }

    public void testnumToCN() throws Exception{
        String string="一百二十三万四千五百六十七";
        assertEquals(string, NumberUtils.numToCN(1234567, true));
        string="五亿九千八百零五万四千二百一十一";
        assertEquals(string, NumberUtils.numToCN(598054211, true));
        string="一亿零一百";
        assertEquals(string, NumberUtils.numToCN(100000100, true));
        string="一百零三";
        assertEquals(string, NumberUtils.numToCN(103, true));
        string="三";
        assertEquals(string, NumberUtils.numToCN(3, true));
    }
}
