package xstar.com.commons;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xstar.com.library.commons.ViewUtils;

public class DialogFragmentActivity extends Activity {

    ListView list_item;
    ArrayAdapter<String> adapter;
    List<String> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        list_item= ViewUtils.find(this,R.id.list_item);
        data.add("点按钮弹出框");
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list_item.setAdapter(adapter);
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
//                        startActivity(new Intent(getApplicationContext(),DialogActivity.class));
                        showDisalog();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
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

    private void  showDisalog(){
        View view=LayoutInflater.from(this).inflate(R.layout.dialog_layout,null);
        AlertDialog alertDialog=new AlertDialog.Builder(this,R.style.DialogTheme).setView(view).create();
        alertDialog.show();
//        alertDialog.getWindow().setContentView(view);
    }
}
