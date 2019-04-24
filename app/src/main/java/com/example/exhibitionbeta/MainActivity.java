package com.example.exhibitionbeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "exhPref";
    public static final  String value = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Name", "in name");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        /*
        Typeface tf = ResourcesCompat.getFont(this, R.font.notosansblack);
        TextView tv = (TextView) findViewById(R.id.nameText);
        tv.setTypeface(tf);
        EditText et = (EditText) findViewById(R.id.nameInput);
        et.setTypeface(tf);
        Button bt = (Button) findViewById(R.id.nameNextButton);
        bt.setTypeface(tf);
        */
    }

    /** Called when the user taps the Next button */
    public void sendNameNext(View view) {
        Intent nameToPhone = new Intent(this, PhoneActivity.class);
        EditText name = (EditText) findViewById(R.id.nameInput);
        String nameString = name.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(value, nameString);
        editor.apply();

        startActivity(nameToPhone);
    }

    public void sendPassword(View view) {
        Intent password = new Intent(this, PasswordActivity.class);
        startActivity(password);
    }

}
