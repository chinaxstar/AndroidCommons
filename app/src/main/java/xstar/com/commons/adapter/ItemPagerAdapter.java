package xstar.com.commons.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>viewpager 的抽象pageradapter createview初始化视图 onscroll 方法在翻页时调用 由于滑动动画</p>
 *
 * Created by xstar on 2015/6/29.
 */
public abstract class ItemPagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener
{

	private List<T>					itemList;
	private Context					context;
	private ViewPager owner;
	private Map<Integer, Object>	objs	= new HashMap<>();
	private LayoutInflater			inflater;

	public ItemPagerAdapter(Context context, ViewPager owner)
	{
		this.context = context;
		this.owner = owner;
		inflater = LayoutInflater.from(context);
        this.owner.setOnPageChangeListener(this);
	}

	@Override
	public int getCount()
	{
		return itemList == null ? 0 : itemList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object o)
	{
		return view.equals(o);
	}

	@Override
	public int getItemPosition(Object object)
	{
		return itemList == null ? -1 : itemList.indexOf(object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		container.removeView((View) objs.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		T t = itemList.get(position);
		View view = createItem(position, t,inflater);
		if (!objs.containsValue(view))
		{
			objs.put(position, view);
			container.addView(view);
		}
		return view;
	}

	@Override
	public void onPageScrolled(int i, float offset, int offsetPiexl)
	{
		View left = findViewFromObject(i);
		View right = findViewFromObject(i + 1);
		onScroll(left, right, i, offset, offsetPiexl);
	}

	@Override
	public void onPageSelected(int i)
	{

	}

	@Override
	public void onPageScrollStateChanged(int i)
	{

	}

	public void setItemList(List<T> itemList)
	{
		this.itemList = itemList;
	}

	public List<T> getItemList()
	{
		return itemList;
	}

	public Context getContext()
	{
		return context;
	}

	public LayoutInflater getInflater()
	{
		return inflater;
	}

	public View findViewFromObject(int position)
	{
		Object o = objs.get(Integer.valueOf(position));
		if (o == null)
		{
			return null;
		}
		View v;
		for (int i = 0; i < owner.getChildCount(); i++)
		{
			v = owner.getChildAt(i);
			if (isViewFromObject(v, o)) return v;
		}
		return null;
	}

	public abstract View createItem(int position, T t,LayoutInflater inflater);

	public abstract void onScroll(View left, View right, int position, float offset, int offsetPiexl);
}
