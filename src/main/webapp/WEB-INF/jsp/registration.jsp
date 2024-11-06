<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация - Изучаем Java</title>
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
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center">Регистрация нового пользователя</h2>
    <form action="Controller" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="do_registration"/>
        <div class="form-group">
            <label for="inputName">Имя пользователя</label>
            <input type="text" id="inputName" name="username" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="inputEmail">Email адрес</label>
            <input type="email" id="inputEmail" name="email" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="inputPassword">Пароль</label>
            <input type="password" id="inputPassword" name="password" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="inputConfirmPassword">Подтверждение пароля</label>
            <input type="password" id="inputConfirmPassword" name="confirm_password" class="form-control" required>
        </div>
        <button class="btn btn-primary" type="submit">Зарегистрироваться</button>
      
    </form>
</div>

</body>
</html>
    