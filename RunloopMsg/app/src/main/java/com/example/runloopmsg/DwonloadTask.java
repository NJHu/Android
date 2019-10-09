package com.example.runloopmsg;

import android.os.AsyncTask;
import android.util.Log;

public class DwonloadTask extends AsyncTask<Void, Integer, Boolean> {
    private static final String TAG = "DwonloadTask";
    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute: ");
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: ");
        publishProgress(99);
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG, "onProgressUpdate: " + values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Log.d(TAG, "onPostExecute: ");
        super.onPostExecute(aBoolean);
    }
}
