package xstar.com.commons;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.Result;
import com.google.zxing.WriterException;

import xstar.com.commons.twod.ZxingHelper;
import xstar.com.library.commons.ViewUtils;

public class TwoDGenereteActivity extends Activity
{

	Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two_dscan);
		String str = "我爱北京天安门！";
		try
		{
			bitmap = ZxingHelper.getInstance().encodeMessage(str, 400, 400);
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
		if (bitmap != null)
		{
			ImageView imageView = ViewUtils.find(this, R.id.image);
			imageView.setImageBitmap(bitmap);
			Result result = ZxingHelper.getInstance().decodeBitmap(bitmap, 400, 400);
			TextView content_text = ViewUtils.find(this, R.id.content_text);
            content_text.setText(result.getText());
		}
	}
}
