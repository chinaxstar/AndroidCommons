package xstar.com.library.commons;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import java.io.File;
import java.util.List;

import xstar.com.library.commons.javacommons.Assert;
import xstar.com.library.commons.javacommons.Empty;

/**
 * @author: xstar
 * @since: 2017-05-23.
 */

public class AppUtils {
    private Context context;
    private static AppUtils INSTANCE;

    public static AppUtils getInstance() {
        if (INSTANCE == null) INSTANCE = new AppUtils();
        return INSTANCE;
    }

    private AppUtils() {
    }

    public void init(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public Context app() {
        Assert.notNullCheck(context, "未调用getInstance(Context)初始化！");
        return context;
    }

    /**
     * 根据包名取得应用信息
     *
     * @param packageName
     * @return
     */
    public PackageInfo getPackageInfo(String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            return null;
        }
    }

    public PackageInfo getPackageInfo() {
        return getPackageInfo(context.getPackageName());
    }

    /**
     * 获取应用程序名称
     */
    public String getAppName() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) return packageInfo.versionName;
        return null;
    }

    /**
     * [获取应用程序版本序号信息]
     *
     * @return 当前应用的版本
     */
    public int getVersion() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) return packageInfo.versionCode;
        return 0;
    }

    private DisplayMetrics displayMetrics = new DisplayMetrics();

    public DisplayMetrics getDisplayInfo() {
        if (context != null) {
            if (displayMetrics.heightPixels == 0) {
                WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            }
            return displayMetrics;
        }
        return null;
    }

    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getDisplayInfo());
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getDisplayInfo());
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public float px2dp(float pxVal) {
        if (context != null) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (pxVal / scale);
        }
        return 0;
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public float px2sp(float pxVal) {
        if (context != null) {
            return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
        } else return 0;
    }

    /**
     *  应用程序是否在前台使用
     * @return
     */
    public  boolean isApplicationInForeground() {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (Empty.notEmpty(taskList)) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

     /**
     * 安装
     */
    public  void install(File file) {
        // 核心是下面几句代码
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 卸载
     */
    public  void uninstall(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }
}
