package com.example.exhibitionbeta.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.exhibitionbeta.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public void addUser(User... users);

    @Update
    public void updateUser(User... users);

    @Delete
    public void deleteUser(User user);

    @Query("DELETE from users WHERE uid = :userId")
    public void deleteUserWithId(int userId);

    @Query("SELECT * from users ORDER BY fullName ASC")
    public LiveData<List<User>> getAllUsers();

    @Query("SELECT * from users WHERE uid = :userId")
    public User getUserWithId(int userId);

    @Query("SELECT COUNT(*) from users")
    public int getAllUserCount();

    @Query("SELECT memo from users WHERE uid = :userId")
    public String getUserMemo(int userId);

    @Query("UPDATE users SET memo = :memo WHERE uid = :userId")
    public void updateUserMemo(String memo, int userId);
}
