package xstar.com.commons;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xstar.com.library.commons.Views;

/**
 * Created by Administrator on 2015/8/24.
 */
public class DialogActivity extends Activity {
    private String text;
    private DisplayMetrics metrics;
    private int width, height;
    RelativeLayout content_view;
    Button negative,positive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (metrics != null) {
            metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            width = (int) (metrics.widthPixels * 0.628f);
            height = (int) (metrics.heightPixels * 0.628f);
        }
        setContentView(R.layout.dialog_layout);
        content_view = Views.find(this, R.id.content_view);
        TextView content_text = Views.find(this, R.id.content_text);
        text="但按钮弹出框";
        if (!TextUtils.isEmpty(text)) {
            content_text.setText(text);
        }
        negative = Views.find(this, R.id.negative);
        positive = Views.find(this, R.id.positive);
    }

    public void cancel(View view) {
        finish();
    }

    public void ensure(View view) {
        finish();
    }
}
