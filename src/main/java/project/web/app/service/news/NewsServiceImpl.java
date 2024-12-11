package project.web.app.service.news;

import project.web.app.bean.News;
import project.web.app.dao.news.NewsDao;
import project.web.app.dao.news.NewsDaoImpl;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao;

    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List<News> getAll() {

        return newsDao.getAll();
    }

    @Override
    public void save(News news) {

        newsDao.save(news);
    }

    @Override
    public void delete(int newsId) {

        newsDao.delete(newsId);
    }
}
