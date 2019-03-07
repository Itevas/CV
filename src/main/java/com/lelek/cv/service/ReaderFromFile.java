package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lelek.cv.model.CV;

import java.io.*;

class ReaderFromFile {

    private ObjectMapper mapper;
    private CV cv;

    void readDataFromXML(String pathToFile) throws IOException {
        String cvString = (new ReaderFromFile()).inputStreamToString(new FileInputStream(pathToFile));
        mapper = new XmlMapper();
        cv = mapper.readValue(cvString, CV.class);
        writeInFiles(cv);
    }

    void readDataFromJson(String pathToFile) throws IOException {
        mapper = new ObjectMapper();
        cv = mapper.readValue(new File(pathToFile), CV.class);
        writeInFiles(cv);
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String string;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((string = bufferedReader.readLine()) != null) {
            stringBuilder.append(string);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    private void writeInFiles(CV cv) throws IOException {
        WriterInFile.writeInXmlFile(cv);
        WriterInFile.writeInYmlFile(cv);
        WriterInFile.writeInJsonFile(cv);
    }
}
