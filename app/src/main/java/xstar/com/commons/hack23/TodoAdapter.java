package xstar.com.commons.hack23;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import xstar.com.commons.R;
import xstar.com.library.commons.ViewUtils;

/**
 * author: xstar
 * since: 2017-05-02.
 */

public class TodoAdapter extends CursorAdapter {

    private static final String[] PROJECTION_IDS_TITLE_AND_STATUS =
            new String[]{
                    TodoContentProvider.COLUMN_ID,
                    TodoContentProvider.COLUMN_TITLE,
                    TodoContentProvider.COLUMN_STATUS_FLAG};


    Activity mActivity;

    public TodoAdapter(Activity context) {
        super(context, getMamagedQuery(context), true);
        mActivity = context;
    }

    private static Cursor getMamagedQuery(Activity context) {
        CursorLoader cursorLoader = new CursorLoader(context, TodoContentProvider.CONTENT_URI, PROJECTION_IDS_TITLE_AND_STATUS,
                TodoContentProvider.COLUMN_STATUS_FLAG + "!=" + StatusFlag.DELETE, null, TodoContentProvider.DEFAULT_SORT_ORDER);
        return cursorLoader.loadInBackground();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.todos_item_layout, null, false);
        ViewHolder viewHolder=new ViewHolder();
        viewHolder.serial= ViewUtils.find(view, R.id.serial);
        viewHolder.todo_name= ViewUtils.find(view, R.id.todo_name);
        viewHolder.delete= ViewUtils.find(view, R.id.delete);
        view.setTag(viewHolder);
        return view;
    }

    int id_index = 0;
    int title_index = 2;
    int flag_index = 2;

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder= (ViewHolder) view.getTag();
        TextView serial = ViewUtils.find(view, R.id.serial);
        TextView todo_name = ViewUtils.find(view, R.id.todo_name);
        Button delete = ViewUtils.find(view, R.id.delete);
        holder.serial.setText(cursor.getString(id_index));
        holder.todo_name.setText(cursor.getString(title_index));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public static class ViewHolder{
        TextView serial,todo_name;
        Button delete;
    }
}
