package com.example.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
    private static final String TAG = "TitleLayout";
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        Log.d(TAG, context.toString());
        Log.d(TAG, attrs.toString());
        Button backButton = findViewById(R.id.back_button);
        Button editButton = findViewById(R.id.edit_button);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)TitleLayout.this.getContext()).finish();
            }
        });
        editButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TitleLayout.this.getContext(), "clicked edit button", 3).show();
            }
        });
    }
}
