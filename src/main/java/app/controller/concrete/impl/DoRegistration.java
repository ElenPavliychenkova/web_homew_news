package app.controller.concrete.impl;

import app.bean.User;
import app.controller.concrete.Command;
import app.service.user.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoRegistration implements Command {

    private final UserService userService;

    public DoRegistration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("username " + request.getParameter("username"));
        System.out.println("password " + request.getParameter("password"));
        System.out.println("email " + request.getParameter("email"));

        User user = new User(request.getParameter("email"), request.getParameter("password"));
        userService.save(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
        dispatcher.forward(request, response);

    }

}
