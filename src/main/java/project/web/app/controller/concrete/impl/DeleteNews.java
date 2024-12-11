package project.web.app.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.web.app.controller.concrete.Command;
import project.web.app.service.news.NewsService;

import java.io.IOException;

public class DeleteNews implements Command {

    private final NewsService newsService;

    public DeleteNews(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newsIdStr = request.getParameter("id");
        System.out.println("DeleteNews Command invoked with ID: " + newsIdStr);

        if (newsIdStr != null) {
            System.out.println("start delete");
            int newsId = Integer.parseInt(newsIdStr);
            System.out.println("parsing " + newsId);
            newsService.delete(newsId);
        }

        response.sendRedirect("Controller?command=go_to_index_page");
    }
}
