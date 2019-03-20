package com.lelek.cv.webapp;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/")
public class NewCvServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/new.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        CV cv = new CV();
        Person person = new Person.PersonBuilder()
                .firstName(request.getAttribute("firstName").toString())
                .lastName(request.getAttribute("lastName").toString())
                .birthday(LocalDate.parse(request.getAttribute("birthday").toString()))
                .build();
        Contact contact = new Contact.ContactBuilder()
                .phoneNumber(request.getAttribute("phoneNumber").toString())
                .address(request.getAttribute("address").toString())
                .eMail(request.getAttribute("eMail").toString())
                .build();
        cv.setPerson(person);
        cv.setContact(contact);

        request.getRequestDispatcher("/WEB-INF/response.jsp").forward(request, response);

    }
}
