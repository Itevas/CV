package com.lelek.cv.service;

import com.lelek.cv.model.CV;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class WriterInFileTest {

    @Test
    public void testWriteInYmlFile() throws IOException {
        WriterInFile writerInFile = new WriterInFile();
        CV expectedCv = (new ReadFrom().readFile("src/main/resources/cv.json"));
        writerInFile.writeInYmlFile(expectedCv);
        CV actualCv = (new ReadFrom().readFile("src/main/resources/out.yml"));

        Assert.assertEquals(expectedCv.toString(), actualCv.toString());

    }
}