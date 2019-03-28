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
import java.util.List;

@WebServlet(urlPatterns = "/allcv")
public class AllCvServlet extends HttpServlet {

    List<Cv> cvList;

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
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CvFacade facade = new CvFacade();
        int id = Integer.valueOf(request.getParameter("cv_id"));
        facade.deleteCvFromTable(id);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        CvFacade facade = new CvFacade();
        int id = Integer.valueOf(request.getParameter("cv_id"));
        facade.deleteCvFromTable(id);
    }
}
