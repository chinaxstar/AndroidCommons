package xstar.com.commons;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import xstar.com.library.commons.AndroidHelper;
import xstar.com.library.commons.Views;
import xstar.com.library.commons.handler.HandlerAbs;

public class CameraActivity extends Activity implements SurfaceHolder.Callback
{

	SurfaceView		surfaceview;
	SurfaceHolder	surfaceHolder;
	DisplayMetrics	dm;
	Button			take_btn;
	Handler			handler	= new DelayHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		dm = AndroidHelper.getScreenSize();
		surfaceview = Views.find(this, R.id.surfaceview);
		take_btn = Views.find(this, R.id.take_btn);
		surfaceHolder = surfaceview.getHolder();
		surfaceHolder.addCallback(this);
	}

	Camera		camera;
	Camera.Size	curSize;

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		camera = Camera.open();
		if (camera == null)
		{
			AndroidHelper.toast("无可用的摄像头！");
			finish();
			return;
		}
		Camera.Parameters parameters = camera.getParameters();
		List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
		for (Camera.Size size : sizeList)
		{
			if (curSize == null)
			{
				curSize = size;
			}
			else
			{
				if (curSize.width < size.width)
				{
					curSize = size;
				}
			}
		}
		AndroidHelper.toast(curSize.width + "/" + curSize.height);
		parameters.setPreviewSize(curSize.width, curSize.height);
		camera.setParameters(parameters);
		try
		{
			camera.setPreviewDisplay(holder);
			if (!AndroidHelper.isTablet())
			{
				camera.setDisplayOrientation(90);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		camera.startPreview();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		camera.release();
		camera = null;
	}

	public void alertBitmap(Bitmap bitmap)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).setView(showBitmap(bitmap)).create();
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.show();
	}

	public ImageView showBitmap(Bitmap bitmap)
	{
		ImageView imageView = new ImageView(this);
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		imageView.setLayoutParams(params);
		imageView.setImageBitmap(bitmap);
		return imageView;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		showTips();
		return super.onTouchEvent(event);
	}

	private void showTips()
	{
		take_btn.setVisibility(View.VISIBLE);
		if (!dodelay)
		{
			dodelay = true;
			handler.sendEmptyMessageDelayed(WHAT, delay);
		}
		else
		{
			handler.removeMessages(WHAT);
			handler.sendEmptyMessageDelayed(WHAT, delay);
		}
	}

	private static final int	delay	= 3000;
	private static final int	WHAT	= 1001;

	public void takephoto(View view)
	{
		if(camera!=null){
			camera.setOneShotPreviewCallback(new Camera.PreviewCallback() {
				@Override
				public void onPreviewFrame(byte[] data, Camera camera) {
					Camera.Size size=camera.getParameters().getPreviewSize();
					YuvImage yuvImage=new YuvImage(data, ImageFormat.NV21,size.width,size.height,null);
					ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
					yuvImage.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, outputStream);
					byte[] colors=outputStream.toByteArray();
					Matrix matrix=new Matrix();
					if(!AndroidHelper.isTablet()){
						matrix.setRotate(90);
					}
					Bitmap bitmap=BitmapFactory.decodeByteArray(colors, 0, colors.length);
					bitmap=Bitmap.createBitmap(bitmap,0,0,size.width,size.height,matrix,false);
					alertBitmap(bitmap);
				}
			});
		}
	}

	boolean dodelay = false;

	private static class DelayHandler extends HandlerAbs<CameraActivity>
	{
		public DelayHandler(CameraActivity mthis)
		{
			super(mthis);
		}

		@Override
		public void handleMessageSafely(Message msg, CameraActivity mthis)
		{
			switch (msg.what)
			{
				case WHAT:
					mthis.take_btn.setVisibility(View.GONE);
					mthis.dodelay = false;
					break;
			}
		}
	}
}
