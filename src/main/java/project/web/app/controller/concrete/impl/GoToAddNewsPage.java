package project.web.app.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.web.app.controller.concrete.Command;

import java.io.IOException;

public class GoToAddNewsPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/add_news.jsp").forward(request, response);
    }
}
