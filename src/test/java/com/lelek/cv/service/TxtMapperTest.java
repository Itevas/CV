package com.lelek.cv.service;

import com.lelek.cv.model.CV;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TxtMapperTest {

    @Test
    public void testReadValue() throws IOException {
        CV actualCv = (new TxtMapper("src/main/resources/cv.txt")).readValue();
        CV expectedCv = (new ReadFrom()).readFile("src/main/resources/cv.json");

        Assert.assertEquals(actualCv.toString(), expectedCv.toString());

    }
}