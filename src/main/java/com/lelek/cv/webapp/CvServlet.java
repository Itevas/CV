package com.lelek.cv.webapp;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Person;
import com.lelek.cv.service.CvFacade;
import com.lelek.cv.service.ReadFrom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/")
public class CvServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Person person = new Person.PersonBuilder()
                .setFirstName("John")
                .setLastName("Smith")
                .setBirthday(LocalDate.parse("1985-07-26"))
                .build();

        request.setAttribute("name", person.getFirstName());
        request.setAttribute("lastName", person.getLastName());
        request.getRequestDispatcher("/WEB-INF/response.jsp").forward(request, response);
    }
}
