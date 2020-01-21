package com.filesheriff.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {

    private String userName;
    private String email;
    private String password;
    private String matchingPassword;


    public RegisterForm(){

    }
}
