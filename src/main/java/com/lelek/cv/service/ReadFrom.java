package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lelek.cv.model.CV;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class ReadFrom {

    private static final String PATH = "src/main/resources/";

    public CV readFile(String fileName) throws IOException {
        fileName = PATH + fileName;
        Scanner scanFile = new Scanner(new File(fileName));

        while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine();
            if (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        continue;
                    } else if (line.charAt(i) == '<') {

                        return ((new XmlMapper()).readValue(new File(fileName), CV.class));

                    } else if (line.charAt(i) == '{') {

                        return ((new ObjectMapper()).readValue(new File(fileName), CV.class));

                    } else if (line.charAt(i) == '-') {

                        return (new ObjectMapper(new YAMLFactory()).readValue(new File(fileName), CV.class));

                    } else {

                        return ((new TxtMapper(fileName)).readValue());

                    }
                }
            }
        }
        return null;
    }
}
