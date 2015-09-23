package xstar.com.library.commons;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing
 * Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application>
{
	public ApplicationTest()
	{
		super(Application.class);
        AndroidHelper.init(getContext());
	}

    public void testContext(){
        assertEquals("xstar.com.library,commons",getContext().getPackageName());
    }
}