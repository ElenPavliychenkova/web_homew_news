<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.*" %>
<%@ page import="jakarta.servlet.http.*" %>
<%@ page import="java.io.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maine Coon Times</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #40E0D0;
            font-family: 'Arial', sans-serif;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 30px;
            background-color: #00796b;
            color: #ffffff;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
        }

        .auth-button {
            background-color: #ffffff;
            color: #00796b;
            border-radius: 15px;
            text-decoration: none;
            padding: 8px 15px;
        }

        .container {
            max-width: 800px;
            margin-top: 20px;
        }

        .news-card {
            background-color: #ffffff;
            border-radius: 15px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        .news-card h5 {
            color: #00796b;
        }

        .news-card p {
            color: #333333;
        }

        .footer {
            margin-top: 40px;
            color: #f5f5f5;
            text-align: center;
            padding: 10px 0;
            background-color: #00796b;
            border-radius: 10px;
        }
    </style>
</head>
<body>

<div class="header">
    <h1>Maine Coon Times</h1>
    <c:if test="${sessionScope.get('user') == null}">
        <a href="Controller?command=do_auth" class="auth-button">Войти / Регистрация</a>
    </c:if>
    <c:if test="${sessionScope.get('user') != null}">
        <a href="Controller?command=logout" class="auth-button">Выйти</a>
    </c:if>

</div>

<div class="container">
    <c:choose>
        <c:when test="${not empty newsList}">
            <c:forEach var="news" items="${newsList}">
                <form action="Controller" method="post" accept-charset="UTF-8">
                    <input type="hidden" name="command" value="go_to_index_page" />
                    <input type="hidden" name="id" value="${news.id}" />
                    <div class="news-card">
                        <h5>${news.title}</h5>

                        <c:if test="${open_news_id == news.id}">
                            <p>${news.text}</p>
                        </c:if>
                         <c:if test="${open_news_id != news.id}">
                            <p>${news.brief}</p>
                        </c:if>

                        <button class="btn btn-primary" type="submit">Открыть</button>
                    </div>
                </form>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>Нет доступных новостей.</p>
        </c:otherwise>
    </c:choose>
</div>

<div class="footer">
    <p>&copy; 2024 Maine Coon Times. Все права защищены.</p>
</div>

</body>
</html>
