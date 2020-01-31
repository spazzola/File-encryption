package com.filesheriff.encryption;

import org.springframework.stereotype.Service;

import javax.crypto.*;


@Service
public class EncryptionService {

    private final SecretKey secretKey;

    public EncryptionService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public byte[] encryption(byte[] bytesArray) {
        if (bytesArray == null) {
            throw new IllegalArgumentException("Input cant be null");
        }

        final byte[] result;

        try {
            Cipher desCipher = Cipher.getInstance("AES");
            desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            result = desCipher.doFinal(bytesArray);
        } catch (Exception e) {
            e.printStackTrace(); //chcesz zalogowac ten
            throw new RuntimeException("Cos poszlo nie tak podczas szyfrowania", e); // i rzucic swoj
        }

        return result;
    }


    public byte[] decryption(byte[] bytesArray) {
        if (bytesArray == null) {
            throw new IllegalArgumentException("Input cant be null");
        }

        final byte[] result;

        try {
            Cipher desCipher = Cipher.getInstance("AES");
            desCipher.init(Cipher.DECRYPT_MODE, secretKey);
            result = desCipher.doFinal(bytesArray);
        } catch (Exception e) {
            e.printStackTrace(); //zaloguj wyjatek
            throw new RuntimeException("Cos poszlo nie tak podczas szyfrowania", e); //i rzuc wlasny
        }

        return result;
    }

}
