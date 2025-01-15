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
            border-radius: 10px;
        }

        .auth-button {
            background-color: #ffffff;
            color: #00796b;
            border-radius: 15px;
            text-decoration: none;
            padding: 8px 15px;
            font-weight: bold;
            transition: background-color 0.3s, color 0.3s;
        }

        .auth-button:hover {
            background-color: #00796b;
            color: #ffffff;
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
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }

        .news-card h5 {
            color: #00796b;
        }

        .news-card p {
            color: #333333;
        }

        .news-card .btn-group {
            display: flex;
            gap: 10px;
            margin-top: 10px;
        }

        .btn-primary {
            background-color: #00796b;
            border: none;
            border-radius: 8px;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #005f54;
        }

        .btn-danger {
            background-color: #DDA0DD; /* Нежно-фиолетовый цвет */
            border: none;
            border-radius: 8px;
            transition: background-color 0.3s, transform 0.2s;
            font-weight: bold;
        }

        .btn-danger:hover {
            background-color: #BA55D3;
            transform: scale(1.05);
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
    <c:choose>
        <c:when test="${sessionScope.user == null}">
            <a href="Controller?command=do_auth" class="auth-button">Войти / Регистрация</a>
        </c:when>
        <c:otherwise>
            <span>Привет, ${sessionScope.user.name}!</span>
            <a href="Controller?command=logout" class="auth-button">Выйти</a>
        </c:otherwise>
    </c:choose>
</div>

<div class="container">
    <c:choose>
        <c:when test="${not empty newsList}">
            <c:forEach var="news" items="${newsList}">
                <div class="news-card">
                    <h5>${news.title}</h5>
                    <c:if test="${open_news_id == news.id}">
                        <p>${news.text}</p>
                    </c:if>
                    <c:if test="${open_news_id != news.id}">
                        <p>${news.brief}</p>
                    </c:if>
                    <div class="btn-group">
                        <form action="Controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="id" value="${news.id}"/>
                            <input type="hidden" name="command" value="go_to_index_page"/>
                            <button class="btn btn-primary" type="submit">Открыть</button>
                        </form>

                        <c:if test="${role == 'ADMIN' || role == 'JOURNALIST' }">
                            <form action="Controller" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="${news.id}"/>
                                <input type="hidden" name="command" value="delete_news"/>
                                <button class="btn btn-danger" type="submit">Удалить</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p>Нет доступных новостей.</p>
        </c:otherwise>
    </c:choose>
    <c:if test="${role == 'ADMIN' || role == 'JOURNALIST' }">
        <div class="text-center mt-5">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="go_to_add_news_page"/>
                <button class="btn btn-success btn-lg" type="submit">Добавить новость</button>
            </form>
        </div>
    </c:if>
</div>

<div class="footer">
    <p>&copy; 2024 Maine Coon Times. Все права защищены.</p>
</div>

</body>
</html>
