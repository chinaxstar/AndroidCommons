package xstar.com.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import java.util.List;

import xstar.com.commons.R;
import xstar.com.library.commons.AndroidHelper;
import xstar.com.library.commons.Views;
import xstar.com.library.commons.adapter.ItemsAdapter;

/**
 * Created by Administrator on 2015/7/3.
 */
public class MenuAdaptr extends ItemsAdapter<String>
{
	private AbsListView.LayoutParams	selectedParams;
	private AbsListView.LayoutParams	normalParams;

	public MenuAdaptr(Context context, List<String> list)
	{
		super(context);
		setItemsList(list);
		selectedParams = new AbsListView.LayoutParams(AndroidHelper.dpToPiexl(194), AbsListView.LayoutParams.WRAP_CONTENT);
		normalParams = new AbsListView.LayoutParams(AndroidHelper.dpToPiexl(132), AbsListView.LayoutParams.WRAP_CONTENT);
	}

	@Override
	protected View createView(String item, int position, ViewGroup parent, LayoutInflater layoutInflater)
	{
		View view = layoutInflater.inflate(R.layout.menu_list_item, null);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.textView = Views.find(view, R.id.textview);
		viewHolder.underLine = Views.find(view, R.id.underLine);
		view.setTag(viewHolder);
		return view;
	}

	@Override
	protected void bindView(String item, int position, View view)
	{
		view.setBackgroundColor(0x00000000);
		view.setLayoutParams(normalParams);
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.textView.setText(item);
		holder.underLine.setVisibility(View.VISIBLE);
		if (selectedItem != -1 && selectedItem == position)
		{
			view.setLayoutParams(selectedParams);
			holder.underLine.setVisibility(View.INVISIBLE);
			view.setBackgroundResource(R.drawable.selected);
		}
	}

	public static class ViewHolder
	{
		TextView	textView;
		View		underLine;
	}

	private int	selectedItem	= -1;

	public void setSelectItem(int position)
	{
		selectedItem = position;
	}
}
