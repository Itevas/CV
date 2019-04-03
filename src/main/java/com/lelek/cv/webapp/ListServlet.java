package com.lelek.cv.webapp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.cv.dao.DaoCv;
import com.lelek.cv.dao.DaoList;
import com.lelek.cv.model.Cv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/")
public class ListServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger("com.lelek.cv.webapp.listservlet");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cv> cvList = null;
        try {
            cvList = new DaoList().get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("cvList", cvList);
        request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Scanner s = new Scanner(request.getInputStream()).useDelimiter("=");
        String id = s.next().equals("id") ? s.next() : "";

        try {
            new DaoCv().delete(Integer.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("CV deleted successfully");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.valueOf(request.getParameter("id"));
        Cv cv = null;
        try {
            cv = new DaoCv().get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String result = new ObjectMapper().writeValueAsString(cv);
        LOGGER.severe(result);
        response.getWriter().write(result);
    }

}
