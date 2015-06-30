package xstar.com.commons.adapter;

import java.lang.ref.WeakReference;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 *
 * <P>
 * adapterview 的适配器
 * </P>
 * Created by xstar on 2015/6/17.
 */
public abstract class ItemsAdapter<T> extends BaseAdapter
{
	private List<T>							mItemsList;
	private final WeakReference<Context>	contextRef;
	private final LayoutInflater			layoutInflater;

	public ItemsAdapter(Context context)
	{
		this.contextRef = new WeakReference(context);
		this.layoutInflater = LayoutInflater.from(context);
	}

	public void setItemsList(List<T> list)
	{
		this.mItemsList = list;
		this.notifyDataSetChanged();
	}

	public List<T> getItemsList()
	{
		return this.mItemsList;
	}

	protected Context getContext()
	{
		return (Context) this.contextRef.get();
	}

	protected LayoutInflater getLayoutInflater()
	{
		return this.layoutInflater;
	}

	public int getCount()
	{
		return this.mItemsList == null ? 0 : this.mItemsList.size();
	}

	public T getItem(int position)
	{
		return this.mItemsList != null && position >= 0 && position < this.mItemsList.size() ? this.mItemsList.get(position) : null;
	}

	public T getItem(long id)
	{
		return this.getItem((int) id);
	}

	public long getItemId(int pos)
	{
		return (long) pos;
	}

	public View getView(int pos, View convertView, ViewGroup parent)
	{
		T item = this.mItemsList.get(pos);
		if (convertView == null)
		{
			convertView = this.createView(item, pos, parent, this.layoutInflater);
		}
		this.bindView(item, pos, convertView);
		return convertView;
	}

	protected abstract View createView(T item, int position, ViewGroup parent, LayoutInflater layoutInflater);

	protected abstract void bindView(T item, int position, View view);
}
