package app.dao.news;

import app.bean.News;

import java.util.List;

public interface NewsDao {


    void save(News news);

    void delete(Integer id);

    List<News> getAll();
}
