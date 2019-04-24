package com.example.exhibitionbeta;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exhibitionbeta.models.User;

public class TextActivity extends AppCompatActivity {

    private EditText Memo;
    private Context mContext;
    private int uid;
    public static AppDatabase appDatabase;
    User user;
    private static final String TAG = "TextActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mContext = getApplicationContext();
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "users").allowMainThreadQueries().build();
        uid = getIntent().getIntExtra("USER_ID", -1);
        Memo = findViewById(R.id.memo);

        if (uid == -1) {
            Toast.makeText(mContext, "Invalid userId", Toast.LENGTH_SHORT);
        }
        else {
            Log.d(TAG, "onCreate: getting memo for user " + uid);
            user = appDatabase.userDAO().getUserWithId(uid);
            Log.d(TAG, "onCreate: memo: " + user.getMemo());
            Memo.setText(user.getMemo());
        }
    }

    public void sendAdmin(View view) {
        String memo = Memo.getText().toString();
        Log.d(TAG, "sendAdmin: memo: " + memo);

        if (uid == -1) {
            Toast.makeText(mContext, "Invalid userId", Toast.LENGTH_SHORT);
        }
        else {
            appDatabase.userDAO().updateUserMemo(memo, uid);
            Log.d(TAG, "sendAdmin: saved memo " + user.getMemo());
        }

        Intent backToAdmin = new Intent(this, AdminActivity.class);
        startActivity(backToAdmin);
    }
}
