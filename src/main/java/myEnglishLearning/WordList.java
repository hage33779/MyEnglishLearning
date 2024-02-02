package myEnglishLearning;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class WordList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WordDAO wordDAO;

    public void init() throws ServletException {
        wordDAO = new WordDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int idToDelete = Integer.parseInt(request.getParameter("id"));
            wordDAO.deleteWord(idToDelete);
        } else if ("register".equals(action)) {
            String name = request.getParameter("name");
            String explanation = request.getParameter("explanation");
            Word newWord = new Word();
            newWord.setName(name);
            newWord.setExplanation(explanation);
            wordDAO.addWord(newWord);
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	List<Word> words = wordDAO.getAllWords();  
        
    	request.setAttribute("words", words);
        RequestDispatcher dispatcher = request.getRequestDispatcher("wordList.jsp");
        dispatcher.forward(request, response);
    }
}

/*
public class WordList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WordDAO wordDAO;

    public void init() throws ServletException {
        wordDAO = new WordDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
    	String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int idToDelete = Integer.parseInt(request.getParameter("id"));
            boolean deleted = wordDAO.deleteWord(idToDelete);
            if (deleted) {
                response.sendRedirect("WordList");
            } else {
                response.getWriter().println("削除に失敗しました。");
            }
        } else if ("register".equals(action)) {
            String name = request.getParameter("name");
            String explanation = request.getParameter("explanation");
            Word newWord = new Word();
            newWord.setName(name);
            newWord.setExplanation(explanation);
            boolean registered = wordDAO.addWord(newWord);
            if (registered) {
                response.sendRedirect("WordList");
            } else {
                response.getWriter().println("登録に失敗しました。");
                doGet(request, response);
            }
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
    	List<Word> words = wordDAO.getAllWords();
        
        request.setAttribute("words", words);
        RequestDispatcher dispatcher = request.getRequestDispatcher("wordList.jsp");
        dispatcher.forward(request, response);
        
    }
}
*/
