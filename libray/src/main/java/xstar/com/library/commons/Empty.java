package xstar.com.library.commons;

import android.text.TextUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/9/22.
 */
public class Empty
{
	/**
	 * 字符串为空/长度为0时返回 true
	 */
	public static boolean isTextEmpty(String s)
	{
		return TextUtils.isEmpty(s);
	}

	/**
	 * 当集合为null/内容为空时返回true
	 */
	public static boolean isCollectionEmpty(Collection collection)
	{
		return collection == null || collection.isEmpty();
	}

	/**
	 * 当set为null/内容为空时返回true
	 */
	public static boolean isSetEmpty(Set set)
	{
		return set == null || set.isEmpty();
	}

	/**
	 * 当map为null/内容为空时返回true
	 */
	public static boolean isMapEmpty(Map map)
	{
		return map == null || map.isEmpty();
	}

	/**
	 * 当数组为null/长度为0/所有内容为null时 返回 true
	 */
	public static <T> boolean isArrayEmpty(T[] array)
	{
		boolean isnull = array == null || array.length == 0;
		if (!isnull)
		{
			for (T t : array)
			{
				if (t != null)
				{
					return false;
				}
			}
		}
		return true;
	}
}
