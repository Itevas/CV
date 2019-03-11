package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.lelek.cv.service.WriterInFile.writeInYmlFile;

class ReadFrom {

    private static final String PATH = "src/main/resources/";
    private static Logger LOGGER = Logger.getLogger("com.lelek.cv.service.ReadFrom");
    private static boolean goOn = true;

    static void readFrom(String fileName) throws IOException {
        fileName = PATH + fileName;
        Scanner scanFile = new Scanner(new File(fileName));

        while (scanFile.hasNextLine() & goOn) {
            String line = scanFile.nextLine();
            if (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        LOGGER.info("Space");
                        continue;
                    } else if (line.charAt(i) == '<') {
                        LOGGER.info("XmlReader");
                        goOn = false;
                        writeInYmlFile((new XmlMapper()).readValue(new File(fileName), CV.class));
                        break;
                    } else if (line.charAt(i) == '{') {
                        LOGGER.info("JsonReader");
                        writeInYmlFile((new ObjectMapper()).readValue(new File(fileName), CV.class));
                        goOn = false;
                        break;
                    } else if (line.charAt(i) == '-') {
                        LOGGER.info("YamlReader");
                        writeInYmlFile(new ObjectMapper(new YAMLFactory()).readValue(new File(fileName), CV.class));
                        goOn = false;
                        break;
                    } else {
                        LOGGER.info("TextReader");
                        parseTxt(fileName);
                        goOn = false;
                        break;
                    }
                }
            }
        }
    }

    private static void parseTxt(String fileName) throws IOException {
        CV cv = new CV();
        Person person = new Person();
        Contact contact = new Contact();
        JobPlace jobPlace = new JobPlace();
        List<JobPlace> jobPlaces = new ArrayList<>();
        Scanner scanTxt = new Scanner(new File(fileName)).useDelimiter(" : ");

        while (scanTxt.hasNextLine()) {
            String line = scanTxt.next().trim();
            LOGGER.info(line);

            if (line.equals("firstName")) {
                person.setFirstName(scanTxt.next().trim());
                LOGGER.info(person.getFirstName());
            } else if (line.equals("lastName")) {
                person.setLastName(scanTxt.next().trim());
            } else if (line.equals("birthday")) {
                person.setBirthday(scanTxt.next().trim());
            } else if (line.equals("phoneNumber")) {
                contact.setPhoneNumber(Long.parseLong(scanTxt.next().trim()));
            } else if (line.equals("address")) {
                contact.setAddress(scanTxt.next().trim());
            } else if (line.equals("eMail")) {
                contact.seteMail(scanTxt.next().trim());
            } else if (line.equals("company")) {
                if (jobPlace.getCompany() != null) {
                    jobPlaces.add(jobPlace);
                }
                jobPlace.setCompany(scanTxt.next().trim());
            } else if (line.equals("city")) {
                jobPlace.setCity(scanTxt.next().trim());
            } else if (line.equals("from")) {
                jobPlace.setFrom(scanTxt.next().trim());
            } else if (line.equals("to")) {
                jobPlace.setTo(scanTxt.next().trim());
            } else if (line.equals("position")) {
                jobPlace.setPosition(scanTxt.next().trim());
            }
        }
        scanTxt.close();
        cv.setJobPlaces(jobPlaces);
        cv.setContact(contact);
        cv.setPerson(person);
        //writeInYmlFile(cv);
    }
}
