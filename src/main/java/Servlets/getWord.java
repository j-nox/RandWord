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

// Получение и вывод случайного английского слова
public class getWord extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Session session = new words.Session(request);
        User user = session.getCurrentUser();
        LearnedWords learnedWords = session.getLearnedWords();
        // Словарь, содержащий английское слово и 5 вариантов перевода к нему, для отправки данных на fronend
        HashMap<String, ArrayList<String>> randWord = words.Dictionary.getInstance().getWord();

        request.setAttribute("userId", user.getId());
        request.setAttribute("userScore", user.getScore());
        request.setAttribute("learnedWords", learnedWords.getLearnedWords());

        // Ищем слово в списке изученных
        boolean learned = true;
        while (learned) {
            for (String key : randWord.keySet()) {
                if (learnedWords.findLearnedWord(key)) {
                    randWord = session.getCurrentDictionary().getWord();
                } else {
                    // Если слова нет в списке изученных
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
