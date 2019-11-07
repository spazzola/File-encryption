package com.filesheriff.savefile;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Service
public class SaveFileService {

    private final static String PROJECT_PATH = System.getProperty("user.dir");
    private final static String FOLDER_PATH = PROJECT_PATH + "/encrypted-files";
    private final static String FILE_NAME = "/textEncrypted.txt";


    public void saveFile(byte[] fileBytes, String path) throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(fileBytes);
        fos.close();
    }
}
