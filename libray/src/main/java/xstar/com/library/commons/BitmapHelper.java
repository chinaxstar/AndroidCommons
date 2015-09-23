package xstar.com.library.commons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2015/9/22.
 */
public class BitmapHelper
{

	/**
	 * 将bitmap转换成byte数组
	 * 
	 * @param bitmap
	 *            位图对象
	 * @return byte数组
	 */
	public static byte[] getBitmapData(Bitmap bitmap)
	{
		if (bitmap != null)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			return bos.toByteArray();
		}

		return null;
	}

    /**
     *
     * yuv数据转bitmap
     *
     * @throws IOException
     */
	public static Bitmap yuvToBitmap(byte[] yuv, int width, int height) throws IOException
	{
		YuvImage yuvImage = new YuvImage(yuv, ImageFormat.NV21, width, height, null);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, outputStream);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		Bitmap bitmap = null;
		try
		{
			bitmap = BitmapFactory.decodeStream(inputStream);
		}
		finally
		{
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		}
		return bitmap;
	}
}
