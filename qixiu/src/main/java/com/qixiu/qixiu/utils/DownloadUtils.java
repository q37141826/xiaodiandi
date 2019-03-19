package com.qixiu.qixiu.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadUtils {
    private static final String TAG = "DownloadUtils";
    private static volatile DownloadUtils instance;
    private File file;
    private String filePath;

    private OkHttpClient client;
    private File downloadFile;
    private long startPosition;
    private Call call;

    private DownloadUtils() {
        client = new OkHttpClient();
    }

    private DownloadListener listener;

    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }

    /**
     * 初始化下载父路径
     *
     * @param path
     */
    public DownloadUtils initDownload(String path) {
        file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.mkdir();
        }
        filePath = file.getAbsolutePath();
        Log.i(TAG, "initDownload: " + filePath);
        return instance;
    }

    public static DownloadUtils getInstance() {
        if (null == instance) {
            synchronized (DownloadUtils.class) {
                if (instance == null) {
                    instance = new DownloadUtils();
                }
            }
        }
        return instance;
    }

    public void startDownload(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (url.contains(".")) {
            String typeName = url.substring(url.lastIndexOf(".") + 1);
            if (url.contains("/")) {
                String name = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
                String fn = name + "." + typeName;
                downloadFile = new File(file, fn);
            }
        }
        startPosition = 0;
        if (downloadFile.exists()) {
            startPosition = downloadFile.length();
        }
        Request request = new Request.Builder()
                .addHeader("RANGE", "bytes=" + startPosition + "-")
                .url(url)
                .build();

        call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.startDownload();
                ResponseBody body = response.body();
                long totalLength = body.contentLength() + startPosition;
                Log.i(TAG, "totalLength: " + totalLength + "----");
                InputStream is = body.byteStream();
                byte[] buf = new byte[2048];
                int length = 0;
                long totalNum = startPosition;
                RandomAccessFile raf = new RandomAccessFile(downloadFile, "rw");
                raf.seek(totalNum);
                while ((length = is.read(buf, 0, buf.length)) != -1) {
                    raf.write(buf, 0, length);
                    totalNum += length;
                    long finalTotalNum = totalNum;
                    listener.downloadProgress(finalTotalNum * 100 / totalLength);

                }
                Log.i(TAG, "totalNum==" + totalNum + "---");
                listener.finishDownload(downloadFile.getAbsolutePath());
                body.close();
            }
        });

    }

    public void pauseDownload() {
        listener.pauseDownload();
        if (call != null && call.isExecuted()) {
            call.cancel();
        }
    }

    public interface DownloadListener {
        void startDownload();

        void pauseDownload();

        void finishDownload(String path);

        void downloadProgress(long progress);
    }

}