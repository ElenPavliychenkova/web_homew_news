package app.controller.concrete.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import app.bean.User;
import app.controller.concrete.Command;
import app.logic.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private UserRepository userRepository;
	
	public DoRegistration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("username " + request.getParameter("username"));
		System.out.println("password " + request.getParameter("password"));
		System.out.println("email " + request.getParameter("email"));
		
		User user = new User(request.getParameter("email"), request.getParameter("password"));
		userRepository.save(user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
		dispatcher.forward(request, response);
		
	}

}
