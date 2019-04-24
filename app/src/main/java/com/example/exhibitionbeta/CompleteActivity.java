package com.example.exhibitionbeta;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CompleteActivity extends AppCompatActivity {

    private static int TIME_OUT = 5000; //Time to launch survey activity again

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(CompleteActivity.this, SurveyActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
