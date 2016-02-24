package xstar.com.library.commons.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import xstar.com.library.commons.R;
import xstar.com.library.commons.handler.HandlerAbs;


/**
 * Created by Administrator on 2015/9/8.
 */
public class TimeFloatView extends RelativeLayout
{
	private static final int	HIDEN_WHAT	= 0x10086;
	private static final int	SHOW_WHAT	= 0x10010;

	public TimeFloatView(Context context)
	{
		super(context);
	}

	private int		showTime;
	private int		hidenTime;
	private Handler	handler;

	public TimeFloatView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TimeFloatView);
		showTime = a.getInt(R.styleable.TimeFloatView_showTime, 0);
		hidenTime = a.getInt(R.styleable.TimeFloatView_hidenTime, 300);
		hidenTime = hidenTime > 300 ? hidenTime : 300;
		handler = new TimeHandler(this);
		setVisibility(GONE);
	}

	private static class TimeHandler extends HandlerAbs<TimeFloatView>
	{

		public TimeHandler(TimeFloatView timeFloatView)
		{
			super(timeFloatView);
		}

		@Override
		public void handleMessageSafely(Message msg, TimeFloatView timeFloatView)
		{
			switch (msg.what)
			{
				case HIDEN_WHAT:
					timeFloatView.hidenAnimation();
					break;
				case SHOW_WHAT:
					timeFloatView.setAlpha(1.0f);
					timeFloatView.setVisibility(VISIBLE);
					timeFloatView.delayHiden();
					break;
			}

		}
	}

	ObjectAnimator objectAnimator;

	void hidenAnimation()
	{
		if (objectAnimator != null)
		{
			objectAnimator.cancel();
		}
		objectAnimator = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f).setDuration(hidenTime);
		objectAnimator.addListener(hidenadapter);
		objectAnimator.start();
	}

	private AnimatorListenerAdapter hidenadapter = new AnimatorListenerAdapter()
	{
		@Override
		public void onAnimationEnd(Animator animation)
		{
			super.onAnimationEnd(animation);
			Log.e("timefloatview", "animator end");
			setVisibility(GONE);
		}
	};

	void delayHiden()
	{
		if (showTime > 1)
		{
			handler.sendEmptyMessageDelayed(HIDEN_WHAT, showTime);
		}
	}

	public void show()
	{
		handler.removeMessages(HIDEN_WHAT);
		handler.sendEmptyMessage(SHOW_WHAT);
	}

	public int getHidenTime()
	{
		return hidenTime;
	}

	public void setHidenTime(int hidenTime)
	{
		this.hidenTime = hidenTime;
	}

	public int getShowTime()
	{
		return showTime;
	}

	public void setShowTime(int showTime)
	{
		this.showTime = showTime;
	}
}
