package app.controller.concrete.impl;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.util.List;

import app.bean.News;
import app.controller.concrete.Command;
import app.service.news.NewsService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToIndexPage implements Command {

	private final NewsService newsService;

	public GoToIndexPage(NewsService newsService) {
		this.newsService = newsService;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("!!!! go to the index page");

		Object id = request.getParameter("id");

		System.out.println("fetched id = " + id);

		List<News> news = newsService.getAll();
		request.setAttribute("newsList", news);
		request.setAttribute("open_news_id", id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news.jsp");
		dispatcher.forward(request, response);
	}

}
