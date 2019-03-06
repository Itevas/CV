package com.lelek.cv;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class CreateYAML {

    private static final String OUTPATH = "srs/cv.yml";

    public CreateYAML(){

    }
    public void create(Map<String, String> info){

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        String result = yaml.dump(info);

        try {
            PrintWriter writeInFile = new PrintWriter(OUTPATH);
            writeInFile.println(result);
            System.out.println(result);
            writeInFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
