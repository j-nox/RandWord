<%@ page import="java.util.ArrayList" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <meta charset="UTF-8">
        <title>SO question 2370960</title>
    </head>
    <body>
        <h2>Слово</h2>
         <p>
             <%
                String s = (String) request.getAttribute("word");
                out.println(s);
             %>
         </p>
         <h2>Перевод</h2>
          <p>
              <%
                ArrayList<String> translations = (ArrayList<String>) request.getAttribute("translations");
                out.println(translations.toString());
              %>
          </p>
    </body>
</html>