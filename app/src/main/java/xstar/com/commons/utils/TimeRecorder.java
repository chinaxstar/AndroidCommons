package xstar.com.commons.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>
 * 时间点记录器
 * </P>
 * Created by xstar on 2015/6/4.
 */
public class TimeRecorder
{
	private String				TAG			= "TimeRecorder";
	private List<Long>			timePoints	= new ArrayList<>();
	private int					limitSize	= 1000;
	private static TimeRecorder instance;

	public static TimeRecorder getInstance()
	{
		if (instance == null)
		{
			instance = new TimeRecorder();
		}
		return instance;
	}

	private TimeRecorder()
	{
	}

	/**
	 * 记录时间点 超过限制记录数删除最早记录
	 */
	public TimeRecorder record()
	{
		long record = System.currentTimeMillis();
		timePoints.add(record);
		if (listener != null)
		{
			listener.onRecord(record, timePoints.size());
		}
		if (timePoints.size() > limitSize)
		{
			long temp = timePoints.remove(1);
			if (listener != null)
			{
				listener.onLimit(temp, limitSize);
			}
		}
		return instance;
	}

	/**
	 * 打印最后的两次记录间隔
	 */
	public void printLast()
	{
		long interval = getLastInterval();
		if (interval != -1)
		{
			System.out.println(String.format(TAG + ":		last interval time is %d ms", interval));
		}
		else System.out.println(String.format(TAG + ":		recording's size is too small ", timePoints.size()));
	}

	/**
	 * 获得最后两次记录间隔 记录不足两次返回-1
	 * 
	 * @return
	 */
	public long getLastInterval()
	{
		long interval = -1;
		if (timePoints.size() > 2)
		{
			long t1 = timePoints.get(timePoints.size() - 1);
			long t2 = timePoints.get(timePoints.size() - 2);
			interval = t1 - t2;
		}
		return interval;
	}

	/**
	 * 返回所有记录
	 * 
	 * @return
	 */
	public List<Long> getTimePoints()
	{
		return timePoints;
	}

	/**
	 * 返回记录限制大小
	 * 
	 * @return
	 */
	public int getLimitSize()
	{
		return limitSize;
	}

	/**
	 * 设置限制 数字应不小于2否则无效
	 * 
	 * @param limitSize
	 */
	public TimeRecorder setLimitSize(int limitSize)
	{
		this.limitSize = limitSize < 2 ? 1000 : limitSize;
		return instance;
	}

	/**
	 * 设置监听器
	 * @param listener
	 */
	public void setListener(OnRecodListener listener) {
		this.listener = listener;
	}

	public interface OnRecodListener
	{
		/**
		 * 记录监听
		 * 
		 * @param recordtime
		 *            记录时间
		 * @param reocrdsize
		 *            记录次数（1,2,3，。。。）
		 */
		public void onRecord(long recordtime, int reocrdsize);

		/**
		 * 达到限制
		 * 
		 * @param removetime
		 *            删除的最先记录
		 * @param limitsize
		 *            限制大小
		 */
		public void onLimit(long removetime, int limitsize);
	}

	private OnRecodListener	listener	= new OnRecodListener()
										{
											@Override
											public void onRecord(long recordtime, int reocrdsize)
											{

											}

											@Override
											public void onLimit(long removetime, int limitsize)
											{

											}
										};
}
