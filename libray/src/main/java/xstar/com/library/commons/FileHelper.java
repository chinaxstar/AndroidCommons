package xstar.com.library.commons;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2015/9/22.
 */
public class FileHelper
{
	/**
	 * 生成文件
	 */
	public static File getTempFile(String filename, String end)
	{
		String path;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
			path = Environment.getExternalStorageDirectory().getPath();
		}
		else
		{
			path = "";
		}
		if (TextUtils.isEmpty(filename))
		{
			filename = System.currentTimeMillis() + "";
		}
		path = path + "/" + filename + "." + end;
		File file = new File(path);
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 生成文件
	 */
	public static File createFileByPath(String dir) throws IOException
	{
		File file = new File(dir);
		if (file.isDirectory())
		{
			if (!file.exists())
			{
				file.mkdirs();
			}
		}
		else if (file.isFile())
		{
			int lastIndex = dir.lastIndexOf("/");
			if (lastIndex != -1)
			{
				createFileByPath(dir.substring(0, lastIndex + 1));
			}
			if (!file.exists())
			{
				file.createNewFile();
			}
		}
		return file;
	}

	/**
	 * 删除文件 不存在直接返回成功
	 */
	public static boolean deleteFileByPath(String path)
	{
		File file = new File(path);
		if (file.exists())
		{
			return file.delete();
		}
		return true;
	}

    /**
     * 更新文件数据 覆盖/添加
     */
	public static void updateFile(String str, File file, boolean isappend) throws IOException
	{
		OutputStreamWriter outputStreamWriter = null;
		try
		{
			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, isappend), "UTF-8");
			outputStreamWriter.write(str);
		}
		finally
		{
			if (outputStreamWriter != null)
			{
				outputStreamWriter.flush();
				outputStreamWriter.close();
			}
		}
	}
}
