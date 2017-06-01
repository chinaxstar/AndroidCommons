package xstar.com.commons;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import xstar.com.library.commons.AndroidHelper;
import xstar.com.library.commons.ViewUtils;
import xstar.com.library.commons.listener.PointTrigger;
import xstar.com.library.commons.listener.XmlAnimatorPointTrigger;

/**
 *   ;���������
 * create by xstar 2015-7-2
 */
public class ButtonToBiggerActivity extends Activity
{
	Button	button;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button_to_bigger);
        button= ViewUtils.find(this,R.id.button);
        button.setOnTouchListener(new XmlAnimatorPointTrigger(this,button,R.animator.to_bigger,0));
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AndroidHelper.toast("asdasdasdasda");
			}
		});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_button_to_bigger, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	PointTrigger pointTrigger=new PointTrigger() {
		@Override
		public void onPressed(View view) {
			AnimatorSet animatorSet = new AnimatorSet();
			ObjectAnimator oa1 = new ObjectAnimator().ofFloat(view, "ScaleX", 1f, 1.2f);
			ObjectAnimator oa2 = new ObjectAnimator().ofFloat(view, "ScaleY", 1f, 1.2f);
			animatorSet.play(oa1).with(oa2);
			animatorSet.setDuration(100).start();

		}

		@Override
		public void onRelease(View view) {
			AnimatorSet animatorSet = new AnimatorSet();
			ObjectAnimator oa1 = new ObjectAnimator().ofFloat(view, "ScaleX", 1.2f, 1f);
			ObjectAnimator oa2 = new ObjectAnimator().ofFloat(view, "ScaleY", 1.2f, 1f);
			animatorSet.play(oa1).with(oa2);
			animatorSet.setDuration(100).start();
		}
	};
}
