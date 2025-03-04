package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "This task id is: " + getTaskId());
       final Intent intent = this.getIntent();
        // 获取数据
       final String extraDataContent = intent.getStringExtra("extra_data");


        Button button3 = (Button)findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(SecondActivity.this, ("you clicked button3\n" + extraDataContent), 3).show();
//                intent.putExtra("data_return", "hello firstActivity");
//                setResult(RESULT_OK, intent);
//                finish();
//                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
//                startActivity(intent);
                ThirdActivity.actionStart(SecondActivity.this, "123", "456");
            }
        });
//        Intent;
    }

    @Override
    public void onBackPressed() {
        final Intent intent = this.getIntent();
        intent.putExtra("data_return", "hello firstActivity");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
