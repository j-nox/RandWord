package words;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class checkWord extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        // Читаем переданные параметры
        String currentWord = request.getParameter("currentWord");
        String translate = request.getParameter("translate");
        Session session = new words.Session(request);
        Dictionary dictionary = words.Dictionary.getInstance();
        // Проверяем перевод слова
        boolean checkWord = dictionary.checkWord(currentWord, translate);
        if (checkWord) {
            // Отправляем ответ
            response.getWriter().write("1");
            session.addUserScore();
            session.addLearnedWords(currentWord);
        }
    }
}
