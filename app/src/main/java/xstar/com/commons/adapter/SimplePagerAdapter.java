package xstar.com.commons.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import xstar.com.commons.R;
import xstar.com.library.commons.RandomHelper;
import xstar.com.library.commons.Views;
import xstar.com.library.commons.adapter.ItemPagerAdapter;

/**
 * Created by xstar on 15-6-30.
 */
public class SimplePagerAdapter extends ItemPagerAdapter<String>{
    public SimplePagerAdapter(Context context,List<String> nums) {
        super(context);
        setItemList(nums);
    }

    @Override
    public View createItem(int position, String s, LayoutInflater inflater) {
        Log.e("createItem",s+"/"+position);
        View view =inflater.inflate(R.layout.viewpager_item,null);
        TextView textView= Views.find(view,R.id.page_num);
        textView.setText(s);
        textView.setBackgroundColor(RandomHelper.nextColorInt());
        return view;
    }

}
