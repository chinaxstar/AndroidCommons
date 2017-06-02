package xstar.com.library.commons.recyclerutil;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import xstar.com.library.commons.AndroidHelper;


/**
 * Created by xstar on 2016-11-01.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {
    private int hSpace, vSpace;

    public SpaceDecoration(int hSpace, int vSpace) {
        this.hSpace = AndroidHelper.dpToPiexl(hSpace);
        this.vSpace = AndroidHelper.dpToPiexl(vSpace);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = hSpace >> 1;
        outRect.right = hSpace >> 1;
        outRect.top = vSpace >> 1;
        outRect.bottom = vSpace >> 1;
    }
}
