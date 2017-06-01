package xstar.com.library.commons.downloader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by xstar on 2017-03-02.
 */
public class Downloader {
    // 下载线程
    private DownloadThread[] threads;
    private int threadNum;
    private ArrayBlockingQueue<String> mq;

    public Downloader(int threadNum) {
        this.threadNum = threadNum;
        this.threads = new DownloadThread[threadNum];
        this.mq = new ArrayBlockingQueue<String>(20);
    }

    public Downloader() {
        this(3);
    }

    public void download(final String url, final File fileName) {

        new Thread() {
            @Override
            public void run() {
                super.run();

                if (dowloadListener != null) dowloadListener.onDownloadStart();
                try {
                    URLConnection urlConnection = new URL(url).openConnection();
                    long length = urlConnection.getContentLength();
                    long blockSize = length / threadNum + threadNum;
                    long start_pos = 0;
                    long end_pos = 0;
                    RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
                    for (DownloadThread thread : threads) {
                        start_pos = end_pos;
                        end_pos = start_pos + blockSize;
                        if (end_pos > length) end_pos = length;
                        thread = new DownloadThread(url, mq, randomAccessFile, start_pos, end_pos);
                        thread.start();
                    }

                    boolean not_finish = true;
                    boolean no_error = true;
                    long download_length = 0;
                    while (no_error && not_finish) {
                        try {
                            String msg = mq.take();
                            if (msg.contains("Exception")) {
                                no_error = false;
                                if (dowloadListener != null) dowloadListener.onDownloadError(msg);
                            } else {
                                download_length += Integer.parseInt(msg);
                                if (download_length >= length) {
                                    randomAccessFile.close();
                                    not_finish = false;}
                                if (dowloadListener != null)
                                    dowloadListener.onDownloading(length, download_length);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (dowloadListener != null) dowloadListener.onDownloadFinished(fileName);
            }
        }.start();
    }

    public interface DowloadListener {
        void onDownloadStart();

        void onDownloading(long length, long down);

        void onDownloadError(String msg);

        void onDownloadFinished(File src);
    }

    private DowloadListener dowloadListener;

    public DowloadListener getDowloadListener() {
        return dowloadListener;
    }

    public void setDowloadListener(DowloadListener dowloadListener) {
        this.dowloadListener = dowloadListener;
    }
}
