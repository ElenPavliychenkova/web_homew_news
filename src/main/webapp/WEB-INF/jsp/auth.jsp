<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String authError = request.getParameter("authError"); %>

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

        .container {
            max-width: 400px;
            background-color: #00796b;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            color: #ffffff;
        }

        .error-message {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div class="container">
    <c:if test="${not empty authError}">
        <div class="error-message">${authError}</div>
    </c:if>
    <h2 class="text-center">Вход на платформу</h2>
    <form action="Controller" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="do_auth"/>
        <div class="form-group">
            <label for="login">Логин</label>
            <input type="email" id="login" name="login" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Запомнить меня
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
    <p>
        Нет аккаунта? <a href="Controller?command=go_to_registration_page" class="text-white">Зарегистрируйтесь</a>
    </p>
    <h2></h2>
    <c:out value="${requestScope.mainNews.title}"/>
    <br/>
    <c:out value="${requestScope.mainNews.brief}"/>
</div>

</body>
</html>
