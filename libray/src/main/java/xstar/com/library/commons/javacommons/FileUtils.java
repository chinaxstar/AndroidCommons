package xstar.com.library.commons.javacommons;

import java.io.File;
import java.io.IOException;


/**
 * @author: xstar
 * @since: 2017-05-23.
 */

public class FileUtils {
    public static File getDictionary(String dic) {
        File file = new File(dic);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File createFile(String dic, String fileName) {
        File dicFile = getDictionary(dic);
        File file = new File(dicFile, fileName);
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            Print.console(e);
        }
        return file;
    }

    public static File createFile(String fullPath) {
        if (Empty.notEmpty(fullPath)) {
            int index = fullPath.lastIndexOf(File.separator);
            if (index != -1) {
                String dir = fullPath.substring(0, index);
                String filename = fullPath.substring(index);
                if (!Empty.notEmpty(dir)) dir = File.separator;
                if (Empty.notEmpty(filename)) {
                    return createFile(dir, filename);
                }
            }
        }
        Print.console(new IllegalStateException(StrUtils.splice("wrong path: ", fullPath)));
        return null;
    }
}
