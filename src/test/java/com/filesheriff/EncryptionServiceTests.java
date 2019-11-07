package com.filesheriff;

import com.filesheriff.encryption.EncryptionService;
import com.filesheriff.loadfile.LoadFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EncryptionServiceTests {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private LoadFileService loadFileService;



    @Test
    void contextLoads() {
    }

    @Test
    void checkThatBytesWasEncrypted() {
        String text = "Hello world";

        byte[] decryptedBytes = text.getBytes();
        byte[] encryptedBytes = encryptionService.encryption(decryptedBytes);

        assertThat(Arrays.equals(decryptedBytes, encryptedBytes)).isFalse();
    }


    @Test
    void checkThatTextWasEncryptedAndDecryptedAgain() {
        String text = "Encrypting test";

        byte[] decryptedBytes = text.getBytes();
        byte[] encryptedBytes = encryptionService.encryption(decryptedBytes);
        byte[] resultBytes = encryptionService.decryption(encryptedBytes);

        String resultString = new String(resultBytes);

        assertThat(text.equals(resultString)).isTrue();
    }


}
