package xstar.com.library.commons.recyclerutil;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import static xstar.com.library.commons.recyclerutil.ManagerType.Grid;

/**
 * author: xstar
 * since: 2017-04-11.
 */

public class RvBuilder {
    private RvBuilder() {
    }

    public static RvBuilder Builder() {
        return new RvBuilder();
    }

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemDecoration itemDecoration;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    //行间隔
    private int divide;

    private Drawable divideDrawer = new ColorDrawable(0);

    //方向
    private int orientation = VERTICAL;
    private ManagerType managerType = ManagerType.Linear;
    //行或列的数目
    private int spanCount;

    public RvBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public RvBuilder setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        return this;
    }

    public RvBuilder setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
        return this;
    }

    public RvBuilder setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public RvBuilder setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public RvBuilder setDivide(int divide) {
        this.divide = divide;
        return this;
    }

    public RvBuilder setDivideColor(int divideColor) {
        this.divideDrawer = new ColorDrawable(divideColor);
        return this;
    }

    public RvBuilder setDivideDrawer(Drawable divideDrawer) {
        this.divideDrawer = divideDrawer;
        return this;
    }

    /**
     * spanCount<=0为Linear
     * 否则为grid
     *
     * @param orientation 方向
     * @param spanCount   数目
     * @return
     */
    public RvBuilder setOrientation(int orientation, int spanCount) {
        setOrientation(ManagerType.Linear, orientation, spanCount);
        return this;
    }

    public RvBuilder setOrientation(ManagerType type, int orientation, int spanCount) {
        this.managerType = type;
        this.orientation = orientation;
        this.spanCount = spanCount;
        return this;
    }

    public void build(RecyclerView recyclerView) {
        setRecyclerView(recyclerView).build();
    }

    public void build() {
        if (recyclerView == null) throw new NullPointerException("no recyclerView !");
        if (layoutManager == null)
            layoutManager = managerBuild();
        if (itemDecoration == null) {
            itemDecoration = decorationBuild();
        }
        if (itemAnimator == null) {
            itemAnimator = animatorBuild();
        }
        if (adapter != null) {
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setItemAnimator(itemAnimator);
            recyclerView.setAdapter(adapter);
        }

    }

    private RecyclerView.ItemAnimator animatorBuild() {
        itemAnimator = new DefaultItemAnimator();
        return itemAnimator;
    }


    private RecyclerView.LayoutManager managerBuild() {
        RecyclerView.LayoutManager layoutManager = null;
        switch (managerType) {
            case Grid:
                layoutManager = new GridLayoutManager(recyclerView.getContext(), spanCount, orientation, false);
                break;
            case Linear:
                layoutManager = new LinearLayoutManager(recyclerView.getContext(), orientation, false);
                break;
            case Stagger:
                layoutManager = new StaggeredGridLayoutManager(spanCount, orientation);
                break;


        }
        return layoutManager;
    }

    private RecyclerView.ItemDecoration decorationBuild() {
        if (divideDrawer == null) {
            divideDrawer = new ColorDrawable(0);
        }
        if (divide > 0) {
            itemDecoration = new DivideDecoration(divideDrawer, orientation, divide,managerType);
        } else itemDecoration = new RecyclerView.ItemDecoration() {
        };
        return itemDecoration;
    }
}
