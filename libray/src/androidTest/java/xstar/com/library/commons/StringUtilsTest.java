package xstar.com.library.commons;

import android.test.InstrumentationTestCase;

/**
 * author: xstar
 * since: 2015/7/28.
 */
public class StringUtilsTest extends InstrumentationTestCase {
    public void testIsEmpty() throws Exception {
        assertEquals(true, NumberUtils.isEmpty(null));
        assertEquals(true, NumberUtils.isEmpty(""));
        assertEquals(false, NumberUtils.isEmpty(" "));
        assertEquals(false, NumberUtils.isEmpty("sda"));
    }

    public void testnumToCN() throws Exception {
        assertEquals("三", NumberUtils.numToCN(3, true));
        assertEquals("三十", NumberUtils.numToCN(30, true));
        assertEquals("三百", NumberUtils.numToCN(300, true));
        assertEquals("三千三百三十三", NumberUtils.numToCN(3333, true));
        assertEquals("三千零三十三", NumberUtils.numToCN(3033, true));
        assertEquals("三千零三", NumberUtils.numToCN(3003, true));
        assertEquals("三万零三十三", NumberUtils.numToCN(30033, true));
        assertEquals("三十万零三十三", NumberUtils.numToCN(300033, true));
        assertEquals("三百万零三十三", NumberUtils.numToCN(3000033, true));
        assertEquals("三百万三千零三十三", NumberUtils.numToCN(3003033, true));
        assertEquals("三百零三万三千零三十三", NumberUtils.numToCN(3033033, true));
        assertEquals("三千零三万三千零三十三", NumberUtils.numToCN(30033033, true));
        assertEquals("三亿零三万三千零三十三", NumberUtils.numToCN(300033033, true));
        assertEquals("叁亿零叁万叁千零叁十叁", NumberUtils.numToCN(300033033, false));
        assertEquals("负叁亿零叁万叁千零叁十叁", NumberUtils.numToCN(-300033033, false));
    }
}
