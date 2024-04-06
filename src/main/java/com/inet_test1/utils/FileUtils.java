package com.inet_test1.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
    public static Scanner scannerFile(String path) throws FileNotFoundException {
        File file = new File(path);
        return new Scanner(file);
    }

    public static String readAll(String path) throws FileNotFoundException {
        String fileText = null;
        Scanner file = scannerFile(path);

        while (file.hasNextLine()) {
            fileText += file.nextLine();
        }

        file.close();

        return fileText;
    }
}
