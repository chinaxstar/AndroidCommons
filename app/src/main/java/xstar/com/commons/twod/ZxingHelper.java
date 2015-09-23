package xstar.com.commons.twod;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/28.
 */
public class ZxingHelper
{
	private MultiFormatReader	multiFormatReader;
	private MultiFormatWriter	multiFormatWriter;

	private ZxingHelper()
	{
		multiFormatReader = new MultiFormatReader();
		Map<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
		Collection<BarcodeFormat> decodeFormats = new ArrayList<BarcodeFormat>();
		decodeFormats.addAll(Arrays.asList(BarcodeFormat.values()));
		hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		multiFormatReader.setHints(hints);
		multiFormatWriter = new MultiFormatWriter();
	}

	private static ZxingHelper instance;

	public static ZxingHelper getInstance()
	{
		if (instance == null)
		{
			instance = new ZxingHelper();
		}
		return instance;
	}

	private static final int	WHITE	= 0xffFFFFFF;
	private static final int	BLACK	= 0xff000000;

	public Bitmap encodeMessage(String message, int width, int height) throws WriterException
	{
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix matrix = multiFormatWriter.encode(message, BarcodeFormat.QR_CODE, width, height,hints);
		int[] piexls = new int[width * height];
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				boolean black = matrix.get(i, j);
				int color = WHITE;
				if (black)
				{
					color = BLACK;
				}
				piexls[j + i * height] = color;
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(piexls, 0, width, width, height, Bitmap.Config.ARGB_4444);
		return bitmap;
	}
	public Result decodeBitmap(Bitmap bitmap, int width, int height)
	{
		Log.e("bitmap", bitmap.getWidth() + "/" + bitmap.getHeight());
		int pixels[] = new int[width * height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
		Result result = null;
		try
		{
			result = multiFormatReader.decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(width, width, pixels))));
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		return result;
	}

}
