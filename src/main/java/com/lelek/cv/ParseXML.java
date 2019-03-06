package com.lelek.cv;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;

public class ParseXML {

    private static final String INPATH = "src/cv.xml";
    private static final String OUTPATH = "src/out.xml";
    private XmlMapper mapper;

    public CV getInfoFromXML() throws IOException {
        String cvString = inputStreamToString(new FileInputStream(INPATH));
        mapper = new XmlMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        CV cv = mapper.readValue(cvString, CV.class);
        return cv;
    }

    public void writeInFile(CV cv) throws IOException{
        mapper = new XmlMapper();

        mapper.writeValue(new File(OUTPATH), cv);
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String string;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((string = bufferedReader.readLine()) != null) {
            stringBuilder.append(string +"\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

}
