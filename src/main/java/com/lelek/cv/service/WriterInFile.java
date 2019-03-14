package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lelek.cv.model.CV;

import java.io.File;
import java.io.IOException;



class WriterInFile {

    private static final String OUT_PATH_YAML = "src/main/resources/out.yml";

    public void writeInYmlFile(CV cv) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File(OUT_PATH_YAML), cv);
    }

}
