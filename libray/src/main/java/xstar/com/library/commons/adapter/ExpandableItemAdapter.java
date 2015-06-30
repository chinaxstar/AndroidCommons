package xstar.com.library.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * 　expandablelistview adapter
 *
 * Created by xstar on 2015/6/18.
 */
public abstract class ExpandableItemAdapter<Parent, Child> extends BaseExpandableListAdapter
{
	private Context				context;
	private LayoutInflater		layoutInflater;
	private List<Parent>		groupList;
	private List<List<Child>>	childList;

	public ExpandableItemAdapter(Context context)
	{
		this.context = context;
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getGroupCount()
	{
		return groupList == null ? 0 : groupList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		return childList.get(groupPosition) == null ? 0 : childList.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		return groupList == null ? null : groupList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		return childList.get(groupPosition) == null ? null : childList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	@Override
	public boolean hasStableIds()
	{
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
	{
		Parent p = groupList.get(groupPosition);
		if (convertView == null)
		{
			convertView = createGroupView(p, groupPosition, isExpanded, parent, layoutInflater);
		}
		bindGroupView(p, groupPosition, isExpanded, convertView);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		Child child = childList.get(groupPosition).get(childPosition);
		if (convertView == null)
		{
			convertView = createChildView(child, groupPosition, childPosition, isLastChild, parent, layoutInflater);
		}
		bindChildView(child, groupPosition, childPosition, isLastChild, convertView);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return true;
	}

	public Context getContext()
	{
		return context;
	}

	public LayoutInflater getLayoutInflater()
	{
		return layoutInflater;
	}

	public void setItemList(List<Parent> groupList, List<List<Child>> childList)
	{
		this.groupList = groupList;
		this.childList = childList;
		notifyDataSetChanged();
	}

	public List<Parent> getGroupList()
	{
		return groupList;
	}

	public List<List<Child>> getChildList()
	{
		return childList;
	}

	public abstract View createGroupView(Parent p, int groupPosition, boolean isExpanded, ViewGroup parent, LayoutInflater layoutInflater);

	public abstract void bindGroupView(Parent p, int groupPosition, boolean isExpanded, View convertView);

	public abstract View createChildView(Child child, int groupPosition, int childPosition, boolean isLastChild, ViewGroup parent, LayoutInflater layoutInflater);

	public abstract void bindChildView(Child child, int groupPosition, int childPosition, boolean isLastChild, View convertView);
}
