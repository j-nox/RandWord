package words;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class getWord extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session session = new words.Session(request);
        PrintWriter printOut = response.getWriter();
        HashMap<String, ArrayList<String>> randWord = session.getCurrentDictionary().getWord();

        for (String key : randWord.keySet()) {
            printOut.println(key);

            request.setAttribute("message", key);
            request.getRequestDispatcher("/WEB-INF/getWord.jsp").forward(request, response);

        }
        for (ArrayList<String> valuesList : randWord.values()) {
            for (String value : valuesList) {
                printOut.println(value);
            }
        }


    }
}
