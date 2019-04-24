package com.example.exhibitionbeta;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.exhibitionbeta.models.User;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private DatabaseRepository repository;
    private LiveData<List<User>> allUsers;

    public MainViewModel (Application application) {
        super(application);
        repository = new DatabaseRepository(application);
        allUsers = repository.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public void deleteUser(Integer userId) {
        repository.deleteUser(userId);
    }

    public void findUser(String memo, int userid) {
        repository.findUser(memo, userid);
    }
}
