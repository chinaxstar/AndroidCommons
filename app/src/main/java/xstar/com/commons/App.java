package xstar.com.commons;

import android.app.Application;

import xstar.com.library.commons.AppUtils;

/**
 * @author: xstar
 * @since: 2017-06-01.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.getInstance().init(this);
    }
}
