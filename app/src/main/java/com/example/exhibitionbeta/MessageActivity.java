package com.example.exhibitionbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageActivity extends AppCompatActivity {

    private EditText messageEt;
    private OkHttpClient mClient = new OkHttpClient();
    private String body, userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        messageEt = findViewById(R.id.messageBody);
        userPhoneNumber = getIntent().getStringExtra("USER_PHONE");
    }

    public void sendAdmin(View view) {
        body = messageEt.getText().toString();

        try {
            post("+1" + userPhoneNumber, "https://exhibitionappbeta.cfapps.io/sms", new  Callback(){

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"SMS Sent!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent backToAdmin = new Intent(this, AdminActivity.class);
        startActivity(backToAdmin);
    }

    Call post(String toPhoneNumber, String url, Callback callback) throws IOException{

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES); // read timeout

        mClient = builder.build();

        RequestBody formBody = new FormBody.Builder()
                .add("To", toPhoneNumber)
                .add("Body", body)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call response = mClient.newCall(request);
        response.enqueue(callback);
        return response;
    }
}
