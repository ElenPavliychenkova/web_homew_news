<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать в личный кабинет</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #40E0D0;
        }

        .container {
            max-width: 600px;
            padding: 15px;
            margin: auto;
            background-color: #00796b;
            border-radius: 7px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            color: #ffffff;
        }

        .welcome-heading {
            color: #ffffff;
            margin-bottom: 20px;
        }

        .footer {
            text-align: center;
            padding: 20px 0;
            margin-top: 20px;
            border-top: 1px solid #e5e5e5;
            color: #f5f5f5;
        }

        .btn-custom {
            background-color: #40E0D0; 
            color: #ffffff;
            font-size: 1.2em;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin: 10px 5px;
        }

        .btn-custom:hover {
            background-color: #20b2aa;
        }

        .btn-container {
            display: flex;
            justify-content: center;
            gap: 20px; 
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container text-center">
    <h1 class="welcome-heading">Добро пожаловать в личный кабинет</h1>
    <p class="lead">Поздравляем с успешным входом на платформу. Вы готовы узнать больше о котиках^^?</p>

    <p class="lead">
        <c:out value="${requestScope.invitationMessage}"/></p>
    <c:out value="${sessionScope.user.name}"/>

    <div class="btn-container">
        <a href="Controller?command=go_to_index_page" class="btn btn-custom">На главную</a>
        <form action="Controller" method="post" style="display: inline;">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit" class="btn btn-custom">Выйти</button>
        </form>
    </div>
  
    <div class="footer">
        <p>&copy; 2024 "Maine Coon Times". Все права защищены.</p>
    </div>
</div>

</body>
</html>
