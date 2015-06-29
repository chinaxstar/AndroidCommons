package xstar.com.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/6/29.
 */
public class AndroidUtil {

    /**
     * 获取屏幕尺寸信息
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenSize(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 获取app内存大小
     * @param context
     * @return
     */
    public static int getAppMemerySize(Context context)
    {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return am.getMemoryClass();
    }


    //bitmap -> byte[]
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

    public static void toast(Context context, CharSequence charSequence, boolean islong)
    {
        Toast.makeText(context, charSequence, islong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, CharSequence charSequence)
    {
        toast(context, charSequence, false);
    }

    /**
     * dp -> px
     * @param context
     * @param dp
     * @return
     */
    public static int dpToPiexl(Context context, float dp)
    {
        DisplayMetrics dm = getScreenSize(context);
        return (int) (dp * dm.density + 0.5f);
    }

    /**
     * px -> dp
     * @param context
     * @param px
     * @return
     */
    public static int piexlToDp(Context context, float px)
    {
        DisplayMetrics dm = getScreenSize(context);
        return (int) (px / dm.density + 0.5f);
    }

    /**
     * 跳转
     * @param act
     * @param clzz
     * @param finish
     * @param enter
     * @param exit
     */
    public static void startActivity(Activity act, Class<? extends Activity> clzz, boolean finish, int enter, int exit)
    {
        Intent intent = new Intent(act, clzz);
        act.startActivity(intent);
        if (finish)
        {
            act.finish();
        }
        if (act.getResources().getAnimation(enter) != null && act.getResources().getAnimation(exit) != null)
        {
            act.overridePendingTransition(enter, exit);
        }
    }

    /**
     * 跳转
     * @param act
     * @param clzz
     * @param finish
     */
    public static void startActivity(Activity act, Class<? extends Activity> clzz, boolean finish)
    {
        startActivity(act, clzz, finish, 0, 0);
    }

    /**
     * 跳转页面 默认不关闭上一页面
     * @param act
     * @param clzz
     */
    public static void startActivity(Activity act, Class<? extends Activity> clzz)
    {
        startActivity(act, clzz, false);
    }

    /**
     * 打开页面等待返回结果
     * @param act
     * @param clazz
     * @param requestCode
     */
    public static void startActivityForResult(Activity act,Class<? extends Activity> clazz,int requestCode){
        startActivityForResult(act, new Intent(act, clazz), requestCode);
    }

    /**
     * 打开页面等待返回结果
     * @param act
     * @param intent
     * @param requestCode
     */
    public static void startActivityForResult(Activity act,Intent intent,int requestCode){
        act.startActivityForResult(intent, requestCode);
    }

    /**
     * 数据流转byte数组
     * @param inputStream
     * @return
     */
    public byte[] getFileData(InputStream inputStream)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int temp = -1;
        try
        {
            while ((temp = inputStream.read()) != -1)
            {
                byteArrayOutputStream.write(temp);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 是否是空集合
     * @param collection
     * @return
     */
    public static boolean isListEmpty(Collection collection)
    {
        if (collection == null || collection.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**|
     * 是否是空字符串
     * @param charSequence
     * @return
     */
    public static boolean isEmptyText(CharSequence charSequence){
        return TextUtils.isEmpty(charSequence);
    }
}
