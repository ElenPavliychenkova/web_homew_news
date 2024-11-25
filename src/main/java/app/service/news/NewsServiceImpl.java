package app.service.news;

import app.bean.News;
import app.dao.news.NewsDao;
import app.dao.news.NewsDaoImpl;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao = new NewsDaoImpl();

    @Override
    public List<News> getAll() {

        return newsDao.getAll();
    }

    @Override
    public void save(News news) {

        newsDao.save(news);
    }

    @Override
    public void delete(Integer id) {

        newsDao.delete(id);
    }
}
