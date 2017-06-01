package xstar.com.commons;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import xstar.com.commons.adapter.MenuAdaptr;
import xstar.com.library.commons.AndroidHelper;
import xstar.com.library.commons.ViewUtils;


public class MenuListActivity extends Activity {

    ListView menu_list;
    MenuAdaptr adapter;
    DisplayMetrics dm;
    LinearLayout left_menu_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        dm= AndroidHelper.getScreenSize();
        left_menu_ll= ViewUtils.find(this,R.id.left_menu_ll);
        left_menu_ll.setLayoutParams(new RelativeLayout.LayoutParams(dm.widthPixels/3,LinearLayout.LayoutParams.MATCH_PARENT));
        menu_list = ViewUtils.find(this, R.id.menu_list);
        List<String> list=new ArrayList<>();
        for(int i=1;i<21;i++){
            list.add("item "+i);
        }
        adapter=new MenuAdaptr(this,list);
        menu_list.setAdapter(adapter);
        menu_list.setDividerHeight(0);
        menu_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void back(View view) {
        finish();
    }
}
