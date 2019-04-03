package com.lelek.cv.webapp;

import com.lelek.cv.dao.DaoCv;
import com.lelek.cv.model.Cv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger("com.lelek.cv.webapp.EditServlet");


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
