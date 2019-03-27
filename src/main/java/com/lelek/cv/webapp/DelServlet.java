package com.lelek.cv.webapp;

import com.lelek.cv.service.CvFacade;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        int id  =Integer.valueOf(request.getParameter("name"));
        new CvFacade().deleteCvFromTable(id);
        System.out.println("9999999999999999999999999999999999999999999999999999999999999999");
    }
}
