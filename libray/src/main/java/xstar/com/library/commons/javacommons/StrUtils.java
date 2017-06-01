package xstar.com.library.commons.javacommons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author: xstar
 * @since: 2017-05-25.
 * 字符串辅助类
 */

public class StrUtils {
    private static final StringBuilder SB = new StringBuilder();
    private static final String DEFAULT_SPACE = ",";


    public static String toStr(int[] ints) {
        List<Integer> list = new ArrayList<>();
        if (ints != null && ints.length > 0) {
            for (int i = 0; i < ints.length; i++) {
                list.add(ints[i]);
            }
        }
        return toStr(list);
    }

    public static String toStr(float[] floats) {
        List<Float> list = new ArrayList<>();
        if (floats != null && floats.length > 0) {
            for (int i = 0; i < floats.length; i++) {
                list.add(floats[i]);
            }
        }
        return toStr(list);
    }

    public static <T> String toStr(T[] ts) {
        return toStr(Arrays.asList(ts));
    }

    public static String toStr(Throwable e) {
        StackTraceElement[] elements = e.getStackTrace();
        String title = e.toString();
        SB.setLength(0);
        if (Empty.notEmpty(title)) SB.append(title).append("\n");
        if (Empty.notEmpty(elements)) {
            int len = elements.length;
            for (int i = 0; i < len; i++) {
                SB.append(elements[i].toString()).append("\n");
            }
        }
        return SB.toString();
    }

    public static <T> String toStr(Collection<T> collection, String spaceText) {
        SB.setLength(0);
        if (!Empty.notEmpty(spaceText)) spaceText = DEFAULT_SPACE;
        if (collection != null) {
            SB.append("[");
            for (T t : collection) {
                SB.append(String.valueOf(t)).append(spaceText);
            }
            if (collection.size() > 0) deleteLastStr(SB, spaceText);
            SB.append("]");
        }
        return SB.toString();
    }

    public static <T> String toStr(Collection<T> collection) {
        return toStr(collection, DEFAULT_SPACE);
    }

    public static <K, V> String toStr(Map<K, V> map, String spaceText) {
        SB.setLength(0);
        if (!Empty.notEmpty(spaceText)) spaceText = DEFAULT_SPACE;
        if (map != null) {
            SB.append("{");
            for (Map.Entry<K, V> entry : map.entrySet()) {
                SB.append(entry.getKey()).append("=").append(entry.getValue()).append(spaceText);
            }
            if (map.size() > 0) deleteLastStr(SB, spaceText);
            SB.append("}");
        }
        return SB.toString();
    }

    public static <K, V> String toStr(Map<K, V> map) {
        return toStr(map, DEFAULT_SPACE);
    }


    private static StringBuilder deleteLastStr(StringBuilder builder, CharSequence sequence) {
        return builder.delete(builder.length() - sequence.length(), builder.length());
    }


    public static String formatF(double d, int place) {
        if (place < 0) place = 0;
        return String.format(splice("%.", place, "f"), d);
    }

    /**
     * 拼接字符串
     *
     * @param args
     * @return
     */
    public static String splice(Object... args) {
        SB.setLength(0);
        if (Empty.notEmpty(args)) {
            for (Object o : args) {
                SB.append(o);
            }
        }
        return SB.toString();
    }

    /**
     * 字符串替换标志生成新字符串
     *
     * @param text      带有替换标志的字符串
     * @param placeChar 替换标志
     * @param args      顺序替换的字符串
     * @return
     */
    public static String place(String text, char placeChar, Object... args) {
        SB.setLength(0);
        if (Empty.notEmpty(text) && Empty.notEmpty(args)) {
            int len = text.length();
            int argsLen = args.length;
            char ch;
            int count = 0;
            for (int i = 0; i < len; i++) {
                ch = text.charAt(i);
                if (ch == placeChar) {
                    if (count < argsLen)
                        SB.append(String.valueOf(args[count]));
                    count++;
                } else SB.append(ch);
            }
            if (count > argsLen)
                Print.console(new IllegalStateException(splice('"', placeChar, '"', " num is excepted ", count, " but have ", argsLen)));
        }
        return SB.toString();
    }
}
