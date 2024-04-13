package words;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
        }
    }

    public HttpSession getCurrentSession() {
//        HttpSession session = request.getSession();
        return session;
    }
    public User getCurrentUser() {
//        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        return currentUser;
    }
    public Dictionary getCurrentDictionary() {
//        HttpSession session = request.getSession();
        Dictionary currentDictionary = (Dictionary) session.getAttribute("dictionary");
        return currentDictionary;
    }
    public void updateCurrentUser() {
//        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        currentUser.addScore();
        session.removeAttribute("user");
        session.setAttribute("user", currentUser);
    }

}
