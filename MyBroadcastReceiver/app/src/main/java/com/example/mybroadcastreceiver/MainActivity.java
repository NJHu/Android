package com.example.mybroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        intentFilter = new IntentFilter("com.example.broadcasttest.MY_BROADCAST");
//        myBroadcastReceiver = new MyBroadcastReceiver();
//        registerReceiver(myBroadcastReceiver, intentFilter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.test.MY_BROADCAST");
                intent.putExtra("receive", "test-broadcast");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
//                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
//                ComponentName componentName = new ComponentName(getApplicationContext(), "com.example.broadcasttest.MY_BROADCAST");
//                getIntent().setComponent(componentName);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
}
