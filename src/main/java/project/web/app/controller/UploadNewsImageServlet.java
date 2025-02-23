package project.web.app.controller;


import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import project.web.app.bean.News;
import project.web.app.bean.User;
import project.web.app.dao.connections.ConnectionPool;
import project.web.app.dao.connections.ConnectionPoolException;
import project.web.app.dao.news.NewsDaoImpl;
import project.web.app.service.news.NewsService;
import project.web.app.service.news.NewsServiceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@WebServlet("/web_homew_news/upload")
@MultipartConfig
public class UploadNewsImageServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    private NewsService newsService;

    public UploadNewsImageServlet() {
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
        try {
            connectionPool = ConnectionPool.create(
                    "jdbc:mysql://localhost:3306/news_meincoon?allowPublicKeyRetrieval=true&useSSL=false",
                    "root",
                    "admin",
                    10
            );
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        this.newsService = new NewsServiceImpl(new NewsDaoImpl(connectionPool));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("image");
        String fileName = extractFileName(filePart); // Извлекаем имя файла

        System.out.println("filename " + fileName);

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, new File(uploadDir, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String text = request.getParameter("text");
        News news = new News();
        news.setTitle(title);
        news.setBrief(brief);
        news.setText(text);
        news.setImage("uploads/" + fileName);
        news.setAuthorId(user.getId());
        newsService.save(news);

        response.sendRedirect("Controller?command=go_to_index_page");
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
