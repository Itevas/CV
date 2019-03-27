package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lelek.cv.model.Cv;

import java.io.File;
import java.io.IOException;

class WriterInFile {

    private static final String OUT_PATH_YAML = "src/main/resources/out.yml";

    public void writeInYmlFile(String fileName, Cv cv) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File(fileName), cv);
    }

    public void writeInYmlFile(Cv cv) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File(OUT_PATH_YAML), cv);
    }

    public void writeInXmlFile (Cv cv) throws IOException{
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File("src/main/resources/out.xml"), cv);
    }

    public void writeInJsonFile (Cv cv) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/resources/out.json"), cv);
    }
}
