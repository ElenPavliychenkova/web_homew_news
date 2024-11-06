package app.controller.concrete.impl;
import java.util.List;
import java.io.IOException;
import java.util.List;

import app.bean.News;
import app.controller.concrete.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToIndexPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("go to the index page");

		List<News> newsList = List.of(
				new News("Java 21 Вышел в Продакшн", "Java 21 официально выпущен и уже доступен для использования! Новая версия включает долгожданные функции: сопоставление шаблонов (Pattern Matching) в выражениях switch, улучшенные Scoped Values для работы с потоками..."),
				new News("Появился Набор Инструментов Java для Искусственного Интеллекта", "С выходом новой библиотеки Deep Java Library (DJL), Java-разработчики теперь могут легко интегрировать возможности машинного обучения в свои приложения. DJL предоставляет простой и удобный интерфейс, поддерживающий модели PyTorch..."),
				new News("Java в Топе Популярных Языков для Бэкенда", "По данным недавнего опроса разработчиков от Stack Overflow, Java вновь вошла в топ-3 самых популярных языков для бэкенда. Разработчики ценят Java за её надёжность, масштабируемость и кросс-платформенность...")
				);
		request.setAttribute("newsList", newsList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news.jsp");
		dispatcher.forward(request, response);
	}

}
