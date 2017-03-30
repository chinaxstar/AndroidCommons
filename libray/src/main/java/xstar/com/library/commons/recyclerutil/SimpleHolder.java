package xstar.com.library.commons.recyclerutil;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xstar.com.library.commons.Views;

/**
 * Created by xstar on 2016-10-19.
 */
public class SimpleHolder extends RecyclerView.ViewHolder {
    public SimpleHolder(View itemView) {
        super(itemView);
    }

    public SimpleHolder(View itemView, int... resIds) {
        super(itemView);
        initRes(resIds);
    }

    public SimpleHolder(View itemView, List<Integer> resIds) {
        super(itemView);
        initRes(resIds);
    }

    private Map<Integer, View> innerMap = new HashMap<>();
    private Map<String, Object> bindObj = new HashMap<>();

    public void initRes(int... resIds) {
        if (resIds != null) {
            for (int res : resIds) {
                innerMap.put(res, Views.find(this.itemView, res));
            }
        }
    }

    public void initRes(List<Integer> resIds) {
        if (resIds != null) {
            for (int res : resIds) {
                innerMap.put(res, Views.find(this.itemView, res));
            }
        }
    }

    public <T extends View> T getViewChild(int resId) {
        return (T) innerMap.get(resId);
    }

    public View getView(int resId) {
        return innerMap.get(resId);
    }

    public TextView getTextView(int resId) {
        return (TextView) innerMap.get(resId);
    }
    public RecyclerView getRecyclerView(int resId) {
        return (RecyclerView) innerMap.get(resId);
    }
    public EditText getEditText(int resId) {
        return (EditText) innerMap.get(resId);
    }

    public Button getButton(int resId) {
        return (Button) innerMap.get(resId);
    }

    public ImageView getImageView(int resId) {
        return (ImageView) innerMap.get(resId);
    }

    public void bindObj(Object obj) {
        bindObj.put(obj.getClass().getName(), obj);
    }

    public void bindObj(String name, Object obj) {
        bindObj.put(name, obj);
    }

    public <T> T getObj(String name) {
        if (!bindObj.containsKey(name))return null;
        return (T)bindObj.get(name);
    }
}
