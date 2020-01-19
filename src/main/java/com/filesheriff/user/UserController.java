package com.filesheriff.user;

import com.filesheriff.MyLogger;
import com.filesheriff.key.KeyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.annotation.WebServlet;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;

@RestController
@Log4j2
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KeyService keyService;

    @Autowired
    private MyLogger myLogger;

    private Logger logger = LogManager.getLogger(UserController.class);


    @GetMapping("/hello")
    public String home() {
        return ("<h1>Hello User</h1>");
    }

    //zamiast name password i email jeden obiekt
    //metoda niech zwroci UserDto
    @PostMapping("/register")
    public void registerUser(String userName, String password, String email) throws NoSuchAlgorithmException {
        logger.info("---Starting registration process---");

        userService.registerUser(userName, password, email);

        logger.info("---Registration process finished---");
    }



}
