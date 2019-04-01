package com.lelek.cv.webapp;

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

@WebServlet(urlPatterns = "/")
public class ListController extends HttpServlet {
    private List<Cv> cvList = null;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        DaoCv cvById = new DaoCv();
        Scanner s = new Scanner(request.getInputStream()).useDelimiter("=");
        String id = s.next().equals("id") ? s.next() : "";

        try {
            cvById.delete(Integer.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("CV deleted successfully");
    }
}
