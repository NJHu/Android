package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.start_download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask().execute();
            }
        });


        Button startbutton = findViewById(R.id.start_service);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, MyService.class);
                MainActivity.this.startService(startIntent);

            }
        });

        Button stopbutton = findViewById(R.id.stop_service);
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopIntent = new Intent(MainActivity.this, MyService.class);
                MainActivity.this.stopService(stopIntent);

            }
        });

    }
}
