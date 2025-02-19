<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить новость</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="text-center mb-4">
        <h1>Добавить новость</h1>
    </div>

  <form action="upload" method="post" class="p-4 border rounded bg-light" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add_news">
        <div class="mb-3">
            <label for="title" class="form-label">Заголовок</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="Введите заголовок" required>
        </div>

        <div class="mb-3">
            <label for="brief" class="form-label">Краткое описание</label>
            <textarea class="form-control" id="brief" name="brief" rows="3" placeholder="Введите краткое описание"
                      required></textarea>
        </div>

        <div class="mb-3">
            <label for="text" class="form-label">Полное описание</label>
            <textarea class="form-control" id="text" name="text" rows="5" placeholder="Введите полное описание"
                      required></textarea>
        </div>

        <div class="mb-3">
            <label for="image" class="form-label">Изображение</label>
            <input type="file" class="form-control" id="image" name="image" accept="image/*">
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-success">Сохранить</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>