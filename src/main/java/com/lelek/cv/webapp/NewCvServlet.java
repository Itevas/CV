package com.lelek.cv.webapp;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.Person;
import com.lelek.cv.service.CvFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/")
public class NewCvServlet extends HttpServlet {
    private final String PATH = "C:\\Users\\vleletc\\IdeaProjects\\cv\\src\\main\\resources\\temp.yml";

    private CV cv;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/new.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        cv = new CV();
        Person person = new Person.PersonBuilder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .birthday(LocalDate.parse(request.getParameter("birthday")))
                .build();
        Contact contact = new Contact.ContactBuilder()
                .phoneNumber(request.getParameter("phoneNumber"))
                .address(request.getParameter("address"))
                .eMail(request.getParameter("eMail"))
                .build();
// Add list of Job Places if not empty
//cv.setJobPlaces();
        cv.setPerson(person);
        cv.setContact(contact);
        new CvFacade().writeCvInFile(PATH, cv);
        new CvServlet().doPost(request, response);
    }
}
