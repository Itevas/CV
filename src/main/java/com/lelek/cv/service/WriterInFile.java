package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lelek.cv.model.CV;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

class WriterInFile {

    private static final String OUT_PATH_XML = "src/out.xml";
    private static final String OUT_PATH_YAML = "src/out.yml";
    private static final String OUT_PATH_JSON = "src/out.json";
    private static ObjectMapper mapper;

    static void writeInXmlFile(CV cv) throws IOException {
        mapper = new XmlMapper();
        mapper.writeValue(new File(OUT_PATH_XML), cv);
    }

    static void writeInYmlFile(CV cv) throws IOException{
        Yaml yaml = new Yaml();
        PrintWriter printWriter = new PrintWriter(OUT_PATH_YAML);
        yaml.dump(cv, printWriter);
        printWriter.close();
    }

    static void writeInJsonFile(CV cv) throws IOException{
        mapper = new ObjectMapper();
        mapper.writeValue(new File(OUT_PATH_JSON), cv);
    }
}
