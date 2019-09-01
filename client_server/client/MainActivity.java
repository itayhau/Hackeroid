package com.example.itaykan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);


        OkHttpClient client = new OkHttpClient();

        //String url = "https://reqres.in/api/users?page=2";
        String url = "http://10.0.2.2:8080/webapi/coupon";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                            mTextViewResult.setText(myResponse);

                            //JsonObject jsonObject = new JsonParser().parse(myResponse).getAsJsonObject();

                             //Log.d("My App", jsonObject.toString());

                             //   Log.d("My App name = ", jsonObject.get("name").getAsString());

                                Log.d("==============", myResponse);
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson Gson = gsonBuilder.create();
                                MyMessage[] wu = Gson.fromJson(myResponse, MyMessage[].class);
                                //Log.d("My App name = ", wu.name + " " + wu.id);
                                for (MyMessage msg:wu) {
                                    Log.d("==============", msg.toString());
                                }

                            } catch (Throwable tx) {
                                Log.e("My App", "Could not parse malformed JSON: \"" + myResponse + "\"");
                            }
                        }
                    });
                }
            }
        });
    }


}