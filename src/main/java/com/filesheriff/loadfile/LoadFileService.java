package com.filesheriff.loadfile;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoadFileService {

    private final static String PROJECT_PATH = System.getProperty("user.dir");
    private final static String FOLDER_PATH = PROJECT_PATH + "/decrypted-files";
    private final static String FILE_NAME = "/text.txt";


    public File loadFile(String pathURL) {
        //Path path = Paths.get(FOLDER_PATH + FILE_NAME);
        Path path = Paths.get(pathURL);
        return path.toFile();
    }

    public byte[] convertToByte(File file) throws IOException {
        return Files.readAllBytes(file.toPath());

    }

    public String loadStringFromFile() throws IOException {
        Path path = Paths.get(FOLDER_PATH + FILE_NAME);

        File file = path.toFile();
        System.out.println(file);

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

        return data;
    }

}
