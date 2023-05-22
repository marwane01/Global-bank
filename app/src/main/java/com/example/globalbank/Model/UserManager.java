package com.example.globalbank.Model;

public class UserManager {
    private static UserManager instance;
    private User user;

    private UserManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

