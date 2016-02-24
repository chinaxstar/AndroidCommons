package xstar.com.commons;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import xstar.com.library.commons.Views;
import xstar.com.library.commons.view.DiscolorButton;

/**
 * * Created by Administrator on 2015/9/28.
 */
public class ButtonTestActivity extends Activity {
    DiscolorButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_test_act);
        button= Views.find(this,R.id.button);
    }

    public void change(View view) {
        button.setBgColor(Color.YELLOW);
        button.setRadius(30);
    }
}
