package project.web.app.dao.news;

import project.web.app.bean.News;

import java.util.List;

public interface NewsDao {


    void save(News news);

    void delete(int newsId);

    List<News> getAll();
}
