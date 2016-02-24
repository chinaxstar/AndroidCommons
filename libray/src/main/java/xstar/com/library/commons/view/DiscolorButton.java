package xstar.com.library.commons.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import xstar.com.library.commons.R;
import xstar.com.library.commons.listener.PointTrigger;

/**
 * @author xstar
 * @since 2015/8/7.
 *        <P>
 *        字变色按钮
 *        </P>
 */
public class DiscolorButton extends Button
{
	private String simpleName;

	public DiscolorButton(Context context)
	{
		super(context);
	}

	public DiscolorButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initAttr(attrs);
		simpleName = context.getClass().getSimpleName();
	}

	private void initAttr(AttributeSet attrs)
	{
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DiscolorButton);
		mTextColor = getCurrentTextColor();
		textColor_selectd = a.getColor(R.styleable.DiscolorButton_textColor_selectd, mTextColor);
		initGradientDrawable(a);
		SelectTrigger st = new SelectTrigger();
		st.setCanContinue(true);
		setOnTouchListener(st);
	}

	public DiscolorButton(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initAttr(attrs);
	}

	private int					textColor_selectd;
	private int					mTextColor;
	private GradientDrawable	bgdrawable;
	private int					bgColor;


	private class SelectTrigger extends PointTrigger
	{
		@Override
		public void onPressed(View view)
		{
			setTextColor(textColor_selectd);
		}

		@Override
		public void onRelease(View view)
		{
			setTextColor(mTextColor);
		}

		@Override
		public void onClick(View view)
		{

		}
	}

	@Override
	public boolean callOnClick()
	{
		boolean bool = super.callOnClick();
		if (bool)
		{
			onClick();
		}
		return bool;
	}

	@Override
	public boolean performClick()
	{
		boolean bool = super.performClick();
		if (bool)
		{
			onClick();
		}
		return bool;
	}

	public void onClick()
	{

	}

	private float radius;

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void initGradientDrawable(TypedArray a)
	{
		if(getBackground() instanceof StateListDrawable)return;
		if (bgdrawable == null) bgdrawable = new GradientDrawable();
		radius = a.getDimension(R.styleable.DiscolorButton_radius, 0);
		bgColor = a.getColor(R.styleable.DiscolorButton_bgColor, 0xfff);
		bgdrawable.setCornerRadius(radius);
		bgdrawable.setColor(bgColor);
		setBackground(bgdrawable);
	}

	private void resetGradientDrawable(){
		if(bgdrawable == null)return;
		bgdrawable.setCornerRadius(radius);
		bgdrawable.setColor(bgColor);
		postInvalidate();
	}

	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
		resetGradientDrawable();
	}

	public int getTextColor_selectd() {
		return textColor_selectd;
	}

	public void setTextColor_selectd(int textColor_selectd) {
		this.textColor_selectd = textColor_selectd;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		resetGradientDrawable();
	}
}
