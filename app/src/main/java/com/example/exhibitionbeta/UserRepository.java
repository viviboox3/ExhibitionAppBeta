package com.example.exhibitionbeta;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.exhibitionbeta.dao.UserDao;
import com.example.exhibitionbeta.models.User;

import java.util.List;

public class UserRepository {

    /*
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDAO();
        mAllUsers = mUserDao.getAllUsers();
    }

    LiveData<List<User>> getAllWords() {
        return mAllUsers;
    }


    public void insert (User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }*/
}
