package com.filesheriff.validation;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidation {

    public PasswordValidation() {

    }

    public boolean checkMatchingPassword(String password, String matchingPassword) {

        if (password.equals("") && matchingPassword.equals("")) {
            return false;
        }
        return password.equals(matchingPassword);
    }
}
