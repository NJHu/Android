package com.example.servicetest;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
    private static final String TAG = "DownloadTask";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: "+ Thread.currentThread().toString());
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        for (int i = 0; i < 3; i++) {
            publishProgress(i);
        }

        Log.d(TAG, "doInBackground: " + Thread.currentThread().toString());
        return false;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + Thread.currentThread().toString());
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.d(TAG, "onPostExecute:  " + Thread.currentThread().toString());
    }
}
