package xstar.com.library.commons;

import android.app.Activity;
import android.view.View;

/**
 * @author: xstar
 * @since: 2015/6/29.
 */
public class ViewUtils {
    public static void forbid(View... views) {
        for (View v : views) {
            v.setEnabled(false);
            v.setFocusable(false);
            v.setClickable(false);
        }
    }

    public static void restore(View... views) {
        for (View v : views) {
            v.setEnabled(true);
            v.setFocusable(true);
            v.setClickable(true);
        }
    }

    public static void hiden(boolean haveBody, View... views) {
        int hiden = haveBody ? View.INVISIBLE : View.GONE;
        for (View v : views) {
            v.setVisibility(hiden);
        }
    }

    public static void hiden(View... views) {
        hiden(false, views);
    }

    public static void show(View... views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    public static <T extends View> T find(View view, int resid) {
        return (T) view.findViewById(resid);
    }

    public static <T extends View> T find(Activity activity, int resid) {
        return (T) activity.findViewById(resid);
    }
}
