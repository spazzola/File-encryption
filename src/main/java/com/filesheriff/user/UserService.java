package com.filesheriff.user;

import com.filesheriff.key.Key;
import com.filesheriff.validation.EmailValidation;
import com.filesheriff.encryption.PasswordEncryptionService;
import com.filesheriff.key.KeyService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@Log4j2
public class UserService {

    private final EmailValidation emailValidation;
    private final KeyService keyService;
    private final UserDao userDao;
    private final PasswordEncryptionService passwordEncryptionService;

    private Logger logger = LogManager.getLogger(UserService.class);

    public UserService(EmailValidation emailValidation, KeyService keyService, UserDao userDao,
                       PasswordEncryptionService passwordEncryptionService) {
        this.emailValidation = emailValidation;
        this.keyService = keyService;
        this.userDao = userDao;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    public void registerUser(String userName, String password, String email) throws NoSuchAlgorithmException {
        //TODO password validation

        if (validateUser(userName, email)) {

            final String encryptedPassword = passwordEncryptionService.encryptPassword(password);
            final User user = new User(userName, encryptedPassword, email);

            userDao.save(user);

            final Key key = keyService.generateKey(user);

            user.setKey(key);

            userDao.save(user);
        }
    }

    public boolean validateUser(String userName, String email) {

        return emailValidation.validateEmail(email) && isUserNameExist(userName);
    }

    private boolean isUserNameExist(String userName) {
        if (!userDao.findByUserName(userName).isPresent()) {
            return true;
        }
        logger.info("User name already exists");
        return false;
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
