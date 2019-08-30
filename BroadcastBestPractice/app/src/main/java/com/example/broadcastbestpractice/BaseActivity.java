package com.example.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    ForceOfflineReceiver offlineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (offlineReceiver == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
            offlineReceiver = new ForceOfflineReceiver();
            registerReceiver(offlineReceiver, intentFilter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (offlineReceiver != null) {
            unregisterReceiver(offlineReceiver);
            offlineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("WARNING");
            builder.setCancelable(false);
            builder.setMessage("must relogin");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCollector.finishAll();
                    LoginActivity.actionStart(context, "data");
                }
            });
            builder.show();
        }
    }
}
