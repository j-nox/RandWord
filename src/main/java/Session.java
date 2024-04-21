package words;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Создание и работа с сессией
public class Session extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(words.Dictionary.class);
    private HttpSession session;
    private User user;
    private Dictionary dictionary = words.Dictionary.getInstance();
    private LearnedWords learnedWords;
    public Session (HttpServletRequest request)
            throws ServletException, IOException {
        session = request.getSession();
        // Сбрасывать сессию при закрытии браузера
        session.setMaxInactiveInterval(-1);
        // Если сессия существует
        if (session.isNew() == true) {
            user = new words.User();
            session.setAttribute("user", user);
            learnedWords = new words.LearnedWords();
            session.setAttribute("learnedWords", learnedWords);
            // Пишем в лог, что сессия создана
            LOG.info("New session has been created");
        // Если сессия не существует
        } else {
            user = (User) session.getAttribute("user");
            learnedWords = (LearnedWords) session.getAttribute("learnedWords");
        }
    }

    public HttpSession getCurrentSession() { return session; }
    public Dictionary getCurrentDictionary() { return dictionary; }
    public User getCurrentUser() { return user; }
    public LearnedWords getLearnedWords() { return learnedWords; }
    public void updateDataSession(String name, Object value) {
        session.removeAttribute(name);
        session.setAttribute(name, value);
    }
}
