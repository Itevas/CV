package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lelek.cv.model.CV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.lelek.cv.service.WriterInFile.writeInYmlFile;

public class ReadFrom {

    private static final String PATH = "src/";
    private static boolean goOn = true;

    public static void readFrom(String fileName) throws IOException {
        fileName = PATH + fileName;
        Scanner scanFile = new Scanner(new File(fileName));

        while (scanFile.hasNextLine() & goOn) {
            String line = scanFile.nextLine();
            if (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        System.out.println("Empty");
                        continue;
                    } else if (line.charAt(i) == '<') {
                        System.out.println("XmlReader");
                        goOn = false;
                        writeInYmlFile((new XmlMapper()).readValue(new File(fileName), CV.class));
                        break;
                    } else if (line.charAt(i) == '{') {
                        System.out.println("JsonReader");
                        writeInYmlFile((new ObjectMapper()).readValue(new File(fileName), CV.class));
                        goOn = false;
                        break;
                    } else {
                        System.out.println("TextReader");
                        parseTxt(fileName);
                        goOn = false;
                        break;
                    }
                }
            }
        }
    }

    private static void parseTxt(String fileName) throws FileNotFoundException {
        CV cv = new CV();
        Scanner scanFile = new Scanner(new File(fileName));
        scanFile.useDelimiter(",;:'=\" *");

        while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine();
            if (line.equals("firstName")) {
                cv.getPerson().setFirstName(scanFile.nextLine());
            }
        }
    }
}
