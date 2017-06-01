package xstar.com.library.commons;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.DisplayMetrics;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing
 * Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        AndroidHelper.init(getContext());
    }

    public void testAndroidHelper() {
        assertEquals("xstar.com.library.commons.test", getContext().getPackageName());
        DisplayMetrics displayMetrics = AndroidHelper.getScreenSize();
        assertNotNull(displayMetrics);
        assertTrue(displayMetrics.widthPixels > 0);
        assertTrue(displayMetrics.heightPixels > 0);
        assertFalse(AndroidHelper.isTablet());
        assertEquals("123", AndroidHelper.getMetaValue("app_key"));

    }
}