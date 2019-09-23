package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView contentTextView = null;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.send_request);
        contentTextView = findViewById(R.id.content_text_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MainActivity.this.sendRequest();
//                MainActivity.this.sendOkRequest();
//                  MainActivity.this.sendUtilRequest();;

                MainActivity.this.sendOkHtRequest();;
            }
        });
    }

    private void sendOkHtRequest() {
        HttpUtil.sendOkHttpRequest("https://www.baidu.com", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String resStr = response.body().string();
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    private void sendUtilRequest(){
        HttpUtil.sendHttpRequest("https://www.baidu.com", new HttpUtil.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Log.d(TAG, "onFinish: " + response);
            }

            @Override
            public void onError(Exception e) {
                Log.d(TAG, "onError: ");
                e.printStackTrace();
            }
        });
    }

    private void sendOkRequest (){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://njhu.github.io/files/oftengoods.json")
                            .build();
                    Response response  = client.newCall(request).execute();
                    String responseData = response.body().string();
                    MainActivity.this.showResponse(responseData);
                    MainActivity.this.parserJSONWithJSONObject(responseData);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parserJSONWithJSONObject(String jsonData) {
        /*
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String goodsId = jsonObject.getString("goodsId");
                String goodsName = jsonObject.getString("goodsName");
                String price = jsonObject.getString("price");
                Log.d(TAG, "parserJSONWithJSONObject: " + goodsId + goodsName + price);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        */

        Gson gson = new Gson();
        List<Goods> googsList = gson.fromJson(jsonData, new TypeToken<List<Goods>>(){}.getType());
        for(Goods goods : googsList) {
            Log.d(TAG, "parserJSONWithJSONObject: " + goods.getGoodsId() + goods.getGoodsName() + goods.getPrice());
        }
    }

    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    MainActivity.this.showResponse(stringBuilder.toString());
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                contentTextView.setText(response);

            }
        });
    }
}
