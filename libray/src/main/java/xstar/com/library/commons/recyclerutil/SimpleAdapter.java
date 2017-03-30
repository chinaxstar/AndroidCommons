package xstar.com.library.commons.recyclerutil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by xstar on 2016-10-19.
 */
public abstract class SimpleAdapter<T> extends ObjAdapter<T, SimpleHolder> {

    LayoutInflater layoutInflater;
    public Context mContext;
    private int[] itemIds;

    public SimpleAdapter() {
    }

    public SimpleAdapter(int... itemIds) {
        this.itemIds = itemIds;
    }


    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType != 0) {
            if (mContext == null) {
                mContext = parent.getContext();
                layoutInflater = LayoutInflater.from(mContext);
            }
            if (layoutInflater == null) return null;
            return new SimpleHolder(layoutInflater.inflate(viewType, parent, false), itemIds);
        }
        return null;
    }

    public int[] getItemIds() {
        return itemIds;
    }

    public void setItemIds(int... itemIds) {
        this.itemIds = itemIds;
    }
}
