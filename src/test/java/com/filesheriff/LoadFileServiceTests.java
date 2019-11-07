package com.filesheriff;

import com.filesheriff.loadfile.LoadFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LoadFileServiceTests {

    @Autowired
    private LoadFileService loadFileService;


    @Test
    void contextLoads() {
    }

    @Test
    void checkThatTextWasLoadedFromFile() throws IOException {
        String expectedResult = "Loading test.";

        String result = loadFileService.loadStringFromFile();

        assertThat(result.equals(expectedResult)).isTrue();
        //assertThat(result.contentEquals(expectedResult)).isTrue();
    }

    @Test
    void checkThatFileWasLoaded() {
        File expectedFile = new File("/Users/spazzola/Desktop/filesheriff/decrypted-files/text.txt");

        File result = loadFileService.loadFile(System.getProperty("user.dir") + "/decrypted-files/text.txt");

        assertThat(result.equals(expectedFile)).isTrue();
    }

}
