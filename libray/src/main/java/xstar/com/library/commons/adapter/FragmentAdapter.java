package xstar.com.library.commons.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import xstar.com.library.commons.Empty;

/**
 * Created by Administrator on 2015/9/22.
 */
public class FragmentAdapter<T extends Fragment> extends FragmentPagerAdapter
{
	private List<T>				itemList;
	private List<CharSequence>	titles;

	public FragmentAdapter(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int position)
	{
		return Empty.isCollectionEmpty(itemList) ? null : itemList.get(position);
	}

	@Override
	public int getCount()
	{
		return Empty.isCollectionEmpty(itemList) ? 0 : itemList.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return Empty.isCollectionEmpty(titles) ? null : titles.get(position);
	}

	@Override
	public void startUpdate(ViewGroup container)
	{
		super.startUpdate(container);
		if (onUpdateListner != null)
		{
			onUpdateListner.start();
		}
	}

	@Override
	public void finishUpdate(ViewGroup container)
	{
		super.finishUpdate(container);
		if (onUpdateListner != null)
		{
			onUpdateListner.onFinish();
		}
	}

	public List<T> getItemList()
	{
		return itemList;
	}

	public void setItemList(List<T> itemList)
	{
		this.itemList = itemList;
	}

	public List<CharSequence> getTitles()
	{
		return titles;
	}

	public void setTitles(List<CharSequence> titles)
	{
		this.titles = titles;
	}

	private interface OnUpdateListner
	{
		void start();

		void onFinish();
	}

	private OnUpdateListner onUpdateListner;

	public OnUpdateListner getOnUpdateListner()
	{
		return onUpdateListner;
	}

	public void setOnUpdateListner(OnUpdateListner onUpdateListner)
	{
		this.onUpdateListner = onUpdateListner;
	}
}
