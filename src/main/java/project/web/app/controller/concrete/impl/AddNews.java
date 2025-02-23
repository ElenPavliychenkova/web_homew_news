package project.web.app.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import project.web.app.bean.News;
import project.web.app.bean.User;
import project.web.app.controller.concrete.Command;
import project.web.app.service.news.NewsService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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

        System.out.println("THIS IS ..... TTTTIITLE " + title);

        Part filePart = request.getPart("image");
        String imagePath = saveUploadedFile(filePart);

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

    private String saveUploadedFile(Part filePart) throws IOException {

        String uploads = "src/webapp/images/";
        File uploadDir = new File(uploads);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File file = new File(uploadDir, fileName);
            filePart.write(file.getAbsolutePath());
            return "uploads/" + fileName;
        }
		return uploads;
    }
}
