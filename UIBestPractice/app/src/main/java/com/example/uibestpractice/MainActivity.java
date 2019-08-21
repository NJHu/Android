package com.example.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final MsgAdapter adapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(adapter);

        final TextView textView = findViewById(R.id.edit_text);

        Button sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = textView.getText().toString();

                Random random = new Random();
                int type = random.nextInt(100) % 2;

                Msg msg = new Msg(content, type);
                msgList.add(msg);
                adapter.notifyItemInserted(msgList.size() - 1);
                recyclerView.scrollToPosition(msgList.size() - 1);
                textView.setText("");
            }
        });

    }

    private void initMsgs() {
        for (int i = 0; i < 4; i++) {
            String name = "abc" + i;
            Random random = new Random();
            int length = random.nextInt(50) + 1;
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < length; j++) {
                builder.append(name);
            }

            Msg msg = new Msg( builder.toString(), i % 2);
            msgList.add(msg);
        }
    }
}
