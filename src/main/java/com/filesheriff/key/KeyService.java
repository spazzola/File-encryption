package com.filesheriff.key;

import com.filesheriff.MyLogger;
import com.filesheriff.encryption.EncryptionConfiguration;
import com.filesheriff.user.User;
import com.filesheriff.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;


@Service
public class KeyService {

    //obie byly final
    private  KeyDao keyDao;
    private  UserDao userDao;

    @Autowired
    private EncryptionConfiguration encryptionConfiguration;


    public KeyService(KeyDao keyDao, UserDao userDao) {
        this.keyDao = keyDao;
        this.userDao = userDao;
    }

    @Bean
    public SecretKey getSecretKey() {
        return new SecretKeySpec(encryptionConfiguration.key.getBytes(), encryptionConfiguration.algorithm);
    }

    public SecretKey getRandomKey() throws NoSuchAlgorithmException {
        final KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        return keygenerator.generateKey();
    }



    public Key generateRandomKey() throws NoSuchAlgorithmException {
        SecretKey secretKey = getRandomKey();

        return new Key(secretKey);
    }


    //Generating key for given user
    public Key generateKey(Long userId) throws NoSuchAlgorithmException {
        SecretKey secretKey = getRandomKey();
        Key key = new Key(userId, secretKey);

        return key;
    }

    public void setKeyToUser(User user) throws NoSuchAlgorithmException {
        final long keyId = user.getUserId();

        Key key = generateKey(keyId);
        keyDao.save(key);

        userDao.setKey(keyId, keyId);
    }

}
