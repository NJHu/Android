package com.example.broadcastbestpractice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class LoginActivity extends BaseActivity {

    public static void actionStart(Context context, String data) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText accountET = LoginActivity.this.findViewById(R.id.account);
                EditText pwdET = LoginActivity.this.findViewById(R.id.password);
                String accountStr = accountET.getText().toString();
                String pwdStr = pwdET.getText().toString();
                if (accountStr.equals("testing12") && pwdStr.equals("123456")) {
                    Toast.makeText(LoginActivity.this, "输入正确", Toast.LENGTH_SHORT).show();
                    MainActivity.actionStart(LoginActivity.this, "datacontent");
                }else  {
                    Toast.makeText(LoginActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
