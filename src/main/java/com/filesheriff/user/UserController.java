package com.filesheriff.user;

import com.filesheriff.MyUserDetailsService;
import com.filesheriff.encryption.PasswordEncryptionService;
import com.filesheriff.form.LoginForm;
import com.filesheriff.form.RegisterForm;
import com.filesheriff.key.KeyService;
import com.filesheriff.validation.PasswordValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.security.NoSuchAlgorithmException;


@Log4j2
//@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KeyService keyService;

    @Autowired
    private PasswordValidation passwordValidation;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;



    private Logger logger = LogManager.getLogger(UserController.class);



    //zamiast name password i email jeden obiekt
    //metoda niech zwroci UserDto
    //@PostMapping("/register")
    public void registerUser(String userName, String password, String email) throws NoSuchAlgorithmException {
        logger.info("---Starting registration process---");

        userService.registerUser(userName, password, email);

        logger.info("---Registration process finished---");
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String getRegisterForm(){
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/termsandconditions", method = RequestMethod.GET)
    public String getTermsAndConditions() {
        return "termsandconditions";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String getCredentials(@ModelAttribute(name="registerForm") RegisterForm registerForm) throws NoSuchAlgorithmException {

        String userName = registerForm.getUserName();
        String password = registerForm.getPassword();
        String email = registerForm.getEmail();
        String matchingPassword = registerForm.getMatchingPassword();

        if (passwordValidation.checkMatchingPassword(password, matchingPassword)) {
            registerUser(userName, password, email);
            return "login";
        }

        logger.info("Passwords doesn't match");
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm) {

        String userName = loginForm.getUserName();
        String password = loginForm.getPassword();

        UserDetails user  = myUserDetailsService.loadUserByUsername(userName);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(password, user.getPassword())) {
            return "home";
        }

        return "login";
    }


}
