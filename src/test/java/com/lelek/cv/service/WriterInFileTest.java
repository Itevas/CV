package com.lelek.cv.service;

import com.lelek.cv.model.Cv;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class WriterInFileTest {

    @Test
    public void testWriteInYmlFile() throws IOException {
        WriterInFile writerInFile = new WriterInFile();
        Cv expectedCv = (new ReadFrom().readFile("src/main/resources/cv.json"));
        writerInFile.writeInYmlFile(expectedCv);
        Cv actualCv = (new ReadFrom().readFile("src/main/resources/out.yml"));

        Assert.assertEquals(expectedCv.toString(), actualCv.toString());

    }
}