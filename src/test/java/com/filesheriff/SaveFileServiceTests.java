package com.filesheriff;

import com.filesheriff.encryption.EncryptionService;
import com.filesheriff.loadfile.LoadFileService;
import com.filesheriff.savefile.SaveFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SaveFileServiceTests {

    @Autowired
    private SaveFileService saveFileService;

    @Autowired
    private LoadFileService loadFileService;

    @Autowired
    private EncryptionService encryptionService;

    @Test
    void contextLoads() {
    }


    @Test
    void checkThatOryginalFileWasSaved() throws IOException {
        byte[] text = "Hello world".getBytes();

        saveFileService.saveFile(text, System.getProperty("user.dir") + "/decrypted-files/testFile.txt");
    }

    @Test
    void CheckThathFileWasEncryptedAndDecryptedAgain() throws IOException {
        File file = loadFileService.loadFile(System.getProperty("user.dir") + "/decrypted-files/text.txt");
        byte[] decryptedBytes = loadFileService.convertToByte(file);

        byte[] encryptedBytes = encryptionService.encryption(decryptedBytes);
        saveFileService.saveFile(encryptedBytes, System.getProperty("user.dir") + "/encrypted-files/encryptedText.txt");

        File encryptedFile = loadFileService.loadFile(System.getProperty("user.dir") + "/encrypted-files/encryptedText.txt");
        encryptedBytes = loadFileService.convertToByte(encryptedFile);

        byte[] result = encryptionService.decryption(encryptedBytes);

        assertThat(Arrays.equals(decryptedBytes, result)).isTrue();
    }

}
