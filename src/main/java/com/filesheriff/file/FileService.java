package com.filesheriff.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {


    public void saveFile(byte[] fileBytes, File f) throws IOException {
        String path = f.getAbsolutePath();
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(fileBytes);
        fos.close();
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
