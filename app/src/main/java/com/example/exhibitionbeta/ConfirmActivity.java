package com.example.exhibitionbeta;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

import com.example.exhibitionbeta.models.User;

public class ConfirmActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "exhPref";
    //public static AppDatabase appDatabase;
    public static final String fromPhoneNumber = "+18566198691";
    private EditText UserName;
    private TextView UserPhoneNumber;
    private OkHttpClient mClient = new OkHttpClient();
    private Context mContext;
    private String body = "Welcome! This is a test";
    private boolean checkBoxOne = false;
    private boolean checkBoxTwo = false;
    private MainViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //((Application) getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "users").allowMainThreadQueries().build();

        updateNameTextEdit();
        updatePhoneTextView();

        UserName = findViewById(R.id.nameConfirm);
        UserPhoneNumber = findViewById(R.id.phoneConfirm);

        CheckBox personalInfoCheckBox = (CheckBox) findViewById(R.id.personalInfoCheck);
        CheckBox textReceiveCheckBox = (CheckBox) findViewById(R.id.textReceiveCheck);
        final ImageButton nameNextBtn = (ImageButton) findViewById(R.id.nameNextButton);
        nameNextBtn.setColorFilter(new LightingColorFilter(0xFF808080, 0x77777777));
        //nameNextBtn.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.DARKEN);
        nameNextBtn.setClickable(false);

        personalInfoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkBoxOne = true;
                    if (checkBoxOne && checkBoxTwo) {
                        nameNextBtn.clearColorFilter();
                        nameNextBtn.setClickable(true);
                    }

                }
                else {
                    checkBoxOne = false;
                    nameNextBtn.setColorFilter(new LightingColorFilter(0xFF808080, 0x77777777));
                    nameNextBtn.setClickable(false);
                }

            }
        });

        textReceiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkBoxTwo = true;
                    if (checkBoxOne && checkBoxTwo) {
                        nameNextBtn.clearColorFilter();
                        nameNextBtn.setClickable(true);
                    }

                }
                else {
                    checkBoxTwo = false;
                    nameNextBtn.setColorFilter(new LightingColorFilter(0xFF808080, 0x77777777));
                    nameNextBtn.setClickable(false);
                }

            }
        });


    }

    public void updateNameTextEdit() {
        String name = sharedpreferences.getString("name", "홍길동");
        EditText tv = (EditText) findViewById(R.id.nameConfirm);
        // set the string from sp as text of the textview
        tv.setText(name);

    }

    public void updatePhoneTextView() {
        String phone = sharedpreferences.getString("phone", "000000000");
        TextView et = (TextView) findViewById(R.id.phoneConfirm);
        et.setText(phone);

    }

    public void onConfirm(View view) {
        String userName = UserName.getText().toString();
        String userPhoneNumber = UserPhoneNumber.getText().toString();
        mContext = getApplicationContext();

        User user = new User();
        user.setFullName(userName);
        user.setPhoneNumber(userPhoneNumber);
        user.setUid(0);

        mViewModel.insertUser(user);
        //appDatabase.userDAO().addUser(user);
        Toast.makeText(getApplicationContext(), "Adding new user", Toast.LENGTH_SHORT).show();

        Log.e("ConfirmActivity","Inserting uid: " + user.getUid() + " into the DB");
        //Log.e("ConfirmActivity","There are total: " + appDatabase.userDAO().getAllUserCount() + " users");

        // appDatabase.userDAO().getAllUsers().get(1).

        // TODO: make sure to add correct country code and test for a valid phone number
        // sendMsg("+1" + userPhoneNumber);

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

        Intent complete = new Intent(this, CompleteActivity.class);
        startActivity(complete);

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

    public void clickPhone(View view) {
        Intent backToPhone = new Intent(this, PhoneActivity.class);
        startActivity(backToPhone);
    }

    public void sendPassword(View view) {
        Intent password = new Intent(this, PasswordActivity.class);
        startActivity(password);
    }
}
