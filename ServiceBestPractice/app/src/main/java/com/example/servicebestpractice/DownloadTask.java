package com.example.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;
    private static final String TAG = "DownloadTask";

    private DownloadLinstener linstener;

    private boolean isCanceled = false;
    private boolean isPaused = false;
    // 最新的进度
    private int lastProgress = 0;
    // 下载地址
    private String downloadUrlString;

    public DownloadTask(DownloadLinstener linstener) {
        this.linstener = linstener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: strings");
        if (strings.length > 0) {
            this.downloadUrlString = strings[0];
        } else {
            return TYPE_FAILED;
        }

        File file = null;
        InputStream inputStream = null;
        RandomAccessFile saveFile = null;

        try {
            // 记录已经下载的文件长度
            long downloadedLength = 0;
            Log.d(TAG, "doInBackground: String fileName =");
            String fileName = downloadUrlString.substring(downloadUrlString.lastIndexOf("/"));
            Log.d(TAG, "doInBackground: Environment.getExternalStorageDirectory");
            String directory = Environment.getExternalStorageDirectory().getAbsolutePath();
            directory = "data/data/com.example.servicebestpractice";
            Log.d(TAG, "doInBackground: file = new File");
            file = new File(directory + fileName);
            if (file.exists()) {
                downloadedLength = file.length();
                Log.d(TAG, "doInBackground: file.length");
            }else  {
                file.createNewFile();
                Log.d(TAG, "doInBackground: createNewFile");
            }

            // 总长度
            long contentLength = getContentLength(downloadUrlString);
            Log.d(TAG, "doInBackground: contentLength = " + contentLength);
            if (contentLength <= 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadedLength) {
                // 已经下载完毕
                return TYPE_SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrlString).build();
            Response response = client.newCall(request).execute();
            if (response == null) {
                return TYPE_FAILED;
            }

            inputStream = response.body().byteStream();
            Log.d(TAG, "doInBackground: inputStream = " + inputStream.toString());
            saveFile = new RandomAccessFile(file, "rw");
            saveFile.seek(downloadedLength);
            byte[] b = new byte[1024];
            int total = 0;
            int len = 0;
            while ((len = inputStream.read(b)) != -1) {
                if (isCanceled) {
                    return TYPE_CANCELED;
                } else if (isPaused) {
                    return TYPE_PAUSED;
                } else {
                    total += len;
                    saveFile.write(b, 0, len);
                    int progress = (int) ((total + downloadedLength) * 100.0 / contentLength);
                    publishProgress(progress);
                    Log.d(TAG, "doInBackground: saveFile = " + saveFile.toString());
                }
            }
            response.body().close();
            return TYPE_SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "doInBackground: Exception = " + e.toString());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (saveFile != null) {
                    saveFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "doInBackground: finally = ");
        }

        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
        if (values.length == 0) {
            return;
        }
        int progress = values[0];
        if (progress > lastProgress) {
            lastProgress = progress;
            linstener.onProgress(downloadUrlString, lastProgress);
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
//        super.onPostExecute(integer);
        switch (status) {
            case TYPE_SUCCESS:
                linstener.onSuccess(downloadUrlString);
                break;
            case TYPE_FAILED:
                linstener.onFailed(downloadUrlString);
                break;
            case TYPE_PAUSED:
                linstener.onPaused(downloadUrlString);
                break;
            case TYPE_CANCELED:
                linstener.onCanceled(downloadUrlString);
                break;
            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void canceleDownload() {
        isCanceled = true;
    }

    /*获取文件长度*/
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            return contentLength;
        }
        return 0;
    }
}
