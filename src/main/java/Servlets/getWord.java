package words;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import words.*;

public class getWord extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Session session = new words.Session(request);
        PrintWriter printOut = response.getWriter();
        HashMap<String, ArrayList<String>> randWord = words.Dictionary.getInstance().getWord();

        User user = session.getCurrentUser();

        request.setAttribute("userId", user.getId());
        request.setAttribute("userScore", user.getScore());
        request.setAttribute("learnedWords", session.getLearnedWords());

        boolean learned = true;
        while (learned) {
            for (String key : randWord.keySet()) {
                if (session.checkLearnedWords(key)) {
                    randWord = session.getCurrentDictionary().getWord();
                } else {
                    request.setAttribute("word", key);
                    learned = false;
                }
            }
        }

        for (ArrayList<String> valuesList : randWord.values()) {
            request.setAttribute("translations", valuesList);
        }

        request.getRequestDispatcher("/WEB-INF/getWord.jsp").forward(request, response);

    }
}
