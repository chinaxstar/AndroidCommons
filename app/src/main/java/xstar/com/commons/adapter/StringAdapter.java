package xstar.com.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xstar.com.commons.entity.PagesItem;
import xstar.com.library.commons.adapter.ItemsAdapter;

/**
 * Created by Administrator on 2015/8/25.
 */
public class StringAdapter extends ItemsAdapter<PagesItem> {
    public StringAdapter(Context context) {
        super(context);
    }

    @Override
    protected View createView(PagesItem item, int position, ViewGroup parent, LayoutInflater layoutInflater) {
        View view=layoutInflater.inflate(android.R.layout.simple_list_item_1,null);
        return view;
    }

    @Override
    protected void bindView(PagesItem item, int position, View view) {
        if(view instanceof TextView){
            ((TextView) view).setText(item.getName());
        }
    }
}
