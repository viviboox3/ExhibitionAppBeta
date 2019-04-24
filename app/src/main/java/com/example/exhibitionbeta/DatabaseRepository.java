package com.example.exhibitionbeta;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;
import android.arch.lifecycle.MutableLiveData;

import com.example.exhibitionbeta.dao.UserDao;
import com.example.exhibitionbeta.models.User;

public class DatabaseRepository {

    private static class MyTaskParams {
        String memo;
        int userId;

        MyTaskParams(String memo, int userId) {
            this.memo = memo;
            this.userId = userId;
        }
    }

    private MutableLiveData<List<User>> searchResults =
            new MutableLiveData<>();

    private void asyncFinished(List<User> results) {
        searchResults.setValue(results);
    }

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public DatabaseRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getDatabase(application);
        userDao = db.userDAO();
        allUsers = userDao.getAllUsers();
    }

    public void insertUser(User newUser) {
        InsertAsyncTask task = new InsertAsyncTask(userDao);
        task.execute(newUser);
    }

    public void deleteUser(Integer userId) {
        DeleteAsyncTask task = new DeleteAsyncTask(userDao);
        task.execute(userId);
    }

    public void findUser(String memo, int userId) {
        MyTaskParams params = new MyTaskParams(memo, userId);
        UpdateAsyncTask task = new UpdateAsyncTask(userDao);
        task.execute(params);
    }

    private static class UpdateAsyncTask extends AsyncTask<MyTaskParams, Void, Void> {

        private UserDao asyncTaskDao;
        UpdateAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(MyTaskParams... params) {
            String memo = params[0].memo;
            int userId = params[0].userId;
            asyncTaskDao.updateUserMemo(memo, userId);
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao asyncTaskDao;

        InsertAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            asyncTaskDao.addUser(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private UserDao asyncTaskDao;

        DeleteAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            asyncTaskDao.deleteUserWithId(params[0].intValue());
            return null;
        }
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}
