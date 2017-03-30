package xstar.com.library.commons.javacommons;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/9/22.
 */
public class Empty
{
	public static boolean isNotEmpty(CharSequence sequence)
	{
		return sequence != null && sequence.length() > 0;
	}

	public static boolean isNotEmpty(String str)
	{
		if (str != null) str = str.trim();
		return isNotEmpty((CharSequence) str);
	}

	public static boolean isNotEmpty(Iterator iterator)
	{
		return iterator != null && iterator.hasNext();
	}

	public static boolean isNotEmpty(Iterable iterable)
	{
		return iterable != null && iterable.iterator().hasNext();
	}

	public static boolean isNotEmpty(Collection collection)
	{
		return isNotEmpty((Iterable) collection);
	}

	public static boolean isNotEmpty(Set set)
	{
		return isNotEmpty((Collection) set);
	}

	public static boolean isNotEmpty(Map map)
	{
		return map != null && map.size() > 0;
	}

	public static boolean isNotEmpty(String[] args)
	{
		return args != null && args.length > 0;
	}

	public static <T> boolean isNotEmpty(T[] args)
	{
		return args != null && args.length > 0;
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
