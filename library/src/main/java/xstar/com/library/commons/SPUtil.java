package xstar.com.library.commons;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

import java.util.Map;

/**
 * @author: xstar
 * @since: 2017-05-23.
 * SharePreference辅助类
 */

public class SPUtil {
    /**
     * 保存在手机里面的文件名
     */
    public static final String DEFAULT_FILE_NAME = "share_data";
    private SharedPreferences sp;
    private Context context;

    private SPUtil() {
    }

    public static SPUtil sp(String fileName, int mode) {
        SPUtil spUtil = new SPUtil();
        spUtil.context = AppUtils.getInstance().app();
        if (spUtil.context != null) spUtil.sp = spUtil.context.getSharedPreferences(fileName, mode);
        return spUtil;
    }

    public static SPUtil sp() {
        return sp(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public SPUtil put(String key, Object object) {
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        return this;
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public <T> T get(String key, T defaultObject) {
        if (sp != null) {
            if (defaultObject instanceof String) {
                String temp = (String) defaultObject;
                return (T) sp.getString(key, temp);
            } else if (defaultObject instanceof Integer) {
                Integer temp = (Integer) defaultObject;
                temp = sp.getInt(key, temp);
                return (T) temp;
            } else if (defaultObject instanceof Boolean) {
                Boolean temp = (Boolean) defaultObject;
                temp = sp.getBoolean(key, temp);
                return (T) temp;
            } else if (defaultObject instanceof Float) {
                Float temp = (Float) defaultObject;
                temp = sp.getFloat(key, temp);
                return (T) temp;
            } else if (defaultObject instanceof Long) {
                Long temp = (Long) defaultObject;
                temp = sp.getLong(key, temp);
                return (T) temp;
            }
        }

        return defaultObject;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public SPUtil remove(String key) {
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(key);
        }
        return this;
    }

    public void apply() {
        if (sp == null) return;
        SharedPreferences.Editor editor = sp.edit();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    /**
     * 清除所有数据
     */
    public SPUtil clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        return this;
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        if (sp == null) return false;
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        if (sp == null) return null;
        return sp.getAll();
    }
}
