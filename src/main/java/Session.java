package words;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import words.*;

public class Session extends HttpServlet {
    private HttpSession session;
    private User user;
    private Dictionary dictionary = words.Dictionary.getInstance();
    private LearnedWords learnedWords;
    public Session (HttpServletRequest request)
            throws ServletException, IOException {
        session = request.getSession();
        session.setMaxInactiveInterval(-1);
        if (session.isNew() == true) {
            user = new words.User();
            session.setAttribute("user", user);
            learnedWords = new words.LearnedWords();
            session.setAttribute("learnedWords", learnedWords);
        } else {
            user = (User) session.getAttribute("user");
            learnedWords = (LearnedWords) session.getAttribute("learnedWords");
        }
    }

    public HttpSession getCurrentSession() { return session; }
    public Dictionary getCurrentDictionary() { return dictionary; }
    public User getCurrentUser() { return user; }
    public void addUserScore() {
        user.addScore();
        updateDataSession("user", user);
    }
    public void deleteUserScore() {
        user.deleteScore();
        updateDataSession("user", user);
    }
    public void addLearnedWords(String word) {
        learnedWords.addLearnedWord(word);
        updateDataSession("learnedWords", learnedWords);
    }

    public boolean checkLearnedWords(String word) {
        return learnedWords.checkLearnedWord(word);
    }

    public List<String> getLearnedWords() { return learnedWords.getLearnedWords(); }

    public void deleteLearnedWord(String word) {
        learnedWords.removeWord(word);
        updateDataSession("learnedWords", learnedWords);
    }

    private void updateDataSession(String name, Object value) {
        session.removeAttribute(name);
        session.setAttribute(name, value);
    }
}
