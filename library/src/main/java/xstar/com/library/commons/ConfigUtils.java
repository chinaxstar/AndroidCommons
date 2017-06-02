package xstar.com.library.commons;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import xstar.com.library.commons.javacommons.IOUtils;


/**
 * @author: xstar
 * @since: 2016-07-25.
 */
public class ConfigUtils {
    private final String config_dic_path = "/AppConfig";
    private final String config_filename = "/config.txt";
    private String dic_path;
    private File configFile;
    private Properties properties = new Properties();

    /**
     * 初始化,生成设置文件
     *
     * @param dirPath
     * @param fileName
     */
    public ConfigUtils init(@NonNull String dirPath, @NonNull String fileName) {
        dic_path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dic_path = Environment.getExternalStorageDirectory().getPath();
        }
        dic_path = dic_path + File.separator + dirPath;// 文件夹
        configFile = new File(getDictionary(dic_path), fileName);
        try {
            if (!configFile.exists()) configFile.createNewFile();
            properties.load(new FileInputStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ConfigUtils init() {
        return init(config_dic_path, config_filename);
    }

    public File getDictionary(String dic) {
        File file = new File(dic);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public ConfigUtils put(String key, String value) {
        properties.setProperty(key, value);
        return this;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void commit() {

            FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(configFile);
            properties.store(fileOutputStream, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(fileOutputStream);
        }
    }

    public static boolean haveSDCard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public String configPath() {
        if (configFile != null)
            return configFile.getPath();
        else return null;
    }

}
