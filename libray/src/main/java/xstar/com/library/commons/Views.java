package xstar.com.library.commons;

import android.app.Activity;
import android.view.View;

/**
 * Created by Administrator on 2015/6/29.
 */
public class Views {
    public static <T extends View> T find(View view, int resid) {
        return (T) view.findViewById(resid);
    }

    public static <T extends View> T find(Activity activity, int resid) {
        return (T) activity.findViewById(resid);
    }
}
