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

// Получаем тематическую картинку к слову
public class getImageApi extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String currentWord = request.getParameter("currentWord");
        // Заглушка для картинки
        String stub = "https://c-promotion.ru/wp-content/uploads/2023/05/reklama-v-yandeks-kartinkah.jpg";
        words.Image image = new words.Image(currentWord);
        String imageURL = image.getImage();
        if(imageURL != null && !imageURL.isEmpty()) {
            // Если картинка найдена
            response.getWriter().write(imageURL);
        } else {
            // Если картинка не найдена
            response.getWriter().write(stub);
        }
    }
}
