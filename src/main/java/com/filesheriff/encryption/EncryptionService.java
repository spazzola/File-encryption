package com.filesheriff.encryption;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class EncryptionService {

    private final SecretKey secretKey;
    
    public EncryptionService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public byte[] encryption(byte[] bytesArray) {
        byte[] result = null;

        try {
            Cipher desCipher = Cipher.getInstance("AES");

            desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            result = desCipher.doFinal(bytesArray);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public byte[] decryption(byte[] bytesArray) {
        byte[] result = null;

        try {
            Cipher desCipher = Cipher.getInstance("AES");

            desCipher.init(Cipher.DECRYPT_MODE, secretKey);
            result = desCipher.doFinal(bytesArray);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] convertToByte(File file) throws IOException {
        return Files.readAllBytes(file.toPath());

    }

}
