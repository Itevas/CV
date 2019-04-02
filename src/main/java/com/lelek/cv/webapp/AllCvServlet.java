package com.lelek.cv.webapp;


import com.lelek.cv.model.Cv;
import com.lelek.cv.service.CvFacade;
import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@WebServlet(urlPatterns = "/allcv")
public class AllCvServlet extends HttpServlet {

    private List<Cv> cvList;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            cvList = new CvFacade().readAllCvFromTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("cvList", cvList);
        request.getRequestDispatcher("/WEB-INF/allcv.jsp").forward(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CvFacade facade = new CvFacade();
        Scanner s = new Scanner(request.getInputStream()).useDelimiter("=");
        String id = s.next().equals("id") ? s.next() : "";

        try {
            facade.deleteCvFromTable(Integer.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("CV deleted successfully");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
}
