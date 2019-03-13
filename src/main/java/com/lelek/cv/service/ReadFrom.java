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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.lelek.cv.service.WriterInFile.writeInYmlFile;

public class ReadFrom {

    private static final String PATH = "src/main/resources/";

    private static Logger LOGGER = Logger.getLogger("com.lelek.cv.service.ReadFrom");

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");

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
        int i = 0;
        Person person = new Person();
        Contact contact = new Contact();
        List<JobPlace> jobPlaces = new ArrayList<>();
        Scanner scanTxt = new Scanner(new File(fileName)).useDelimiter(":");

        while (scanTxt.hasNextLine()) {
            String line = scanTxt.next().trim();

            if (line.equals("firstName")) {
                person.setFirstName(scanTxt.next().trim());
            } else if (line.equals("lastName")) {
                person.setLastName(scanTxt.next().trim());
            } else if (line.equals("birthday")) {
                person.setBirthday(LocalDate.parse(scanTxt.next().trim(), FORMATTER));
            } else if (line.equals("phoneNumber")) {
                contact.setPhoneNumber(scanTxt.next().trim());
            } else if (line.equals("address")) {
                contact.setAddress(scanTxt.next().trim());
            } else if (line.equals("eMail")) {
                contact.seteMail(scanTxt.next().trim());
            } else if (line.equals("company")) {
                jobPlaces.add( new JobPlace());
                jobPlaces.get(i).setCompany(scanTxt.next().trim());
            } else if (line.equals("city")) {
                jobPlaces.get(i).setCity(scanTxt.next().trim());
            } else if (line.equals("from")) {
                jobPlaces.get(i).setFrom(LocalDate.parse(scanTxt.next().trim(), FORMATTER));
            } else if (line.equals("to")) {
                String date = scanTxt.next().trim();
                if (date.equals("This time")) {
                    jobPlaces.get(i).setTo(LocalDate.now());
                } else {
                    jobPlaces.get(i).setTo(LocalDate.parse(date, FORMATTER));
                }
            } else if (line.equals("position")) {
                jobPlaces.get(i).setPosition(scanTxt.next().trim());
                i++;
            }
        }
        scanTxt.close();
        cv.setJobPlaces(jobPlaces);
        cv.setContact(contact);
        cv.setPerson(person);
        writeInYmlFile(cv);
    }
}
