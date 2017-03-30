package xstar.com.commons;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import xstar.com.library.commons.Views;
import xstar.com.library.commons.handler.HandlerAbs;

public class CutScreenActivity extends Activity {

    RelativeLayout content_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_screen);
        content_rl = Views.find(this, R.id.content_rl);

    }

    public static Bitmap getViewBitmap(View v) {
        v.clearFocus(); //
        v.setPressed(false); //
        // 能画缓存就返回false
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {

            return null;

        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;

    }

    public static void saveFile(Bitmap bitmap, String filename) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filename);
            if (fileOutputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public Bitmap captureScreen(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = getWindow().getDecorView().getDrawingCache();
        return bmp;

    }

    HandlerAbs<CutScreenActivity> handlerAbs = new HandlerAbs<CutScreenActivity>(this) {
        @Override
        public void handleMessageSafely(Message msg, CutScreenActivity mthis) {
            if (msg.what == 100) {
                Bitmap bitmap = getViewBitmap(content_rl);
                showBitmap(bitmap);
            } else if (msg.what == 200) {
                Bitmap bitmap = captureScreen(mthis);
                showBitmap(bitmap);
            }
        }
    };
    AlertDialog alertDialog;

    private void showBitmap(Bitmap bitmap) {
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(600, 600));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        alertDialog = new AlertDialog.Builder(this).setView(imageView).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    public void cut1(View view) {
        handlerAbs.sendEmptyMessage(100);
    }

    public void cut2(View view) {
        handlerAbs.sendEmptyMessage(200);
    }
}
