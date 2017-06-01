package xstar.com.commons;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import xstar.com.commons.entity.PagesItem;
import xstar.com.library.commons.AndroidHelper;
import xstar.com.library.commons.ViewUtils;
import xstar.com.library.commons.recyclerutil.ManagerType;
import xstar.com.library.commons.recyclerutil.RvBuilder;
import xstar.com.library.commons.recyclerutil.SimpleAdapter;
import xstar.com.library.commons.recyclerutil.SimpleHolder;

public class LauncherActivity extends Activity {

    List<PagesItem> itemList = new ArrayList<>();
    RecyclerView list_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        AndroidHelper.init(this);
        list_rv = ViewUtils.find(this, R.id.list_rv);
        haveData();
        simpleAdapter.setItemIds(R.id.text1);
        RvBuilder.Builder().setAdapter(simpleAdapter)
                .setDivide(10)
                .setDivideColor(Color.CYAN)
                .setOrientation(ManagerType.Grid,RvBuilder.VERTICAL,3)
                .build(list_rv);
    }

    private void haveData() {
        itemList.add(new PagesItem("ViewPager tranformer", MainActivity.class));
        itemList.add(new PagesItem("dialog test", DialogFragmentActivity.class));
        itemList.add(new PagesItem("dialog activity test", DialogActivity.class));
        itemList.add(new PagesItem("变大按钮 test", ButtonToBiggerActivity.class));
        itemList.add(new PagesItem("超出视图 test", MenuListActivity.class));
        itemList.add(new PagesItem("二维码生成", TwoDGenereteActivity.class));
        itemList.add(new PagesItem("camera", CameraActivity.class));
        itemList.add(new PagesItem("buttontest", ButtonTestActivity.class));
        itemList.add(new PagesItem("跑马灯", MarqeeTextActivity.class));
        itemList.add(new PagesItem("截屏", CutScreenActivity.class));
        itemList.add(new PagesItem("Hack50", Hack50Activity.class));
        itemList.add(new PagesItem("Settings", SettingsActivity.class));
        simpleAdapter.setItemList(itemList);
    }

    SimpleAdapter<PagesItem> simpleAdapter = new SimpleAdapter<PagesItem>() {
        {
            setLayout_id(R.layout.simple_string_item_layout);
        }

        @Override
        public void onBind(SimpleHolder holder, final int position, PagesItem item) {
            holder.getTextView(R.id.text1).setText(item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AndroidHelper.startActivity(LauncherActivity.this, itemList.get(position).getClzz());
                }
            });
        }
    };

}
