package com.filesheriff.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

@Configuration
public class EncryptionConfiguration {

    @Value("${encryption.key}")
    private String key;

    @Value("${encryption.algorithm}")
    private String algorithm;

    @Bean
    public SecretKey getSecretKey() {
        return new SecretKeySpec(key.getBytes(), algorithm);
    }

    public SecretKey getRandomKey() throws NoSuchAlgorithmException {
        final KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        return keygenerator.generateKey();
    }

}