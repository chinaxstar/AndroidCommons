package xstar.com.library.commons.listener;

import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 * 点击触发器 Created by xstar on 2015/5/7.
 */
public abstract class PointTrigger implements View.OnTouchListener
{
	private boolean	isPress	= false;

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{

		int w = v.getWidth();
		int h = v.getHeight();
		RectF rectF = new RectF(0, 0, w, h);
		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				isPress = true;
				onPressed(v);
				break;
			case MotionEvent.ACTION_MOVE:
				if (!rectF.contains(event.getX(), event.getY()) && isPress)
				{
					onRelease(v);
					isPress = false;
				}
				break;
			case MotionEvent.ACTION_UP:
				if (isPress)
				{
					isPress = false;
					onClick(v);
					onRelease(v);
				}
				break;
			case MotionEvent.ACTION_CANCEL:
				onRelease(v);
				isPress = false;
				break;
		}
		return !canContinue;
	}

	/**
	 * 按下
	 */
	public abstract void onPressed(View view);

	/**
	 * 松开
	 */
	public abstract void onRelease(View view);

	/**
	 * 点击事件
	 */
	public void onClick(View view)
	{

	}

	private boolean getPressed()
	{
		return isPress;
	}

	/**
	 * 继续向下传递触屏事件 default 可以继续传递
	 */
	private boolean	canContinue	= true;

	public boolean isCanContinue()
	{
		return canContinue;
	}

	public void setCanContinue(boolean canContinue)
	{
		this.canContinue = canContinue;
	}
}
