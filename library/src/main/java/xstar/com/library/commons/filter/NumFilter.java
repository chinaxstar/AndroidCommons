package xstar.com.library.commons.filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by xstar on 2015/8/19.
 */
public class NumFilter implements InputFilter
{
	@Override
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
	{
		if (source == null || source.length() == 0)
		{
			return source;
		}
		if (source.charAt(0) < 47 && source.charAt(0) > 57)
		{
			return null;
		}
		if (dest.length() == 0 && source.charAt(0) == '0')
		{
			source = source.subSequence(start + 1, end);
		}
		return source;
	}
}
