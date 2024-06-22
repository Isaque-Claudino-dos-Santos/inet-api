package com.inet.framework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.inet.framework.constants.ExitCode;
import com.inet.languages.Languages;
import com.inet.settings.Env;

public class Lang {
    private static Gson gson = new Gson();

    public static String get(String pattern) {
        String translatedText = null;
        String fileContent = "";
        String[] parts = pattern.split("\\.");
        String folder = parts[0];
        String filePath = Env.DIR_BASE + "/languages/" + folder.toLowerCase() + "/" + Env.API_LANG.toLowerCase()
                + ".json";
        File file = null;
        Scanner fileScanner = null;

        try {
            file = new File(filePath);
            fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                fileContent += fileScanner.nextLine();
            }

            Languages json = gson.fromJson(fileContent, Languages.class);
            
            for (int i = 1; i < parts.length; i++) {
                String field = parts[i].toUpperCase();
                Object content = json.getClass().getDeclaredField(field).get(json);

                if (content instanceof String) {
                    translatedText = (String) content;
                } else {
                    json = (Languages) content;
                }
            }
        } catch (FileNotFoundException exception) {
            System.err.println("File not found");
            System.err.println(filePath);
            System.exit(ExitCode.FILE_NOT_FOUND);
        } catch (NoSuchFieldException exception) {
            System.err.println("Field not found");
            System.err.println("class: " + Languages.class.getName());
            System.err.println("Field: " + exception.getMessage());
            System.exit(ExitCode.FILE_NOT_FOUND);
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }

        return translatedText;
    }
}
