package myEnglishLearning;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer totalPoints = (Integer) session.getAttribute("totalPoints");
        if (totalPoints == null) {
            totalPoints = 0;
            session.setAttribute("totalPoints", totalPoints);
        }
        
        request.setAttribute("totalPoints", totalPoints);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer totalPoints = (Integer) session.getAttribute("totalPoints");
        if (totalPoints == null) {
            totalPoints = 0;
        }
        
        String action = request.getParameter("action");
        processAction(action, totalPoints, session);
        
        doGet(request, response);
    }

    private void processAction(String action, Integer totalPoints, HttpSession session) {
        int cost = 0;
        switch (action) {
            case "reduce120":
                cost = 120;
                break;
            case "reduce1200":
                cost = 1200;
                break;
            case "reduce12000":
                cost = 12000;
                break;
            case "ship":
                if (totalPoints >= 25200) {
                    session.setAttribute("message", "出荷しました。新たな卵を育てましょう");
                    session.setAttribute("totalPoints", 0);
                    return;
                }
                break;
        }
        if (totalPoints >= cost) {
            session.setAttribute("totalPoints", totalPoints - cost);
        } else {
            session.setAttribute("error", "ポイントが不足しています。");
        }
    }
}


/*
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        HttpSession session = request.getSession();
        Integer totalPoints = (Integer) session.getAttribute("points");

        if (totalPoints == null) {
            totalPoints = 0;
            session.setAttribute("points", totalPoints);
        }
        
        request.setAttribute("totalPoints", totalPoints);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }
}
*/