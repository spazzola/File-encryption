package com.filesheriff.encryption;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PasswordEncriptionServiceTests {


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void shouldEncryptPassword() {
        //given
        String password = "mariano232";

        //when
        String encryptedPassword = passwordEncoder.encode(password);

        //then
        assertThat(password.equals(encryptedPassword)).isFalse();
    }

}
