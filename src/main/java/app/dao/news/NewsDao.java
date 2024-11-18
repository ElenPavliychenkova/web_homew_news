package app.dao.news;

import app.bean.News;

import java.util.List;

public interface NewsDao {


    void save(News news);

    void delete(String title);

    List<News> getAll();
}
