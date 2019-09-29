package com.example.servicetestb;

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
        ((Button)findViewById(R.id.start)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.start();
            }
        });

        ((Button)findViewById(R.id.stop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.stop();
            }
        });
    }

    private void start() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
    private void stop() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
