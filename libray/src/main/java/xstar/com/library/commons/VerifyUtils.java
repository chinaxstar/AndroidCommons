package xstar.com.library.commons;

import android.graphics.drawable.Drawable;
import android.widget.EditText;

import xstar.com.library.commons.javacommons.Empty;


/**
 * @author xstar on 2015/10/27.
 */
public class VerifyUtils
{

	public static boolean isEditTextNull(EditText editText)
	{
		return !Empty.isNotEmpty(editText.getText().toString());
	}

	/**
	 *
	 * @param editText
	 *            输入
	 * @param errortext
	 *            错误显示
	 * @return 为空时为true
	 */
	public static boolean doEditTextNull(EditText editText, String errortext)
	{
		if (isEditTextNull(editText))
		{
			editText.setError(errortext);
			return true;
		}
		return false;
	}

	public static boolean doEditTextNull(EditText editText, int resid)
	{
		if (isEditTextNull(editText))
		{
			String res = editText.getContext().getString(resid);
			editText.setError(res);
			return true;
		}
		return false;
	}

	public static boolean doEditTextNull(EditText editText, String errortext, Drawable drawable)
	{
		if (isEditTextNull(editText))
		{
			editText.setError(errortext, drawable);
			return true;
		}
		return false;
	}
}
