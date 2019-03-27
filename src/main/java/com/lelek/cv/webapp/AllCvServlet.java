package com.lelek.cv.webapp;


import com.lelek.cv.model.CV;
import com.lelek.cv.service.CvFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@WebServlet(urlPatterns = "/allcv")
public class AllCvServlet extends HttpServlet {

    List<CV> cvList;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        {
            try {
                cvList = new CvFacade().readAllCvFromTable();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("cvList", cvList);
        request.getRequestDispatcher("/WEB-INF/allcv.jsp").forward(request, response);
    }
}
