package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
