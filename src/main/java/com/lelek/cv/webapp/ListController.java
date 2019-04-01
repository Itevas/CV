package com.lelek.cv.webapp;


import com.lelek.cv.model.Cv;
import com.lelek.cv.service.CvFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/")
public class ListController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.webapp.ListController");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cv> cvList = null;
        try {
            cvList = new CvFacade().get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("cvList", cvList);
        request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CvFacade facade = new CvFacade();
        Scanner s = new Scanner(request.getInputStream()).useDelimiter("=");
        String id = s.next().equals("id") ? s.next() : "";

        try {
            facade.delete(Integer.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("CV deleted successfully");
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Scanner s = new Scanner(request.getInputStream()).useDelimiter("=");
        int id = Integer.valueOf(s.next().equals("id") ? s.next() : "");
        LOGGER.severe("Input Stream data " + id);
        Cv cv_preview = null;
        try {
            cv_preview = new CvFacade().get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.severe("put method ListController " +cv_preview.toString());
        request.setAttribute("preview", cv_preview);

    }
}
