package project.web.app.service.news;

import project.web.app.bean.News;

import java.util.List;


public interface NewsService {
    List<News> getAll();

    void save(News news);

    void delete(int newsId);

}
