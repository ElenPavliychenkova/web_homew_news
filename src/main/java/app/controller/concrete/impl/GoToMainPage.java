package app.controller.concrete.impl;

import java.io.IOException;

import app.bean.User;
import app.controller.concrete.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToMainPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("!11111111111111111111111111");

		request.setAttribute("invitationMessage", "Добро пожаловать! Давно не виделись)");

		User user = (User) request.getSession().getAttribute("user");
		System.out.println("Go to main page as " + user.getRole());

		RequestDispatcher dispatcher;
		if (user.getRole() == User.Role.ADMIN || user.getRole() == User.Role.JOURNALIST) {
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main_news_reader.jsp");
		}
		dispatcher.forward(request, response);

	}

}