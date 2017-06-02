package xstar.com.library.commons.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2015/6/12.
 */
public class AreaView extends View
{
	private DisplayMetrics	dm;
	private Rect			middleRect			= new Rect();
	private Rect			topRect				= new Rect();
	private Rect			bottomRect			= new Rect();
	private Rect			leftRect			= new Rect();
	private Rect			rightRect			= new Rect();
	private Paint			paint;
	private int				alpha				= 80;

	// 是否将图片放入中间框的内部
	private boolean			isImageInner		= true;
	private Matrix			matrix				= new Matrix();
	private Bitmap			scanLineBitmap;
	private boolean			isStartAnimation	= false;
	private Thread			animationThread;

	public AreaView(Context context)
	{
		super(context);
		init();
	}

	public AreaView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public AreaView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init()
	{
		paint = new Paint();
		paint.setAntiAlias(true);
		WindowManager windowManager= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		if (dm.widthPixels < dm.heightPixels)
		{
			middleRect.left = dm.widthPixels >> 3;// left 1/8
			middleRect.right = dm.widthPixels - middleRect.left;// right 7/8
			int width = middleRect.right - middleRect.left;// rect width
			int top = (dm.heightPixels - width) >> 1;// top rect height
			middleRect.top = top;
			middleRect.bottom = dm.heightPixels - middleRect.top;
		}
		else
		{
			middleRect.top = dm.heightPixels >> 3;// left 1/8
			middleRect.bottom = dm.heightPixels - middleRect.top;// right 7/8
			int width = middleRect.bottom - middleRect.top;// rect width
			int left = (dm.widthPixels - width) >> 1;// top rect height
			middleRect.left = left;
			middleRect.right = dm.widthPixels - middleRect.left;
		}
		changeRects();
	}

	private int	left	= 0, top = 0;
	private int	speed	= 10;
	private int	times	= 0;

	@Override
	protected void onDraw(Canvas canvas)
	{
		paint.setAlpha(0);
		canvas.drawRect(0, 0, dm.widthPixels, dm.heightPixels, paint);
		paint.reset();
		paint.setAlpha(100);
		if (middleBitmap != null)
		{
			canvas.drawBitmap(middleBitmap, left, top, paint);
		}
		if (scanLineBitmap != null)
		{
			int mtop = top + speed * times;
			canvas.drawBitmap(scanLineBitmap, left, mtop, paint);
			times++;
			if (mtop + speed > middleRect.bottom)
			{
				times = 0;
			}
		}
		paint.setColor(Color.BLACK);
		paint.setAlpha(alpha);
		canvas.drawRect(leftRect, paint);
		canvas.drawRect(rightRect, paint);
		canvas.drawRect(topRect, paint);
		canvas.drawRect(bottomRect, paint);
		paint.reset();
		if (!isStartAnimation)
		{
			isStartAnimation = true;
			animationThread = new Thread(animationRunnable);
			animationThread.start();
		}
	}

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		isStartAnimation = false;
	}

	private Bitmap	middleBitmap;

	private void changeRects()
	{
		topRect.right = dm.widthPixels;
		topRect.bottom = middleRect.top;

		bottomRect.top = middleRect.bottom;
		bottomRect.right = dm.widthPixels;
		bottomRect.bottom = dm.heightPixels;

		leftRect.right = middleRect.left;
		leftRect.top = middleRect.top;
		leftRect.bottom = middleRect.bottom;

		rightRect.left = middleRect.right;
		rightRect.right = dm.widthPixels;
		rightRect.top = middleRect.top;
		rightRect.bottom = middleRect.bottom;

		Log.e("rect", "middleRect:" + middleRect.toString());
		Log.e("rect", "topRect:" + topRect.toString());
		Log.e("rect", "bottomRect:" + bottomRect.toString());
		Log.e("rect", "leftRect:" + leftRect.toString());
		Log.e("rect", "rightRect:" + rightRect.toString());
	}

	public Rect getMiddleRect()
	{
		return middleRect;
	}

	public void setMiddleRectWidth(int width)
	{
		int temp = (width - middleRect.width()) >> 1;
		middleRect.left -= temp;
		middleRect.top -= temp;
		middleRect.right += temp;
		middleRect.bottom += temp;
		changeRects();
	}

	public void setBitmap(Bitmap bitmap)
	{
		this.middleBitmap = bitmap;
		resizeBitmap();
	}

	public void setImage(int resid)
	{
		Drawable drawable = getResources().getDrawable(resid);
		setImage(drawable);
	}

	public void setImage(Drawable drawable)
	{
		setBitmap(drawable2Bitmap(drawable));
	}

	private Bitmap drawable2Bitmap(Drawable drawable)
	{
		if (drawable instanceof BitmapDrawable)
		{
			return ((BitmapDrawable) drawable).getBitmap();
		}
		else
		{
			Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
			drawable.draw(canvas);
			return bitmap;
		}
	}

	private void resizeBitmap()
	{
		if (middleBitmap == null)
		{
			return;
		}
		int w = middleBitmap.getWidth();
		int h = middleBitmap.getHeight();
		if (isImageInner)
		{
			matrix.setScale(middleRect.width() / (float) w, middleRect.height() / (float) h);
			middleBitmap = Bitmap.createBitmap(middleBitmap, 0, 0, w, h, matrix, false);
			left = middleRect.left;
			top = middleRect.top;
		}
		else
		{
			float scalew = dm.widthPixels / (float) w;
			float scaleh = dm.heightPixels / (float) h;
			float tempscale = scalew > scaleh ? scaleh : scalew;
			if (tempscale < 1)
			{
				// 图片大于屏幕
				matrix.setScale(tempscale, tempscale);
				middleBitmap = Bitmap.createBitmap(middleBitmap, 0, 0, w, h, matrix, false);
			}
			else
			{
				middleBitmap = Bitmap.createBitmap(middleBitmap, 0, 0, w, h);
			}
			w = middleBitmap.getWidth();
			h = middleBitmap.getHeight();
			left = (dm.widthPixels - w) >> 1;
			top = (dm.heightPixels - h) >> 1;
		}
	}

	public void setImageInner(boolean isImageInner)
	{
		this.isImageInner = isImageInner;
	}

	public void setScanLineImage(int resid)
	{
		this.scanLineBitmap = drawable2Bitmap(getResources().getDrawable(resid));
		resizeScanLineBitmap();
	}

	private void resizeScanLineBitmap()
	{
		if (scanLineBitmap == null)
		{
			return;
		}
		int mwidth = middleRect.width();
		int lineWidth = scanLineBitmap.getWidth();
		int lineHeight = scanLineBitmap.getHeight();
		float scale = mwidth / (float) lineWidth;
		matrix.setScale(scale, scale);
		scanLineBitmap = Bitmap.createBitmap(scanLineBitmap, 0, 0, lineWidth, lineHeight, matrix, false);
	}

	Runnable	animationRunnable	= new Runnable()
									{
										@Override
										public void run()
										{
											while (isStartAnimation)
											{
												postInvalidate();
												try
												{
													Thread.sleep(16);
												}
												catch (InterruptedException e)
												{
													e.printStackTrace();
												}
											}
										}
									};

}
