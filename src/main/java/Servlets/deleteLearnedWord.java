package words;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Удаление слова из списка изученных слов
public class deleteLearnedWord extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        Session session = new words.Session(request);
        String deleteWord = request.getParameter("deleteWord");
        // Удаляем слово из списка
        session.deleteLearnedWord(deleteWord);
        session.deleteUserScore();
        response.getWriter().write("1");
    }
}
