package xstar.com.library.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/28.
 */
public class NumberUtils
{
	public static boolean isEmpty(String str)
	{
		return str == null || str.equals("");
	}

	private static String[]	CN_NUMBER_LOW		= new String[]
												{ "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	private static String[]	CN_NUMBER_UP		= new String[]
												{ "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	private static String[]	CN_NUMBER			= CN_NUMBER_LOW;
	private static String[]	CN_NUMBER_UNIT_USE	= new String[]
												{ "个", "十", "百", "千", "万", "亿" };

	private static String[]	CN_NUMBER_UNIT		= new String[]
												{ "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿" };

	public static String numToCN(int num, boolean isSimple)
	{
		String[] units=new String[]{"","万","亿"};
		StringBuilder stringBuilder = new StringBuilder();
		boolean isNegative = num < 0;
		if (isNegative)
		{
			num = -num;
		}
		List<Integer> strings = new ArrayList<>();
		int ten = 10000;
		int y = 0;
		int tempnum = num;
		while (tempnum != 0)
		{
			y = tempnum % ten;
			tempnum = tempnum / ten;
			strings.add(y);
		}
		int len = strings.size();
		stringBuilder.setLength(0);
		for (int i = 0; i < len; i++)
		{

			if(strings.get(i)==0){
				continue;
			}
			String temp = fourNumToString(strings.get(i));
			if (i != 0)
			{
				temp = temp + units[i];
			}
			stringBuilder.insert(0, temp);
		}
		if (stringBuilder.indexOf(CN_NUMBER[0]) == 0)
		{
			stringBuilder.deleteCharAt(0);
		}
		if(isNegative){
			stringBuilder.insert(0,"负");
		}
		return stringBuilder.toString();
	}

	private static String fourNumToString(int num)
	{
		int tempnum=num;
		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		int ten = 10;
		int y = 0;
		while (tempnum != 0)
		{
			y = tempnum % ten;
			tempnum = tempnum / ten;
			if (i != 0&&y!=0)
			{
				stringBuilder.append(CN_NUMBER_UNIT_USE[i]);
			}
			stringBuilder.append(CN_NUMBER[y]);
			i++;
		}
		stringBuilder.reverse();
		int index = -1;
		while ((index = stringBuilder.indexOf(CN_NUMBER[0] + CN_NUMBER[0])) != -1)
		{
			stringBuilder.replace(index, index + 2, CN_NUMBER[0]);
		}
		int sblen=stringBuilder.length();
		if (sblen!=0&&stringBuilder.lastIndexOf(CN_NUMBER[0]) == sblen - 1)
		{
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		if(num<1000){
			stringBuilder.insert(0,CN_NUMBER[0]);
		}
		return stringBuilder.toString();
	}
}
