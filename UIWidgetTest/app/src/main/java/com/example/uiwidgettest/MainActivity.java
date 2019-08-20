package com.example.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.PortUnreachableException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addActions();
    }
    private void addActions() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = ((EditText)MainActivity.this.findViewById(R.id.edit_text)).getText().toString();
                Toast.makeText(MainActivity.this, inputText, 3).show();

                ImageView imageView = MainActivity.this.findViewById(R.id.image_view);
                imageView.setImageResource(R.drawable.snip20180807_2);

                ProgressBar progressBar = MainActivity.this.findViewById(R.id.progress_bar);
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(10 + progressBar.getProgress());
                }else {
                    progressBar.setVisibility(View.GONE);
                }

                MainActivity.this.showDialog();
                MainActivity.this.showProgressDialog();
            }
        });
    }

    private void showDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("this is title");
        dialog.setMessage("this is content");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }
    private void showProgressDialog() {

    }
}
