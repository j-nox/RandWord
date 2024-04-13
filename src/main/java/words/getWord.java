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
import java.net.URLDecoder;
import java.net.URLEncoder;

public class getWord extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Session session = new words.Session(request);
        PrintWriter printOut = response.getWriter();
        HashMap<String, ArrayList<String>> randWord = session.getCurrentDictionary().getWord();

        for (String key : randWord.keySet()) {
            request.setAttribute("word", key);
        }

        for (ArrayList<String> valuesList : randWord.values()) {
            request.setAttribute("translations", valuesList);
        }

        request.getRequestDispatcher("/WEB-INF/getWord.jsp").forward(request, response);

    }
}
