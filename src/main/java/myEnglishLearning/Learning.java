package myEnglishLearning;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Learning extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WordDAO wordDAO;
    private static final int MAX_QUESTIONS = 10;
    private static final int MAX_ERRORS = 3;
    public static final int POINTS_PER_CORRECT_ANSWER = 10;
    
    public static int getMaxQuestions() {
        return MAX_QUESTIONS;
    }

    public void init() throws ServletException {
        wordDAO = new WordDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String newSessionFlag = request.getParameter("newSession");
        if ("true".equals(newSessionFlag)) {
            session.setAttribute("errors", 0);
            session.setAttribute("questionCount", 0);
            // 学習スタート時に獲得ポイントをリセット
            session.setAttribute("sessionEarnedPoints", 0);
        }

        Integer errors = (Integer) session.getAttribute("errors");
        Integer questionCount = (Integer) session.getAttribute("questionCount");

        if (questionCount >= MAX_QUESTIONS || errors >= MAX_ERRORS) {
            response.sendRedirect("Result");
            return;
        }

        List<Word> words = wordDAO.getAllWords();
        Random random = new Random();
        Word currentWord = words.get(random.nextInt(words.size()));

        session.setAttribute("currentWord", currentWord);
        session.setAttribute("questionCount", questionCount + 1);

        RequestDispatcher dispatcher = request.getRequestDispatcher("learning.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Word currentWord = (Word) session.getAttribute("currentWord");
        String userAnswer = request.getParameter("userAnswer");
        Integer errors = (Integer) session.getAttribute("errors");
        Integer totalPoints = (Integer) session.getAttribute("totalPoints");
        Integer sessionEarnedPoints = (Integer) session.getAttribute("sessionEarnedPoints");

        if (errors == null) errors = 0;
        if (totalPoints == null) totalPoints = 0;
        if (sessionEarnedPoints == null) sessionEarnedPoints = 0;

        if (currentWord.getName().equalsIgnoreCase(userAnswer)) {
            sessionEarnedPoints += POINTS_PER_CORRECT_ANSWER;
            totalPoints += POINTS_PER_CORRECT_ANSWER; // 獲得ポイントを累計ポイントに加算
        } else {
            errors++;
        }

        session.setAttribute("errors", errors);
        session.setAttribute("sessionEarnedPoints", sessionEarnedPoints);
        session.setAttribute("totalPoints", totalPoints); 

        doGet(request, response);
    }
}
