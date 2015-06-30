package xstar.com.commons.exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Properties;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * <P>
 * 崩溃处理程序
 * </P>
 *
 * Created by xstar on 2015/5/12.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler
{
	/** Debug Log Tag */
	public static final String				TAG							= "CrashHandler";
	/** 是否开启日志输出, 在Debug状态下开启, 在Release状态下关闭以提升程序性能 */
	public static final boolean				DEBUG						= true;
	/** CrashHandler实例 */
	private static CrashHandler INSTANCE;
	/** 程序的Context对象 */
	private Context						mContext;
	/** 系统默认的UncaughtException处理类 */
	private Thread.UncaughtExceptionHandler	mDefaultHandler;

	/** 使用Properties来保存设备的信息和错误堆栈信息 */
	private Properties						mDeviceCrashInfo			= new Properties();
	private static final String				VERSION_NAME				= "versionName";
	private static final String				VERSION_CODE				= "versionCode";
	private static final String				STACK_TRACE					= "STACK_TRACE";
	/** 错误报告文件的扩展名 */
	private static final String				CRASH_REPORTER_EXTENSION	= ".txt";

	private boolean							isSave						= false;

	/** 保证只有一个CrashHandler实例 */
	private CrashHandler()
	{
	}

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance()
	{
		if (INSTANCE == null) INSTANCE = new CrashHandler();
		return INSTANCE;
	}

	private String	path;

	/**
	 * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
	 *
	 * @param context
	 */
	public void init(Context context)
	{
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
		path = "";
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			path = Environment.getExternalStorageDirectory().getPath();
		}
		path = path + "/ExceptionInfo";
		File file = new File(path);
		if (!file.exists())
		{
			file.mkdir();
		}
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex)
	{
		if(onCrashListener!=null){
			onCrashListener.onCrash(mContext,thread,ex);
		}
	}

	/**
	 * 收集程序崩溃的设备信息
	 *
	 * @param ctx
	 */
	public void collectCrashDeviceInfo(Context ctx)
	{
		try
		{
			// Class for retrieving various kinds of information related to the
			// application packages that are currently installed on the device.
			// You can find this class through getPackageManager().
			PackageManager pm = ctx.getPackageManager();
			// getPackageInfo(String packageName, int flags)
			// Retrieve overall information about an application package that is
			// installed on the system.
			// public static final int GET_ACTIVITIES
			// Since: API Level 1 PackageInfo flag: return information about
			// activities in the package in activities.
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
			if (pi != null)
			{
				// public String versionName The version name of this package,
				// as specified by the <manifest> tag's versionName attribute.
				mDeviceCrashInfo.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);
				// public int versionCode The version number of this package,
				// as specified by the <manifest> tag's versionCode attribute.
				mDeviceCrashInfo.put(VERSION_CODE, pi.versionCode);
			}
		}
		catch (NameNotFoundException e)
		{
			Log.e(TAG, "Error while collect package info", e);
		}
		// 使用反射来收集设备信息.在Build类中包含各种设备信息,
		// 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
		// 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields)
		{
			try
			{
				// setAccessible(boolean flag)
				// 将此对象的 accessible 标志设置为指示的布尔值。
				// 通过设置Accessible属性为true,才能对私有变量进行访问，不然会得到一个IllegalAccessException的异常
				field.setAccessible(true);
				mDeviceCrashInfo.put(field.getName(), field.get(null));
				if (DEBUG)
				{
					Log.d(TAG, field.getName() + " : " + field.get(null));
				}
			}
			catch (Exception e)
			{
				Log.e(TAG, "Error while collect crash info", e);
			}
		}
	}

	/**
	 * 保存错误信息到文件中
	 *
	 * @param ex
	 * @return
	 */
	private String saveCrashInfoToFile(Throwable ex)
	{
		Writer info = new StringWriter();
		PrintWriter printWriter = new PrintWriter(info);
		// printStackTrace(PrintWriter s)
		// 将此 throwable 及其追踪输出到指定的 PrintWriter
		ex.printStackTrace(printWriter);

		// getCause() 返回此 throwable 的 cause；如果 cause 不存在或未知，则返回 null。
		Throwable cause = ex.getCause();
		while (cause != null)
		{
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}

		// toString() 以字符串的形式返回该缓冲区的当前值。
		String result = info.toString();
		printWriter.close();
		mDeviceCrashInfo.put(STACK_TRACE, result);
		try
		{
			long timestamp = System.currentTimeMillis();
			String fileName = path + "/crash-" + timestamp + CRASH_REPORTER_EXTENSION;
			File file = new File(fileName);
			if (!file.exists())
			{
				file.createNewFile();
			}
			// 保存文件
			FileOutputStream trace = new FileOutputStream(file);
			mDeviceCrashInfo.store(trace, "");
			trace.flush();
			trace.close();
			return fileName;
		}
		catch (Exception e)
		{
			Log.e(TAG, "an error occured while writing report file...", e);
		}
		return null;
	}

	public boolean isSave()
	{
		return isSave;
	}

	public void setIsSave(boolean isSave)
	{
		this.isSave = isSave;
	}

	public interface OnCrashListener{
		public void onCrash(Context context, Thread thread, Throwable ex);
	}

	private OnCrashListener onCrashListener=new OnCrashListener() {
		@Override
		public void onCrash(Context context, Thread thread, Throwable ex) {
			new Thread()
			{
				@Override
				public void run()
				{
					Looper.prepare();
					Toast.makeText(mContext,"系统崩溃！",Toast.LENGTH_LONG).show();
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(0);
					Looper.loop();
				}
			}.start();
			if (isSave)
			{
				saveCrashInfoToFile(ex);
			}
		}
	};

	public OnCrashListener getOnCrashListener() {
		return onCrashListener;
	}

	public void setOnCrashListener(OnCrashListener onCrashListener) {
		this.onCrashListener = onCrashListener;
	}
}
