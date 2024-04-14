package words;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Session extends HttpServlet {
    HttpSession session;
    public Session (HttpServletRequest request)
            throws ServletException, IOException {
        session = request.getSession();
        session.setMaxInactiveInterval(-1);
        if (session.isNew() == true) {
            User user = new words.User();
            session.setAttribute("user", user);
            Dictionary dictionary = new words.Dictionary();
            session.setAttribute("dictionary", dictionary);
            LearnedWords learnedWords = new LearnedWords();
            session.setAttribute("learnedWords", learnedWords);
        }
    }

    public HttpSession getCurrentSession() {
//        HttpSession session = request.getSession();
        return session;
    }
    public User getCurrentUser() {
        User currentUser = (User) session.getAttribute("user");
        return currentUser;
    }
    public Dictionary getCurrentDictionary() {
        Dictionary currentDictionary = (Dictionary) session.getAttribute("dictionary");
        return currentDictionary;
    }
    public void addUserScore() {
        User currentUser = (User) session.getAttribute("user");
        currentUser.addScore();
        session.removeAttribute("user");
        session.setAttribute("user", currentUser);
    }

    public void addLearnedWords(String word) {
        LearnedWords learnedWords = (LearnedWords) session.getAttribute("learnedWords");
        learnedWords.addLearnedWord(word);
        session.removeAttribute("learnedWords");
        session.setAttribute("learnedWords", learnedWords);
    }

    public boolean checkLearnedWords(String word) {
        LearnedWords learnedWords = (LearnedWords) session.getAttribute("learnedWords");
        return learnedWords.checkLearnedWord(word);
    }

    public List<String> getLearnedWords() {
        LearnedWords learnedWords = (LearnedWords) session.getAttribute("learnedWords");
        return learnedWords.getLearnedWords();
    }

}
