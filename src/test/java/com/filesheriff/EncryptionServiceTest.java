package com.filesheriff;

import com.filesheriff.encryption.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EncryptionServiceTest {

    @Autowired
    EncryptionService encryptionService;


    @Test
    public void chechThatBytesHaveBeenEncrypted() {
        byte[] decryptedBytes = "Tested string".getBytes();
        byte[] encryptedBytes = encryptionService.encryption(decryptedBytes);

        assertThat(!Arrays.equals(decryptedBytes, encryptedBytes)).isTrue();
    }

    @Test
    public void checkThatBytesHaveBeenDecrypted() {
        byte[] decryptedBytes = "Tested string".getBytes();
        byte[] encryptedBytes = encryptionService.encryption(decryptedBytes);
        byte[] result = encryptionService.decryption(encryptedBytes);

        assertThat(Arrays.equals(decryptedBytes, result)).isTrue();
    }
}
