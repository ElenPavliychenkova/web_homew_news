package project.web.app.controller.concrete.impl;

import project.web.app.bean.User;
import project.web.app.controller.concrete.Command;
import project.web.app.service.user.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

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

        User user = new User(
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("surname"),
                User.Role.valueOf(request.getParameter("role")),
                LocalDateTime.now()
        );

        userService.save(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
        dispatcher.forward(request, response);

    }

}
