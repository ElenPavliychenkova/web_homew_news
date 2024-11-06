package app.controller.concrete.impl;

import java.io.IOException;
import app.bean.AuthNews;
import app.bean.User;
import app.controller.concrete.Command;
import app.logic.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAuth implements Command {
	
	private final UserRepository userRepository;
	
	public DoAuth(UserRepository repo) {
		this.userRepository = repo;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String login = request.getParameter("login");
	    String password = request.getParameter("password");

	    if (login == null && password == null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        System.out.println("Performing user authentication. Login: " + login);
	        User user = userRepository.authenticate(new AuthNews(login, password));

	        if (user != null) {
	            request.getSession().invalidate();
	            HttpSession session = request.getSession(true);
	            session.setAttribute("user", user);
	            response.sendRedirect("Controller?command=go_to_main_page");
	        } else {
	            request.setAttribute("authError", "Wrong login or password!");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
	}
}
