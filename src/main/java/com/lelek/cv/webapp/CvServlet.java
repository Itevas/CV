package com.lelek.cv.webapp;

import com.lelek.cv.model.CV;
import com.lelek.cv.service.CvFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

@WebServlet(urlPatterns = "/cv")
public class CvServlet extends HttpServlet {

    private final String PATH = "C:\\Users\\vleletc\\IdeaProjects\\cv\\src\\main\\resources\\";
    private CV cv;

    private String getAge() {
        return String.valueOf(Period.between(cv.getPerson().getBirthday(), LocalDate.now()).getYears());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        cv = new CvFacade().getCvFromFile(PATH + "temp.yml");

        request.setAttribute("person", cv.getPerson());
        request.setAttribute("contact", cv.getContact());
        request.setAttribute("age", getAge() + " years old");

        if (cv.getJobPlaces().size() == 0) {
            request.setAttribute("jobPlaces", "No experience.");
        } else if (cv.getJobPlaces().size() == 1) {
            request.setAttribute("jobPlaces", "Job Place:");
            request.setAttribute("company", cv.getJobPlaces().get(1).getCompany());
            request.setAttribute("city", cv.getJobPlaces().get(1).getCity());
            request.setAttribute("fromTxt", "From:");
            request.setAttribute("from", cv.getJobPlaces().get(1).getFrom());
            request.setAttribute("toTxt", "To:");
            request.setAttribute("to", cv.getJobPlaces().get(1).getTo());
            request.setAttribute("position", cv.getJobPlaces().get(1).getPosition());
            request.setAttribute("line0", "<hr>");
        } else if (cv.getJobPlaces().size() > 1) {
            request.setAttribute("jobPlaces", "Job Places:");
            for (int i = 0; i < cv.getJobPlaces().size(); i++) {
                request.setAttribute("jobplaces" + i, "Job Place:");
                request.setAttribute("company" + i, cv.getJobPlaces().get(i).getCompany());
                request.setAttribute("city" + i, cv.getJobPlaces().get(i).getCity());
                request.setAttribute("fromTxt" + i, "From:");
                request.setAttribute("from" + i, cv.getJobPlaces().get(i).getFrom());
                request.setAttribute("toTxt" + i, "To:");
                request.setAttribute("to" + i, cv.getJobPlaces().get(i).getTo());
                request.setAttribute("position" + i, cv.getJobPlaces().get(i).getPosition());
                request.setAttribute("line" + i, "<hr>");
            }
        }
        request.getRequestDispatcher("/WEB-INF/preview.jsp").forward(request, response);
    }
}
