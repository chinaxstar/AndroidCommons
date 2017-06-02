package xstar.com.library.commons.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2015/7/2.
 */
public class QuickViewPager extends ViewPager
{
	public QuickViewPager(Context context)
	{
		super(context);
	}

	public QuickViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	private float	preX	= 0;
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		boolean temp = super.onInterceptTouchEvent(ev);
		if (ev.getAction() == MotionEvent.ACTION_DOWN)
		{
			preX = ev.getX();
		}
		else
		{
			if (Math.abs(ev.getX() - preX) > 4)
			{
				return true;
			}
			else
			{
				preX = ev.getX();
			}
		}
		return temp;
	}
}
