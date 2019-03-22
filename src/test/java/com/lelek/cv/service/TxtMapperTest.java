package com.lelek.cv.service;

import com.lelek.cv.model.CV;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class TxtMapperTest {

    @Test
    public void testReadValue() throws IOException {
        CV cv = (new TxtMapper("src/main/resources/cv.txt")).readValue();
        CV cv1 = (new ReadFrom()).readFile("src/main/resources/cv.json");

        Assert.assertEquals(cv.toString(), cv1.toString());

    }
}