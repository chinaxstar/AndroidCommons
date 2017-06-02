package xstar.com.library.commons.javacommons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author: xstar
 * @since: 2017-05-26.
 */

public class Print {
    public static void console(Throwable e) {
        StackTraceElement[] elements = e.getStackTrace();
        String title = e.toString();
        if (Empty.notEmpty(title)) System.out.println(title);
        if (Empty.notEmpty(elements)) {
            int len = elements.length;
            for (int i = 0; i < len; i++) {
                System.out.println(elements[i].toString());
            }
        }
    }

    public static <T> void console(T t) {
        System.out.println(t);
    }

    public static void console(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }

    public static <T> boolean file(File log, T t,boolean append) {
        boolean re = false;
        if (log == null || !log.exists()) return re;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter writer = null;
        try {
            fileOutputStream = new FileOutputStream(log, append);
            writer = new OutputStreamWriter(fileOutputStream);
            writer.write(String.valueOf(t));
            writer.write("\n");
            re = true;
        } catch (FileNotFoundException e) {
            console(e);
        } catch (IOException e) {
            console(3);
        } finally {
            IOUtils.close(writer, fileOutputStream);
        }
        return re;
    }

    public static <T> boolean file(String path, T t,boolean append) {
        File file = FileUtils.createFile(path);
        return file(file, t,append);
    }

    public static <T> boolean stream(OutputStream outputStream, T t, boolean close) {
        boolean re = false;
        if (outputStream == null) return re;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        try {
            outputStreamWriter.write(String.valueOf(t));
            re = true;
        } catch (IOException e) {
            console(e);
        } finally {
            if (close)
                IOUtils.close(outputStream);
        }
        return re;
    }

    public static <T> void stream(Writer writer, T t, boolean close) {
        if (writer == null) return;
        try {
            writer.write(String.valueOf(t));
        } catch (IOException e) {
            console(e);
        } finally {
            if (close) IOUtils.close(writer);
        }
    }
}
