package com.filesheriff;

import com.filesheriff.encryption.EncryptionConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EncryptionConfigurationTests {

    @Autowired
    private EncryptionConfiguration encryptionConfiguration;

    @Test
    void contextLoads() {
    }

    @Test
    void checkThatGeneratedKeysAreEquals() {
        SecretKey firstKey = encryptionConfiguration.getSecretKey();
        SecretKey secondKey = encryptionConfiguration.getSecretKey();

        assertThat(firstKey.equals(secondKey)).isTrue();
    }

    @Test
    void checkThatGeneratedKeyAreNotEquals() throws NoSuchAlgorithmException {
        SecretKey firstKey = encryptionConfiguration.getRandomKey();
        SecretKey secondKey = encryptionConfiguration.getRandomKey();

        assertThat(firstKey.equals(secondKey)).isFalse();
    }
}
