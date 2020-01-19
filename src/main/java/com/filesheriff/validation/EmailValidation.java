package com.filesheriff.validation;

import com.filesheriff.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailValidation {


    @Autowired
    private UserService userService;

    private Logger logger = LogManager.getLogger(UserService.class);


    public EmailValidation() {

    }

    public boolean validateEmail(String email) {
        if (validateEmailBody(email) && isEmailExist(email)) {
            return true;
        }
        return false;
    }

    private boolean validateEmailBody(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        } else {
            logger.info("Email: " + email + " doesn't contains \"@\" or \".\"");
            return false;
        }
    }


    private boolean isEmailExist(String email) {
        //change getUserByEmail to findByEmail (from dao)
        if (userService.getUserByEmail(email) == null) {
            return true;
        }
        logger.info("This email already exists");
        return false;
    }

}
