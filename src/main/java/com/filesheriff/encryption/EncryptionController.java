package com.filesheriff.encryption;

import com.filesheriff.file.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RequestMapping("/crypto")
@Controller
public class EncryptionController {

    private final EncryptionService encryptionService;
    private final FileService fileService;

    public EncryptionController(EncryptionService encryptionService, FileService fileService) {
        this.encryptionService = encryptionService;
        this.fileService = fileService;
    }

    @PostMapping("/encryption")
    public String encryptFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        byte[] encryptedBytes = encryptionService.encryption(bytes);

        File file = fileService.convert(multipartFile);
        fileService.saveFile(encryptedBytes, file);

        return "homePage";
    }

    @PostMapping("/decryption")
    public String decryption(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        System.out.println("decrypt");
        byte[] bytes = multipartFile.getBytes();
        byte[] decryptedBytes = encryptionService.decryption(bytes);

        File file = fileService.convert(multipartFile);
        fileService.saveFile(decryptedBytes, file);

        return "homePage";
    }

}
