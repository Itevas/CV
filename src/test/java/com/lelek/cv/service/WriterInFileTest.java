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
        CV cv = (new ReadFrom().readFile("cv.json"));
        writerInFile.writeInYmlFile(cv);
        CV cv1 = (new ReadFrom().readFile("out.yml"));

        Assert.assertEquals(cv.getPerson().getFirstName(), cv1.getPerson().getFirstName());
        Assert.assertEquals(cv.getPerson().getLastName(), cv1.getPerson().getLastName());
        Assert.assertEquals(cv.getPerson().getBirthday(), cv1.getPerson().getBirthday());
        Assert.assertEquals(cv.getContact().getAddress(), cv1.getContact().getAddress());
        Assert.assertEquals(cv.getContact().getPhoneNumber(), cv1.getContact().getPhoneNumber());
        Assert.assertEquals(cv.getContact().geteMail(), cv1.getContact().geteMail());
    }
}