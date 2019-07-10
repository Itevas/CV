package com.lelek.cv.controller;

import com.lelek.cv.dao.DaoCv;
import com.lelek.cv.model.*;

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
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger("com.lelek.cv.webapp.AddServlet");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("id", -1);
        request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get cv in one row
        Cv cv = new Cv();
        List<JobPlace> jobPlaces = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        cv.setId(Integer.valueOf(request.getParameter("id")));
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

        String [] skillsArray = request.getParameterValues("skills[]");
        for (String skill : skillsArray) {
            skills.add(Skill.getByName(skill));
        }
        if (!request.getParameter("company0").isEmpty()) {
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .company(request.getParameter("company0"))
                    .city(request.getParameter("city0"))
                    .from(LocalDate.parse(request.getParameter("from0")))
                    .to(LocalDate.parse(request.getParameter("to0")))
                    .position(Position.getByName(request.getParameter("position0")))
                    .build());
        }
        if (!request.getParameter("company1").isEmpty()) {
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .company(request.getParameter("company1"))
                    .city(request.getParameter("city1"))
                    .from(LocalDate.parse(request.getParameter("from1")))
                    .to(LocalDate.parse(request.getParameter("to1")))
                    .position(Position.getByName(request.getParameter("position1")))
                    .build());
        }
        if (!request.getParameter("company2").isEmpty()) {
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .company(request.getParameter("company2"))
                    .city(request.getParameter("city2"))
                    .from(LocalDate.parse(request.getParameter("from2")))
                    .to(LocalDate.parse(request.getParameter("to2")))
                    .position(Position.getByName(request.getParameter("position2")))
                    .build());
        }
        cv.setJobPlaces(jobPlaces);
        cv.setSkills(skills);
        for (Skill skill : cv.getSkills()){
            LOGGER.severe(skill.toString());
        }
        if (cv.getId() == -1) {
            try {
                LOGGER.severe("Add cv");
                new DaoCv().add(cv);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                LOGGER.severe("Update cv");
                new DaoCv().update(cv);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        new ListServlet().doGet(request, response);
        // Response updated/added successfully
    }
}

