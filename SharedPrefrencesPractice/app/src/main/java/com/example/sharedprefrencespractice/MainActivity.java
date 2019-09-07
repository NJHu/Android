package com.example.sharedprefrencespractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        Button saveBtn = findViewById(R.id.save_btn);
        Button readBtn = findViewById(R.id.read_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = MainActivity.this.getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 12);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = MainActivity.this.getSharedPreferences("data", MODE_PRIVATE);
                Toast.makeText(MainActivity.this, preferences.getString("name", "") + preferences.getInt("age", 0) + preferences.getBoolean("married", true) , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
