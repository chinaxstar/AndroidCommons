package xstar.com.library.commons.javacommons;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: xstar
 * @since: 2016-10-19.
 * 非空判断
 */
public class Empty {
    public static boolean notEmpty(CharSequence sequence) {
        return sequence != null && sequence.length() > 0;
    }

    public static boolean notEmpty(String str) {
        if (str != null) str = str.trim();
        return notEmpty((CharSequence) str);
    }

    public static boolean notEmpty(Iterator iterator) {
        return iterator != null && iterator.hasNext();
    }

    public static boolean notEmpty(Iterable iterable) {
        return iterable != null && iterable.iterator().hasNext();
    }

    public static boolean notEmpty(Collection collection) {
        return collection!=null&&collection.size()>0;
    }

    public static boolean notEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    public static boolean notEmpty(String[] args) {
        return args != null && args.length > 0;
    }

    public static <T> boolean notEmpty(T[] args) {
        return args != null && args.length > 0;
    }
}
