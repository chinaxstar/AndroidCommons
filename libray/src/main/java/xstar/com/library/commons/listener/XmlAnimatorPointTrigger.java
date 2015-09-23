package xstar.com.library.commons.listener;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xstar on 2015/7/2.
 */
public class XmlAnimatorPointTrigger extends PointTrigger
{
	private int						press, release;
	private Context					context;
	private AnimatorSet				pressAnim, releaseAnim;
	private View					view;
	private Map<String, float[]>	viewAttr		= new HashMap<>();
	private int						recoveryTime	= 200;

	public XmlAnimatorPointTrigger(Context context, View user, int press, int release)
	{
		this.press = press;
		view = user;
		this.release = release;
		this.context = context;
		getViewAttrs();
		initAnimation();

	}

	public XmlAnimatorPointTrigger(Context context, View user)
	{
		this(context, user, 0, 0);
	}

	private void getViewAttrs()
	{
		viewAttr.put("scaleX", new float[]
		{ view.getScaleX(), 1 });
		viewAttr.put("scaleY", new float[]
		{ view.getScaleY(), 1 });
		viewAttr.put("TranslationX", new float[]
		{ view.getTranslationX(), 0 });
		viewAttr.put("TranslationY", new float[]
		{ view.getTranslationY(), 0 });
		if (Build.VERSION.SDK_INT >= 21)
		{
			viewAttr.put("TranslationZ", new float[]
			{ view.getTranslationZ(), 0 });
		}
		viewAttr.put("RotationX", new float[]
		{ view.getRotationX(), 0 });
		viewAttr.put("RotationY", new float[]
		{ view.getRotationY(), 0 });
		viewAttr.put("Rotation", new float[]
		{ view.getRotation(), 0 });
		viewAttr.put("Alpha", new float[]
		{ view.getAlpha(), 1 });
		// viewAttr.put("PivotX", new float[]
		// { view.getPivotX(), 0 });
		// viewAttr.put("PivotY", new float[]
		// { view.getPivotY(), 0 });
	}

	@Override
	public void onPressed(View view)
	{
		if (releaseAnim != null)
		{
			releaseAnim.cancel();
		}
		if (pressAnim != null)
		{
			pressAnim.setTarget(view);
			pressAnim.start();
		}
	}

	@Override
	public void onRelease(View view)
	{
		if (pressAnim != null)
		{
			pressAnim.cancel();
		}
		if (releaseAnim != null)
		{
			releaseAnim.setTarget(view);
			releaseAnim.start();
		}
		else recoveryView();
	}

	private void initAnimation()
	{

		try
		{
			pressAnim = (AnimatorSet) AnimatorInflater.loadAnimator(context, press);
		}
		catch (Resources.NotFoundException e)
		{
			pressAnim = null;
		}
		try
		{
			releaseAnim = (AnimatorSet) AnimatorInflater.loadAnimator(context, release);
		}
		catch (Resources.NotFoundException e)
		{
			releaseAnim = null;
		}
	}

	public void recoveryView()
	{
		AnimatorSet animatorSet = new AnimatorSet();
		AnimatorSet.Builder builder = null;
		getViewAttrs();
		boolean isPlay = false;
		for (String s : viewAttr.keySet())
		{
			float[] temp = viewAttr.get(s);
			if (temp[0] != temp[1])
			{
				ObjectAnimator objectAnimator = new ObjectAnimator().ofFloat(view, s, temp[0], temp[1]);
				if (!isPlay)
				{
					builder = animatorSet.play(objectAnimator);
					isPlay = true;
				}
				else builder.with(objectAnimator);
			}
		}
		if (isPlay)
		{
			animatorSet.setDuration(recoveryTime);
			animatorSet.start();
		}

	}

	public void setRecoveryTime(int time)
	{
		recoveryTime = time > 10 ? 200 : time;
	}
}
