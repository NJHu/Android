package com.example.runloopmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView textView;

    public static final int UPDATE_TEXT = 1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "handleMessage: " + Thread.currentThread().toString());
            switch (msg.what) {
                case UPDATE_TEXT:
                    textView.setText("12 2 3  3 ");
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        ((Button) findViewById(R.id.change_text_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: ");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                        Log.d(TAG, "run: " + Thread.currentThread().toString());
                    }
                }).start();
            }
        });

        ((Button) findViewById(R.id.start_download_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               new DwonloadTask().execute();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
