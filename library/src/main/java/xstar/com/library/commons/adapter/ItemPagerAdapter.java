package xstar.com.library.commons.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * viewpager pageradapter
 * createview初始化视图
 * onscroll　滑动
 * * Created by xstar on 2015/6/29.
 */
public abstract class ItemPagerAdapter<T> extends PagerAdapter {

    private List<T> itemList;
    private Context context;
    private LayoutInflater inflater;

    public ItemPagerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

    @Override
    public int getItemPosition(Object object) {
        return itemList == null ? -1 : itemList.indexOf(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        T t = itemList.get(position);
        View view = createItem(position, t, inflater);
        container.addView(view);
        return view;
    }


    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public abstract View createItem(int position, T t, LayoutInflater inflater);
}
