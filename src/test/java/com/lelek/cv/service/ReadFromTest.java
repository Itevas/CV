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
        cvList.add(readFrom.readFile("cv.json"));
        cvList.add(readFrom.readFile("cv.yml"));
        cvList.add(readFrom.readFile("cv.txt"));
        cvList.add(readFrom.readFile("cv.xml"));

        for (int i = 0; i<3; i++) {
       Assert.assertEquals(cvList.get(i).getPerson().getFirstName(), cvList.get(i+1).getPerson().getFirstName());
       Assert.assertEquals(cvList.get(i).getPerson().getLastName(), cvList.get(i+1).getPerson().getLastName());
       Assert.assertEquals(cvList.get(i).getPerson().getBirthday(), cvList.get(i+1).getPerson().getBirthday());
       Assert.assertEquals(cvList.get(i).getContact().getAddress(), cvList.get(i+1).getContact().getAddress());
       Assert.assertEquals(cvList.get(i).getContact().getPhoneNumber(), cvList.get(i+1).getContact().getPhoneNumber());
       Assert.assertEquals(cvList.get(i).getContact().geteMail(), cvList.get(i+1).getContact().geteMail());
        }
    }

}