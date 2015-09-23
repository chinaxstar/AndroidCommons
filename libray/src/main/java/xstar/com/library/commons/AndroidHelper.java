package xstar.com.library.commons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Administrator on 2015/9/22.
 */
public class AndroidHelper
{
	private static Context			context;
	private static DisplayMetrics	dm;

	public static void init(Context context)
	{
		context = context.getApplicationContext();
	}

	/**
	 * 获取屏幕大小
	 * 
	 * @return DisplayMetrics
	 */
	public static DisplayMetrics getScreenSize()
	{
		if (context == null) throw new NullPointerException("field context is null, AndroidHelper may not use init(context)");
		if (dm == null)
		{
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			dm = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(dm);
		}
		return dm;
	}

	/**
	 * 判断是否平板
	 * 
	 * @return true 表示为平板
	 */
	public static boolean isTablet()
	{
		if (context == null) throw new NullPointerException("field context is null, AndroidHelper may not use init(context)");
		DisplayMetrics dm = getScreenSize();
		return dm.widthPixels > dm.heightPixels;
	}

	public static void toast(CharSequence charSequence, boolean islong)
	{
		if (context == null) throw new NullPointerException("field context is null, AndroidHelper may not use init(context)");
		Toast.makeText(context, charSequence, islong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static void toast(CharSequence charSequence)
	{
		toast(charSequence, false);
	}

	/**
	 * 跳转页面
	 */
	public static void startActivity(Activity act, Class<? extends Activity> clzz, boolean finish, int enter, int exit, Bundle data)
	{
		if (context == null)init(act);
		Intent intent = new Intent(act, clzz);
		if (data != null)
		{
			intent.putExtras(data);
		}
		act.startActivity(intent);
		if (finish)
		{
			act.finish();
		}
		act.overridePendingTransition(enter, exit);
	}

	/**
	 * 跳转页面
	 */
	public static void startActivity(Activity act, Class<? extends Activity> clzz, boolean finish, Bundle data)
	{
		startActivity(act, clzz, finish, 0, 0, data);
	}

	/**
	 * 跳转页面
	 */
	public static void startActivity(Activity act, Class<? extends Activity> clzz, Bundle data)
	{
		startActivity(act, clzz, false, 0, 0, data);
	}

	/**
	 * 跳转页面
	 */
	public static void startActivity(Activity act, Class<? extends Activity> clzz, boolean isfinish)
	{
		startActivity(act, clzz, isfinish, 0, 0, null);
	}

	/**
	 * 跳转页面
	 */
	public static void startActivity(Activity act, Class<? extends Activity> clzz)
	{
		startActivity(act, clzz, false, 0, 0, null);
	}

	/**
	 * 跳转页面forresult
	 */
	public static void startActivityForResult(Activity act, Class<? extends Activity> clazz, int requestCode)
	{
		startActivityForResult(act, new Intent(act, clazz), requestCode);
	}

	/**
	 * 跳转页面forresult
	 */
	public static void startActivityForResult(Activity act, Intent intent, int requestCode)
	{
		act.startActivityForResult(intent, requestCode);
	}

	/**
	 * dp -> px
	 *
	 */
	public static int dpToPiexl(float dp)
	{
		DisplayMetrics dm = getScreenSize();
		return (int) (dp * dm.density + 0.5f);
	}

	/**
	 * px -> dp
	 *
	 */
	public static int piexlToDp(float px)
	{
		DisplayMetrics dm = getScreenSize();
		return (int) (px / dm.density + 0.5f);
	}

	/**
	 * 安装
	 */
	public static void install(File file)
	{
		// 核心是下面几句代码
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 卸载
	 */
	public static void uninstall(File file){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_DELETE);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setData(Uri.parse("package:"+context.getPackageName()));
		context.startActivity(intent);
	}

}
