package xstar.com.library.commons.javacommons;

/**
 * @author: xstar
 * @since: 2017-05-23.
 */

public class Assert {
    public static void notNullCheck(Object obj, String msg) {
        if (obj == null) throw new NullPointerException(msg);
    }

    public static void trueChech(boolean bool, String msg) {
        if (!bool) throw new IllegalStateException(msg);
    }

    public static void equalsCheck(Object obj1, Object obj2, String msg) {
        if ((obj1 == null || obj2 == null) && obj1 != obj2) throw new IllegalStateException(msg);
    }
}
