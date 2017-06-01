package xstar.com.commons.hack23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import xstar.com.commons.R;

public class Hack23Activity extends AppCompatActivity {

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack23);
        list= (ListView) findViewById(R.id.list);
    }

    public void addView(View view){
//        startActivity();
    }
}
