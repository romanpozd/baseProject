package com.example.roman.githubapp.server_data;

/**
 * Created by roman on 30/07/2017.
 */

public class User {

    private String userName;
    private String userImage;


    public User(String userName, String userImage) {
        this.userName = userName;
        this.userImage = userImage;
    }


    public String getUserImage() {
        return userImage;
    }

    public String getUserName() {
        return userName;
    }
}
