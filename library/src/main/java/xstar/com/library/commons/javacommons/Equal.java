package xstar.com.library.commons.javacommons;

/**
 * @author: xstar
 * @since: 2017-05-25.
 */

public class Equal {
    public static boolean verify(Object except,Object obj){
        if (except==obj)return true;
        if (except!=null)return except.equals(obj);
        else return obj.equals(except);
    }
}
