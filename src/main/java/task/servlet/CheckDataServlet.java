package task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/jsptemplate")
public class CheckDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/save-request.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String courses = req.getParameter("value");
        if (name == null || name.isEmpty() ||
            surname == null || surname.isEmpty() ||
            courses == null || courses.isEmpty()) {
            resp.sendRedirect("/test");
        } else {
            req.setAttribute("name", name);
            req.setAttribute("surname", surname);
            req.setAttribute("value", courses);
            req.getRequestDispatcher("/WEB-INF/result-application.jsp").forward(req, resp);
        }
    }
}


