package com.example.servicebestpractice;

public interface DownloadLinstener {
    /*更新进度*/
    void onProgress(String downloadURLString, int progress);
    /*下载成功*/
    void onSuccess(String downloadURLString);
    /*下载失败*/
    void onFailed(String downloadURLString);
    /*暂停*/
    void onPaused(String downloadURLString);
    /*取消下载*/
    void onCanceled(String downloadURLString);
}