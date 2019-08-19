package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThirdActivity extends BaseActivity {
    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context, ThirdActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);

    }
    private static final String TAG = "ThirdActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d(TAG, "This task id is: " + getTaskId());
        Button button4 = (Button)findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ThirdActivity.this, "you clicked button4", 3).show();
//                Intent intent = new Intent(ThirdActivity.this, FirstActivity.class);
//                startActivity(intent);
                ActivityCollector.finishAll();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
