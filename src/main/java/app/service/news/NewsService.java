package app.service.news;

import app.bean.News;

import java.util.List;

public interface NewsService {
    List<News> getAll();

    void save(News news);

    void delete(String title);
}
