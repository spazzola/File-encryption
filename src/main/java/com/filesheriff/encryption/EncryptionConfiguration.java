package com.filesheriff.encryption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

@Configuration
public class EncryptionConfiguration {

    //key for DES
    //private String key = "12d4c67a";

    private String key = "1234567890123456";


    @Bean
    public SecretKey getSecretKey() {
        return new SecretKeySpec(key.getBytes(), "AES");
    }

    public SecretKey getRandomKey() throws NoSuchAlgorithmException {
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        return keygenerator.generateKey();
    }

}