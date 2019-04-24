package com.example.exhibitionbeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }

    public void onEnter(View view) {

        EditText input = (EditText) findViewById(R.id.password);
        String password = input.getText().toString();

        if(password.equals("admin")) {
            Intent admin = new Intent(this, AdminActivity.class);
            startActivity(admin);
        }
        else {
            Toast.makeText(this, "WRONG PASSWORD!", Toast.LENGTH_SHORT).show();
        }

    }
}
