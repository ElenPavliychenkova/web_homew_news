package project.web.app.controller.concrete.impl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.web.app.bean.AuthNews;
import project.web.app.bean.User;
import project.web.app.controller.concrete.Command;
import project.web.app.service.user.UserService;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class DoAuth implements Command {

    private final UserService userService;

    public DoAuth(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        RequestDispatcher dispatcher;

        if (login == null || password == null) {
            dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
            dispatcher.forward(request, response);
            return;
        }

        System.out.println("Performing user authentication. Login: " + login);

        try {
            User user = userService.authenticate(new AuthNews(login, password));

            UUID uuid = UUID.randomUUID();
            Instant expiredTime = Instant.now().plus(7, ChronoUnit.DAYS);

            Cookie userIdCookie = new Cookie("userid", uuid.toString());
            userIdCookie.setHttpOnly(true);
            userIdCookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(userIdCookie);

            Cookie expiredTimeCookie = new Cookie("expiredTime", String.valueOf(expiredTime.toEpochMilli()));
            expiredTimeCookie.setHttpOnly(true);
            expiredTimeCookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(expiredTimeCookie);


            request.getSession().invalidate();
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            response.sendRedirect("Controller?command=go_to_main_page");
            return;
        } catch (RuntimeException e) {
            System.out.println("fetched e " + e.getMessage());
        }

        request.setAttribute("authError", "Wrong login or password!");
        dispatcher = request.getRequestDispatcher("WEB-INF/jsp/auth.jsp");
        dispatcher.forward(request, response);
    }
}