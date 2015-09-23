package xstar.com.commons.entity;

import android.app.Activity;

/**
 * Created by Administrator on 2015/8/25.
 */
public class PagesItem {
    private String name;
    private Class<? extends Activity> clzz;
    public PagesItem(String name,Class<? extends Activity> clzz){
        this.name=name;
        this.clzz=clzz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Activity> getClzz() {
        return clzz;
    }

    public void setClzz(Class<? extends Activity> clzz) {
        this.clzz = clzz;
    }
}
