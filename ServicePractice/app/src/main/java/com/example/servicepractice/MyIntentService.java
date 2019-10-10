package com.example.servicepractice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public MyIntentService() {
        super("MyIntentService");
        Log.d(TAG, "MyIntentService: ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
