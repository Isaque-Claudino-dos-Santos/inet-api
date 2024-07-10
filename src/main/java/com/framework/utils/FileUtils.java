package com.framework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
    public static String getAll(String path) {
        String fileContent = "";

        File file = new File(path);

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                fileContent += fileScanner.nextLine();
            }

            fileScanner.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return fileContent;
    }
}
