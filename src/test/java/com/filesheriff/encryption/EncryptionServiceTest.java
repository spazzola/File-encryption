package com.filesheriff.encryption;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EncryptionServiceTest {

    @Autowired
    private EncryptionService encryptionService;


    @Test
    void shouldEncryptText() {
        //given
        String text =  "Tested string";
        byte[] decryptedBytes = text.getBytes();

        //when
        byte[] encryptedBytes = encryptionService.encryption(decryptedBytes);

        //then
        assertThat(Arrays.equals(decryptedBytes, encryptedBytes)).isFalse();
    }

    @Test
    void shouldDecryptText() {
        //given
        String text = "Tested string";
        byte[] decryptedBytes = text.getBytes();
        byte[] encryptedBytes = encryptionService.encryption(decryptedBytes);

        //when
        byte[] result = encryptionService.decryption(encryptedBytes);

        //then
        assertThat(Arrays.equals(decryptedBytes, result)).isTrue();
    }

    @Test
    void shouldIllegalArgumentExceptionWhenInputIsNull() {
        //given
        byte[] bytes = null;

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            encryptionService.encryption(bytes);
        });
    }

}
