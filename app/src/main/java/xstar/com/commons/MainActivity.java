package xstar.com.commons;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import xstar.com.commons.adapter.SimplePagerAdapter;
import xstar.com.commons.transformer.CubeTransformer;
import xstar.com.commons.transformer.ZoomOutTransformer;
import xstar.com.library.commons.AndroidHelper;
import xstar.com.library.commons.Views;


public class MainActivity extends Activity {
    ViewPager viewPager;
    List<String> nums=new ArrayList<>();
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= Views.find(this,R.id.viewpagers);
        for(int i=1;i<101;i++){
            nums.add("PAGE"+i);
        }
        pagerAdapter=new SimplePagerAdapter(this,nums);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, new CubeTransformer());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.cube:
                viewPager.setPageTransformer(true,new CubeTransformer());
                break;
            case R.id.scale:
                viewPager.setPageTransformer(true,new ZoomOutTransformer());
                break;
            case R.id.tobigger:
                AndroidHelper.startActivity(this, ButtonToBiggerActivity.class);
                break;
            case R.id.menu_list:
                AndroidHelper.startActivity(this,MenuListActivity.class);
                break;
            case R.id.dialog_test:
                AndroidHelper.startActivity(this,DialogFragmentActivity.class);
                break;

        }
        return true;
    }
}
