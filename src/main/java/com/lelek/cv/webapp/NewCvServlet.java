package com.lelek.cv.webapp;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;
import com.lelek.cv.service.CvFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class NewCvServlet extends HttpServlet {
    private final String PATH = "C:/Users/vleletc/IdeaProjects/cv/src/main/resources/temp.yml";
    private CV cv;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/new.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        cv = new CV();
        List<JobPlace> jobPlaces = new ArrayList<>();
        cv.setPerson(new Person.PersonBuilder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .birthday(LocalDate.parse(request.getParameter("birthday")))
                .build());
        cv.setContact(new Contact.ContactBuilder()
                .phoneNumber(request.getParameter("phoneNumber"))
                .address(request.getParameter("address"))
                .eMail(request.getParameter("eMail"))
                .build());
        if(!request.getParameter("company0").isEmpty()) {
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .company(request.getParameter("company0"))
                    .city(request.getParameter("city0"))
                    .from(LocalDate.parse(request.getParameter("from0")))
                    .to(LocalDate.parse(request.getParameter("to0")))
                    .position(request.getParameter("position0"))
                    .build());
        }
        if(!request.getParameter("company1").isEmpty()) {
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .company(request.getParameter("company1"))
                    .city(request.getParameter("city1"))
                    .from(LocalDate.parse(request.getParameter("from1")))
                    .to(LocalDate.parse(request.getParameter("to1")))
                    .position(request.getParameter("position1"))
                    .build());
        }
        if(!request.getParameter("company2").isEmpty()) {
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .company(request.getParameter("company2"))
                    .city(request.getParameter("city2"))
                    .from(LocalDate.parse(request.getParameter("from2")))
                    .to(LocalDate.parse(request.getParameter("to2")))
                    .position(request.getParameter("position2"))
                    .build());
        }

        cv.setJobPlaces(jobPlaces);
        setFieldsValues(cv, request);
        new CvFacade().writeCvInFile(PATH, cv);
        new CvServlet().doPost(request, response);
    }

    private void setFieldsValues(CV cv, HttpServletRequest request){
        request.setAttribute("cvFields", cv);
    }

}
