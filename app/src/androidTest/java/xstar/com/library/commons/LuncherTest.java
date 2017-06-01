package xstar.com.library.commons;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.runner.RunWith;

import xstar.com.commons.LauncherActivity;

/**
 * author: xstar
 * since: 2017-04-07.
 */

@RunWith(AndroidJUnit4.class)
public class LuncherTest extends ActivityInstrumentationTestCase2<LauncherActivity> {
    public LuncherTest(Class<LauncherActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
