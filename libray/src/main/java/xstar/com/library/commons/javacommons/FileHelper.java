package xstar.com.library.commons.javacommons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by xstar on 2015/9/22.
 */
public class FileHelper {
    /**
     * 生成文件
     */
    public static File createFileByPath(String dir) throws IOException {
        System.out.println(dir);
        int lastIndex = dir.lastIndexOf("/");
        if (lastIndex != 0) {
            String dir_parent = dir.substring(0, lastIndex);//文件夹
            System.out.println(dir_parent);
            File parent = new File(dir_parent);
            if (!parent.exists()) {
                if (parent.mkdirs()) System.out.println("文件夹创建成功！");
                else System.out.println("文件夹创建失败！");
            }
            if (lastIndex == (dir.length() - 1)) return parent;//最后一个字符是/则是文件夹
        }
        //此时上级文件夹都已经建好
        File file = new File(dir);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 删除文件 不存在直接返回成功
     */
    public static boolean deleteFileByPath(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /**
     * 更新文件数据 false 覆盖/ true 添加
     */
    public static void updateFile(String str, File file, boolean isappend) throws IOException {
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, isappend), "UTF-8");
            outputStreamWriter.write(str);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
        }
    }
}
