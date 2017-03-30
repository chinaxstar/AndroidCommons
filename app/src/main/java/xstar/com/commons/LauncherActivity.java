package xstar.com.commons;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xstar.com.commons.adapter.StringAdapter;
import xstar.com.commons.entity.PagesItem;
import xstar.com.library.commons.AndroidHelper;
import xstar.com.library.commons.Views;

public class LauncherActivity extends Activity {

    ListView list;
    List<PagesItem> itemList=new ArrayList<>();
    StringAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        AndroidHelper.init(this);
        list= Views.find(this,R.id.list);
        adapter=new StringAdapter(this);
        haveData();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AndroidHelper.startActivity(LauncherActivity.this,itemList.get(position).getClzz());
            }
        });
    }

    private void haveData() {
        itemList.add(new PagesItem("ViewPager tranformer",MainActivity.class));
        itemList.add(new PagesItem("dialog test",DialogFragmentActivity.class));
        itemList.add(new PagesItem("dialog activity test",DialogActivity.class));
        itemList.add(new PagesItem("变大按钮 test",ButtonToBiggerActivity.class));
        itemList.add(new PagesItem("超出视图 test",MenuListActivity.class));
        itemList.add(new PagesItem("二维码生成",TwoDGenereteActivity.class));
        itemList.add(new PagesItem("camera",CameraActivity.class));
        itemList.add(new PagesItem("buttontest",ButtonTestActivity.class));
        itemList.add(new PagesItem("跑马灯",MarqeeTextActivity.class));
        itemList.add(new PagesItem("截屏",CutScreenActivity.class));
        adapter.setItemsList(itemList);
    }

}
