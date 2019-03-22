package com.lelek.cv.service;

import com.lelek.cv.model.CV;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Test
public class ReadFromTest {

    @Test
    public void testReadFile() throws IOException {
        ReadFrom readFrom = new ReadFrom();
        List<CV> cvList = new ArrayList<>();
        cvList.add(readFrom.readFile("src/main/resources/cv.json"));
        cvList.add(readFrom.readFile("src/main/resources/cv.yml"));
        cvList.add(readFrom.readFile("src/main/resources/cv.txt"));
        cvList.add(readFrom.readFile("src/main/resources/cv.xml"));

        for (int i = 0; i<3; i++) {
       Assert.assertEquals(cvList.get(i).toString(), cvList.get(i+1).toString());

        }
    }

}