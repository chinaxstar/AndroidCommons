package xstar.com.downloader.downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by xstar on 2017-03-02.
 */
public class DownloadThread extends Thread
{
	public static final String			TAG			= "DownloadThread";

	private String						url_str;
	private boolean						isFinished	= false;
	private ArrayBlockingQueue<String>	queue;
	private RandomAccessFile						srcFile;
	private long						start_pos;
	private long						end_pos;

	public DownloadThread(String url_str, ArrayBlockingQueue<String> message, RandomAccessFile file, long start_pos, long end_pos)
	{
		super();
		this.url_str = url_str;
		this.queue = message;
		this.srcFile = file;
		this.start_pos = start_pos;
		this.end_pos = end_pos;
	}

	@Override
	public void run()
	{
		super.run();
		RandomAccessFile randomAccessFile = srcFile;
		StringBuilder sbuilder = new StringBuilder();
        int code=0;
		try
		{

			URL url = new URL(url_str);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setAllowUserInteraction(true);
			sbuilder.append("bytes=").append(start_pos).append("-").append(end_pos);
			connection.addRequestProperty("Range", sbuilder.toString());
            code=connection.getResponseCode();
			InputStream inputStream = connection.getInputStream();

			randomAccessFile.seek(start_pos);

			byte[] temp = new byte[1024];
			int len = -1;
			while ((len = inputStream.read(temp)) != -1)
			{
				randomAccessFile.write(temp, 0, len);
				if (queue != null)
				{
					sbuilder.setLength(0);
					sbuilder.append(len);
					queue.put(sbuilder.toString());
				}
			}
		}
		catch (MalformedURLException e)
		{
			sbuilder.setLength(0);
			sbuilder.append("Exception "+code);
		}
		catch (IOException e)
		{
			sbuilder.setLength(0);
			sbuilder.append("Exception "+code);
		}
		catch (InterruptedException e)
		{
			sbuilder.setLength(0);
			sbuilder.append("Exception "+code);
		}
		try
		{
			if (sbuilder.length() > 0) queue.put(sbuilder.toString());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		isFinished = true;
	}
}
