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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <title>SO question 2370960</title>
        <style>
        body {margin-top: 100px;}
        </style>
        <%
            String word = (String) request.getAttribute("word");
            int userId = (int) request.getAttribute("userId");
            int userScore = (int) request.getAttribute("userScore");
            ArrayList<String> translations = (ArrayList<String>) request.getAttribute("translations");
        %>
    </head>
    <body>
          <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container-fluid">
              <a class="navbar-brand" href="#">5000 слов</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/RandWord/getWord">
                        <button class="btn btn-info rounded-pill px-3" type="button">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                              <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2z"/>
                              <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466"/>
                            </svg>
                        </button>
                    </a>
                  </li>
                </ul>
                <div class="d-lg-flex col-lg-3 justify-content-lg-end">
                  <button class="btn btn-primary">
                    User #<% out.println(userId); %>
                  </button>
                  <button style="margin-left: 15px;" class="btn btn-primary">
                    Выучил слов: <% out.println(userScore); %>
                  </button>
                </div>
              </div>
            </div>
          </nav>
          <main class="container">
            <h1 class="text-center">Выучи 5000 английских слов</h1>
            <div class="bg-body-tertiary p-5 rounded">
              <h1><% out.println(word); %></h1>
              <p class="lead">Выбери правильный перевод</p>
              <%
              for (String translate : translations) {
                out.println("<a class='btn btn-lg btn-primary' href='/' role='button'>" + translate + "</a>");
              }
              %>
            </div>
          </main>
    </body>
</html>