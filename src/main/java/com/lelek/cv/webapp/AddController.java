package com.lelek.cv.webapp;

import com.lelek.cv.model.*;
import com.lelek.cv.service.CvFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/add")
public class AddController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.webapp.AddController");
    private final String PATH = "C:/Users/vleletc/IdeaProjects/cv/src/main/resources/temp.yml";
    private Cv cv;
    int id;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            id = Integer.valueOf(request.getParameter("id"));
            LOGGER.severe("Id not NULL");
            LOGGER.severe("Request Get Parametr: " + id);
            CvFacade facade = new CvFacade();
            try {
                cv = facade.get(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (request.getParameter("cv_edit") != null){
            LOGGER.severe("EDIT NOT NULL");
            request.getParameter("cv_edit");
        }

        LOGGER.severe("cv_edit parametr: "+request.getParameter("cv_edit"));
        LOGGER.severe("inputStream: "+request.getInputStream().toString());
        request.setAttribute("cv_edit", cv);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("ok");
        request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        cv = new Cv();
//        List<JobPlace> jobPlaces = new ArrayList<>();
//        cv.setPerson(new Person.PersonBuilder()
//                .firstName(request.getParameter("firstName"))
//                .lastName(request.getParameter("lastName"))
//                .birthday(LocalDate.parse(request.getParameter("birthday")))
//                .build());
//        cv.setContact(new Contact.ContactBuilder()
//                .phoneNumber(request.getParameter("phoneNumber"))
//                .address(request.getParameter("address"))
//                .eMail(request.getParameter("eMail"))
//                .build());
//        if (!request.getParameter("company0").isEmpty()) {
//            jobPlaces.add(new JobPlace.JobPlaceBuilder()
//                    .company(request.getParameter("company0"))
//                    .city(request.getParameter("city0"))
//                    .from(LocalDate.parse(request.getParameter("from0")))
//                    .to(LocalDate.parse(request.getParameter("to0")))
//                    .position(Position.getByName(request.getParameter("position0")))
//                    .build());
//        }
//        if (!request.getParameter("company1").isEmpty()) {
//            jobPlaces.add(new JobPlace.JobPlaceBuilder()
//                    .company(request.getParameter("company1"))
//                    .city(request.getParameter("city1"))
//                    .from(LocalDate.parse(request.getParameter("from1")))
//                    .to(LocalDate.parse(request.getParameter("to1")))
//                    .position(Position.getByName(request.getParameter("position1")))
//                    .build());
//        }
//        if (!request.getParameter("company2").isEmpty()) {
//            jobPlaces.add(new JobPlace.JobPlaceBuilder()
//                    .company(request.getParameter("company2"))
//                    .city(request.getParameter("city2"))
//                    .from(LocalDate.parse(request.getParameter("from2")))
//                    .to(LocalDate.parse(request.getParameter("to2")))
//                    .position(Position.getByName(request.getParameter("position2")))
//                    .build());
//        }
//        cv.setJobPlaces(jobPlaces);
//        request.setAttribute("cv_edit", cv);
        LOGGER.severe("Method Post AddController");
        LOGGER.severe("Input data" + cv);
        LOGGER.severe("Method Post AddController");
        request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request, response);
    }

}
