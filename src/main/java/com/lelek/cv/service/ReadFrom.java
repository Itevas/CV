package com.lelek.cv.service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFrom {

    private static final String PATH = "src/";

    public static void readFrom(String fileName) throws IOException {
        fileName = PATH + fileName;
        File sourceFile = new File(fileName);
        Scanner scanFile = new Scanner(sourceFile);
        String line = scanFile.nextLine();

        if (line != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ' ') { return;
                } else if (line.charAt(i) == '<') {
                    (new ReaderFromFile()).readDataFromXML(fileName);
                    break;
                } else {
                    (new ReaderFromFile()).readDataFromJson(fileName);
                }

            }
        }

    }
}
