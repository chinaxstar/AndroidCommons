package xstar.com.commons;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import xstar.com.library.commons.ViewUtils;

public class DataShowActivity extends Activity {

    LinearLayout content_linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        content_linear= ViewUtils.find(this,R.id.content_linear);
        Bitmap bitmap=getIntent().getParcelableExtra("bitmap");
        content_linear.addView(showBitmap(bitmap));
    }

    public ImageView showBitmap(Bitmap bitmap){
        ImageView imageView=new ImageView(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER;
        imageView.setLayoutParams(params);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }


}
