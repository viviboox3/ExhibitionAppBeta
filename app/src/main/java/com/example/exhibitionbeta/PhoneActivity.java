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
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn[] = new Button[12];
    ImageButton confirmButton;
    EditText userinput;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "exhPref";
    public static final String value = "phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btn[0] = (Button)findViewById(R.id.btn_0);
        btn[1] = (Button)findViewById(R.id.btn_1);
        btn[2] = (Button)findViewById(R.id.btn_2);
        btn[3] = (Button)findViewById(R.id.btn_3);
        btn[4] = (Button)findViewById(R.id.btn_4);
        btn[5] = (Button)findViewById(R.id.btn_5);
        btn[6] = (Button)findViewById(R.id.btn_6);
        btn[7] = (Button)findViewById(R.id.btn_7);
        btn[8] = (Button)findViewById(R.id.btn_8);
        btn[9] = (Button)findViewById(R.id.btn_9);
        //btn[10] = (Button)findViewById(R.id.confirmButton);
        btn[10] = (Button)findViewById(R.id.btn_x);
        btn[11] = (Button)findViewById(R.id.btn_del);

        confirmButton = (ImageButton)findViewById(R.id.confirmButton);

        for(int i = 0 ; i < 12 ; i++) {
            btn[i].setOnClickListener(this);
        }

        confirmButton.setOnClickListener(this);


        /*
        Typeface tf = ResourcesCompat.getFont(this, R.font.notosansblack);
        TextView tv = (TextView) findViewById(R.id.phoneText);
        tv.setTypeface(tf);
        Button bt = (Button) findViewById(R.id.confirmButton);
        bt.setTypeface(tf);
        */
    }

    public void addtoarray(String numbers) {
        //register TextBox
        userinput = (EditText)findViewById(R.id.numberpadtext);
        userinput.append(numbers);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_0:
                addtoarray("0");
                break;
            case R.id.btn_1:
                addtoarray("1");
                break;
            case R.id.btn_2:
                addtoarray("2");
                break;
            case R.id.btn_3:
                addtoarray("3");
                break;
            case R.id.btn_4:
                addtoarray("4");
                break;
            case R.id.btn_5:
                addtoarray("5");
                break;
            case R.id.btn_6:
                addtoarray("6");
                break;
            case R.id.btn_7:
                addtoarray("7");
                break;
            case R.id.btn_8:
                addtoarray("8");
                break;
            case R.id.btn_9:
                addtoarray("9");
                break;
            case R.id.btn_del:
                int sLength = userinput.length();
                if(sLength > 0){
                    //get the last character of the input
                    String selection = userinput.getText().toString().substring(0, sLength-1);
                    Log.e("Selection",selection);

                    /*
                    String result = userinput.getText().toString().replace(selection, "");
                    Log.e("Result",result);*/

                    userinput.setText(selection);
                    userinput.setSelection(userinput.getText().length());
                }
                break;
            case R.id.btn_x:
                userinput = (EditText)findViewById(R.id.numberpadtext);
                userinput.setText("");
                break;
            case R.id.confirmButton:
                Intent phoneToConfirm = new Intent(this, ConfirmActivity.class);
                EditText phoneNumber = (EditText) findViewById(R.id.numberpadtext);
                String phoneString = phoneNumber.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(value, phoneString);
                editor.apply();

                startActivity(phoneToConfirm);
                break;
            default:

        }

    }

    public void sendPassword(View view) {
        Intent password = new Intent(this, PasswordActivity.class);
        startActivity(password);
    }

    /*
    public void sendPhoneNext(View view) {
        Intent phoneToConfirm = new Intent(this, ConfirmActivity.class);
        EditText phoneNumber = (EditText) findViewById(R.id.numberpadtext);
        String phoneString = phoneNumber.getText().toString();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", phoneString);
        editor.commit();

        startActivity(phoneToConfirm);
    }*/
}
