package xstar.com.library.commons.recyclerutil;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import xstar.com.library.commons.javacommons.Empty;

/**
 * Created by xstar on 2016-07-22.
 */
public abstract class ObjAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> itemList;

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBind(holder, position, itemList.get(position));
    }

    public abstract void onBind(VH holder, int position, T item);

    @Override
    public int getItemCount() {
        return Empty.notEmpty(itemList) ? itemList.size() : 0;
    }

    public interface OnItemClickListner {
        void onItemClick(View item, int position);
    }

    private OnItemClickListner onItemClickListner;

    public OnItemClickListner getOnItemClickListner() {
        return onItemClickListner;
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    protected SparseArray<Boolean> selecteds = new SparseArray<>();

    public void setSelecteds(SparseArray<Boolean> selecteds) {
        this.selecteds = selecteds;
    }

    public SparseArray<Boolean> getSelecteds() {
        return selecteds;
    }

    public List<T> getSelectItems() {
        List<T> paymentItems = new ArrayList<>();
        for (int i = 0; i < selecteds.size(); i++) {
            if (selecteds.get(i)) paymentItems.add(getItemList().get(i));
        }
        return paymentItems;
    }

    public void setSelected(int position) {
        selecteds.put(position, true);
        if (!isMulti && singleSelected != -1 && singleSelected != position) {
            removeSelected(singleSelected);
        }
        singleSelected = position;
    }

    public void setSelectAll() {
        setAll(true);
    }

    public void clearSelect() {
        setAll(false);
    }

    private void setAll(boolean bool) {
        if (isMulti) {
            for (int i = 0; i < getItemCount(); i++) {
                selecteds.put(i, bool);
            }
        }
    }

    public void removeSelected(int position) {
        selecteds.put(position, false);
        if (isMulti) if (singleSelected == position) singleSelected = -1;
    }

    public boolean isSelected(int position) {
        return selecteds.get(position)==null?false:selecteds.get(position);
    }

    private boolean isMulti = true;
    private int singleSelected = -1;

    public boolean isMulti() {
        return isMulti;
    }

    public void setMulti(boolean multi) {
        isMulti = multi;
    }

    public int getSingleSelected() {
        return singleSelected;
    }

    public void setSingleSelected(int singleSelected) {
        this.singleSelected = singleSelected;
    }

    public interface OnItemLongClickListner {
        void onItemLongClick(View item, int position);
    }

    private OnItemLongClickListner onItemLongClickListner;

    public OnItemLongClickListner getOnItemLongClickListner() {
        return onItemLongClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    // with holder
    public interface OnItemClickListnerWithHolder {
        void onItemClick(RecyclerView.ViewHolder viewHolder, View item, int position);
    }

    private OnItemClickListnerWithHolder onItemClickListnerWithHolder;

    public OnItemClickListnerWithHolder getOnItemClickListnerWithHolder() {
        return onItemClickListnerWithHolder;
    }

    public void setOnItemClickListnerWithHolder(OnItemClickListnerWithHolder onItemClickListner) {
        this.onItemClickListnerWithHolder = onItemClickListner;
    }

    public interface OnItemLongClickListnerWithHolder {
        void onItemLongClick(RecyclerView.ViewHolder viewHolder, View item, int position);
    }

    private OnItemLongClickListnerWithHolder onItemLongClickListnerWithHolder;

    public OnItemLongClickListnerWithHolder getOnItemLongClickListnerWithHolder() {
        return onItemLongClickListnerWithHolder;
    }

    public void setOnItemLongClickListnerWithHolder(OnItemLongClickListnerWithHolder onItemLongClickListner) {
        this.onItemLongClickListnerWithHolder = onItemLongClickListner;
    }

    private int layout_id;

    public int getLayout_id() {
        return layout_id;
    }

    public void setLayout_id(int layout_id) {
        this.layout_id = layout_id;
    }

    @Override
    public int getItemViewType(int position) {
        return layout_id;
    }
}
