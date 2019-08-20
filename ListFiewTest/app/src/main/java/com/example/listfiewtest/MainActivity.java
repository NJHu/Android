package com.example.listfiewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"ax", "bx", "cw", "dd", "dx", "x1", "ea", "lb", "ax", "bx", "cw", "dd", "dx", "x1", "ea", "lb"};

    private List<Fruit> fruits = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, MainActivity.this.data);
//        ListView listView = findViewById(R.id.list_view);
//        listView.setAdapter(adapter);

        initFruits();
        FruitAdapt adapt = new FruitAdapt(MainActivity.this, R.layout.fruit_item, fruits);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = fruits.get(i);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initFruits() {
        for (int i = 0; i < 100; i++ ) {
            fruits.add(new Fruit("aju" + i, R.drawable.ic_launcher_background));
        }
    }
}
