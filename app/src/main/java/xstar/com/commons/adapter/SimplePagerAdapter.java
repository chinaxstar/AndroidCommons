package xstar.com.commons.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import xstar.com.commons.R;
import xstar.com.library.commons.RandUtils;
import xstar.com.library.commons.ViewUtils;
import xstar.com.library.commons.adapter.ItemPagerAdapter;

/**
 * @author: xstar
 * @since: 15-6-30.
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
        TextView textView= ViewUtils.find(view,R.id.page_num);
        textView.setText(s);
        textView.setBackgroundColor(RandUtils.nextColorInt());
        return view;
    }

}
