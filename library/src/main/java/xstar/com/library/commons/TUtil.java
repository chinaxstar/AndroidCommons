package xstar.com.library.commons;


import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast辅助类
 */
public class TUtil {

    private static Toast mToast;

    public static void showLong(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    public static void showLong(Context context, @StringRes int text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    public static void showShort(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void showShort(Context context, @StringRes int text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration, int gravity) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    public static void show(Context context, CharSequence text, int duration) {
        show(context, text, duration, Gravity.CENTER);
    }

    public static void show(Context context, @StringRes int res, int duration) {
        show(context, context.getString(res), duration, Gravity.CENTER);
    }
}
