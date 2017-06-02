package xstar.com.library.commons;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @author: xstar
 * @since: 2017-05-23.
 * 日志
 */

public class L {
    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static String TAG = "LOG";

    public static void setTAG(@NonNull String tag) {
        TAG = tag;
    }

    public static void isDebug(boolean bool) {
        isDebug = bool;
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void e(String msg, Throwable ex) {
        if (isDebug)
            Log.e(TAG, msg, ex);
    }

    public static void e(Throwable ex) {
        if (isDebug)
            Log.e(TAG, "", ex);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable ex) {
        if (isDebug) Log.e(tag, msg, ex);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }


}
