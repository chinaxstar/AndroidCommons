package xstar.com.library.commons.javacommons;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author: xstar
 * @since: 2017-05-25.
 */

public class IOUtils {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Closeable... closees){
        if (closees!=null){
            for (Closeable closeable:closees)close(closeable);
        }
    }



}
