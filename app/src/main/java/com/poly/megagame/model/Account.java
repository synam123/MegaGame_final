package com.poly.megagame.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class Account {
    private String userName;
    private String password;
    private String name;
    private String avatar;

    public Account() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
