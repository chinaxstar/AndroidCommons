package xstar.com.library.commons.javacommons;

import android.util.Log;

import java.util.Collection;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author xstra
 */
public final class StringTools {
    public static float parseFloat(String num) {
        return (float) parasDouble(num);
    }

    public static int paresInteger(String num) {
        return (int) parasDouble(num);
    }

    public static double parasDouble(String num) {
        double temp = 0;
        if (Empty.isNotEmpty(num)) {
            try {
                temp = Double.parseDouble(num);
            } catch (Exception e) {
                Log.e("paras number", "paramter is no number:" + num);
            }
        }
        return temp;
    }

    public static String formartMoney(double money, int bit) {
        if (bit == 0) {
            return money + "";
        }
        return String.format("%." + bit + "f", money);
    }

    public static String formartMoney(double money) {
        return formartMoney(money, 2);
    }

    public static String formartMoney(String money) {
        return formartMoney(parasDouble(money));
    }

    public static String getUrlWithOutIp(String url) {
        if (url == null) return null;
        int index = getIndexOf(url, "/", 3);
        return index == -1 ? null : url.substring(index, url.length());
    }

    public static int getIndexOf(String res, String str, int num) {
        int index = 0;
        if (res == null) return -1;
        for (int i = 0; i < num; i++) {
            index = res.indexOf(str, index + 1);
            if (index == -1) break;
        }
        return index;
    }

    public static String generateStringByPlaceholder(String str, String regex, String... args) {
        StringBuilder builder = new StringBuilder(str);
        int index = 0;
        int time = 0;
        int len = Empty.isArrayEmpty(args) ? 0 : args.length;
        while (index != -1) {
            index = builder.indexOf(regex);
            if (index != -1) {
                if (time < len) {
                    builder.replace(index, index + 1, args[time]);
                }
                time++;
            }
        }
        if (time != len) {
            throw new IndexOutOfBoundsException("需要替换的占位符数目与传入的字符串数目不一致！");
        }
        return builder.toString();
    }

    public static String replaceByWhy(String str, String... args) {
        return generateStringByPlaceholder(str, "?", args);
    }

    public static final String DEFULT_TASTE_GAPS = ",";
    private static StringBuilder stringBuilder = new StringBuilder();

    public static <T> String getListStr(List<T> list) {
        return getListStr(list, new ToStr<T>() {
            @Override
            public String toStr(T t) {
                return t.toString();
            }
        }, DEFULT_TASTE_GAPS);
    }

    public static <T> String getListStr(Collection<T> list, ToStr<T> toStr, String gaps) {
        if (!Empty.isNotEmpty(list)) return "";
        stringBuilder.setLength(0);
        for (T t : list) {
            stringBuilder.append(toStr.toStr(t)).append(gaps);
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }

    public interface ToStr<T> {
        String toStr(T t);
    }
}
