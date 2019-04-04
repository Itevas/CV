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
    private int id = -1;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("id", id);
        LOGGER.severe("ID Get = " + id);
        Cv cv = null;
        try {
            cv = new DaoCv().get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.severe("CV Get = " + cv);
        request.setAttribute("cv", cv);
        request.getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        id = Integer.valueOf(request.getParameter("id"));
        LOGGER.severe("ID Post = " + id);
        request.getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
    }
}