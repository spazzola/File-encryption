package com.filesheriff.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/crypto")
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public ResponseEntity<byte[]> encryptFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        byte[] encryptedBytes = encryptionService.encryption(bytes);

        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(encryptedBytes, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<byte[]> decryption(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        byte[] decryptedBytes = encryptionService.decryption(bytes);

        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<>(decryptedBytes, responseHeaders, HttpStatus.OK);
    }

}
