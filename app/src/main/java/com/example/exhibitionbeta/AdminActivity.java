package com.example.exhibitionbeta;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.exhibitionbeta.models.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private static final String TAG = "AdminActivity";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mNumbers = new ArrayList<>();
    private ArrayList<Integer> mUids = new ArrayList<>();
    private MainViewModel mViewModel;
    //public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "users").allowMainThreadQueries().build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Log.d(TAG, "onCreate: started.");

        initUserList();
    }

    private void initUserList() {
        mViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                User tempUser;
                int size = users.size();
                Log.d(TAG, "initUserList: there are " + size + " users.");
                int i = 0;

                while(i < size) {
                    tempUser = users.get(i);
                    mNames.add(tempUser.getFullName());
                    mNumbers.add(tempUser.getPhoneNumber());
                    mUids.add(tempUser.getUid());
                    i++;
                }

                initRecyclerView();
            }
        });
        //List<User> allUsers = appDatabase.userDAO().getAllUsers();
        /*
        User tempUser;
        int size = allUsers.size();
        Log.d(TAG, "initUserList: there are " + size + " users.");
        int i = 0;

        while(i < size) {
            tempUser = allUsers(i);
            mNames.add(tempUser.getFullName());
            mNumbers.add(tempUser.getPhoneNumber());
            mUids.add(tempUser.getUid());
            i++;
        }*/
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerView");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mNumbers, mUids);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
