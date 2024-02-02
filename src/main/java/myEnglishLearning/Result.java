package myEnglishLearning;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Result extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer errors = (Integer) session.getAttribute("errors");
        Integer sessionEarnedPoints = (Integer) session.getAttribute("sessionEarnedPoints");
        if (errors == null) errors = 0;
        if (sessionEarnedPoints == null) sessionEarnedPoints = 0;
        int score = Learning.getMaxQuestions() - errors;
        request.setAttribute("score", score);
        request.setAttribute("points", sessionEarnedPoints);
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }
}
