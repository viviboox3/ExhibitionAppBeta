package com.example.exhibitionbeta.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int uid;

    @ColumnInfo(name = "fullName")
    @NonNull
    private String fullName;

    @ColumnInfo(name = "phoneNumber")
    @NonNull
    private String phoneNumber;

    @ColumnInfo(name = "memo")
    private String memo;

    public int getUid() {
        return uid;
    }

    public void setUid(int id) {
        this.uid = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    public String getMemo() { return memo; }

    public void setMemo(String memo) { this.memo = memo; }
}
