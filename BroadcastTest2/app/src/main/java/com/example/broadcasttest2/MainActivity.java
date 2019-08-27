package com.example.broadcasttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent("com.example.myacion.myreceiver");
//        sendBroadcast(intent);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.myacion.myreceiver");
                intent.setPackage(MainActivity.this.getPackageName());
//                sendBroadcast(intent);
                sendOrderedBroadcast(intent, null);
            }
        });

        MyReceiver myReceiver = new MyReceiver();
        this.myReceiver = myReceiver;
        IntentFilter intentFilter = new IntentFilter("com.example.myacion.myreceiver");
        intentFilter.setPriority(100);
        registerReceiver(myReceiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
