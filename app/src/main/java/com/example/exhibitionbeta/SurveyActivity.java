package com.example.exhibitionbeta;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Typeface tf = ResourcesCompat.getFont(this, R.font.notosansblack);
        CheckBox tv1 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox tv2 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox tv3 = (CheckBox) findViewById(R.id.checkBox4);
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
    }

    /** Called when the user taps the Next button */
    public void sendNext(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.e("Survey", "next clicked");
    }

    public void sendPassword(View view) {
        Intent password = new Intent(this, PasswordActivity.class);
        startActivity(password);
    }
}
