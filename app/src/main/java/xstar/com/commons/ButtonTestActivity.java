package xstar.com.commons;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

import xstar.com.library.commons.ViewUtils;
import xstar.com.library.commons.downloader.Downloader;
import xstar.com.library.commons.javacommons.FileHelper;
import xstar.com.library.commons.view.DiscolorButton;

/**
 * * Created by Administrator on 2015/9/28.
 */
public class ButtonTestActivity extends Activity {
    DiscolorButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_test_act);
        button = ViewUtils.find(this, R.id.button);
    }

    public void change(View view) {
        button.setBgColor(Color.YELLOW);
        button.setRadius(30);
        String url = "https://staticlive.douyucdn.cn/upload/client/douyu_client_1_0v2_4_7_1.apk";
        downloadFile(url);
    }

    private void downloadFile(String url) {
        Downloader downloader = new Downloader(4);
        try {
//            File file = FileHelper.createFileByPath("/downloadtemp/download.apk");
            File file = FileHelper.createFileByPath("/atestdir/testdir1/");
//            downloader.setDowloadListener(new Downloader.DowloadListener() {
//                @Override
//                public void onDownloadStart() {
//
//                }
//
//                @Override
//                public void onDownloading(long length, long down) {
//                    Log.e("loading", (down / (double) length) + "");
//
//                }
//
//                @Override
//                public void onDownloadError(String msg) {
//                    Log.e("ERROR", msg);
//                }
//
//                @Override
//                public void onDownloadFinished(File src) {
//
//                }
//            });
//            downloader.download(url, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
