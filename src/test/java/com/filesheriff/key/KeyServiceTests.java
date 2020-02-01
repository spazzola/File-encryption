package com.filesheriff.key;

import com.filesheriff.key.KeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class KeyServiceTests {


    @Autowired
    private KeyService keyService;

    @Test
    void contextLoads() {
    }

    @Test
    void checkThatGeneratedKeysAreEquals() {
        SecretKey firstKey = keyService.getSecretKey();
        SecretKey secondKey = keyService.getSecretKey();

        assertThat(firstKey.equals(secondKey)).isTrue();
    }

    @Test
    void checkThatGeneratedKeyAreNotEquals() throws NoSuchAlgorithmException {
        SecretKey firstKey = keyService.getRandomKey();
        SecretKey secondKey = keyService.getRandomKey();

        assertThat(firstKey.equals(secondKey)).isFalse();
    }
}
