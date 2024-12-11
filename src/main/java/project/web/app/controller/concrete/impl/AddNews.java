package project.web.app.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.web.app.bean.News;
import project.web.app.bean.User;
import project.web.app.controller.concrete.Command;
import project.web.app.service.news.NewsService;

import java.io.IOException;

public class AddNews implements Command {

    private final NewsService newsService;

    public AddNews(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String text = request.getParameter("text");
        String imagePath = request.getParameter("imagePath");

        User user = (User) request.getSession().getAttribute("user");

        News news = new News();
        news.setTitle(title);
        news.setBrief(brief);
        news.setText(text);
        news.setImage(imagePath);
        news.setAuthorId(user.getId());

        System.out.println("NEWS " + news.toString());

        newsService.save(news);

        response.sendRedirect("Controller?command=go_to_index_page");
    }
}
