package com.example.carlaundry.util;

public class UserAccount {
    private String userId;
    private String password;

    //TODO constructor
    public UserAccount(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //TODO
    private boolean isUnique() {
        return false;
    }
}
