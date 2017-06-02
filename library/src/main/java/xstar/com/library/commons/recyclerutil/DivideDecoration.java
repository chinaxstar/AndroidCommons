package xstar.com.library.commons.recyclerutil;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

/**
 * author: xstar
 * since: 2017-04-11.
 */

public class DivideDecoration extends RecyclerView.ItemDecoration {
    private int orientation;
    private int divide;
    private Drawable divideDrawer;
    private ManagerType type;


    public DivideDecoration(Drawable divideDrawable, int orientation, int divide, ManagerType type) {
        this.divideDrawer = divideDrawable;
        this.orientation = orientation;
        this.divide = divide;
        this.type = type;
    }

    public DivideDecoration(Drawable divideDrawable, int orientation, int divide) {
        this(divideDrawable, orientation, divide, ManagerType.Linear);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (type == ManagerType.Linear) {
            if (orientation == LinearLayout.HORIZONTAL) {
                drawHorizontal(c, parent, state);
            } else drawVertical(c, parent, state);
        } else {
            drawHorizontal(c, parent);
            drawVertical(c, parent);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (ManagerType.Linear == type) {
            if (orientation == LinearLayout.HORIZONTAL) {
                outRect.set(0, 0, divide, 0);
            } else {
                outRect.set(0, 0, 0, divide);
            }
        } else {
            int itemPosition = parent.getChildLayoutPosition(view);
            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();
            if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
            {
                outRect.set(0, 0, getDivideW(), 0);
            } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
            {
                outRect.set(0, 0, 0, getDivideH());
            } else {
                outRect.set(0, 0, getDivideW(),
                        getDivideH());
            }
        }

    }

    private int getDivideW() {
        int width = divideDrawer.getIntrinsicWidth();
        if (width <= 0) width = divide;
        return width;
    }

    private int getDivideH() {
        int width = divideDrawer.getIntrinsicHeight();
        if (width <= 0) width = divide;
        return width;
    }

    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            int width = divideDrawer.getIntrinsicWidth();
//            if (width > child.getWidth()) width = child.getWidth();
            if (width <= 0) width = divide;
            final int right = left + width;
            divideDrawer.setBounds(left, top, right, bottom);
            divideDrawer.draw(c);
        }

    }

    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            int height = divideDrawer.getIntrinsicHeight();
//            if (height > child.getHeight()) height = child.getHeight();
            if (height <= 0) height = divide;
            final int bottom = top + height;
            divideDrawer.setBounds(left, top, right, bottom);
            divideDrawer.draw(c);
        }
    }

    private int getSpanCount(RecyclerView recyclerView) {
        if (type == ManagerType.Grid) {
            return ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
        } else if (type == ManagerType.Stagger)
            return ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
        return 0;
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + getDivideW();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + getDivideH();
            divideDrawer.setBounds(left, top, right, bottom);
            divideDrawer.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + getDivideW();

            divideDrawer.setBounds(left, top, right, bottom);
            divideDrawer.draw(c);
        }
    }
}
