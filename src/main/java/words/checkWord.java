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

public class checkWord extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String currentWord = request.getParameter("currentWord");
        String translate = request.getParameter("translate");

        Dictionary dictionary = new Dictionary();
        boolean checkWord = dictionary.checkWord(currentWord, translate);
        if (checkWord) {
            response.getWriter().write("Верно");
        } else {
            response.getWriter().write("Неверно");
        }
    }
}
