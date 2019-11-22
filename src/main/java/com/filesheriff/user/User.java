package com.filesheriff.user;

import javax.crypto.SecretKey;

//Entity
public class User {

    private int userId;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
