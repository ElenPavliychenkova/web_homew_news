package app.dao.news;

import app.bean.News;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDaoImpl implements NewsDao {

    private static final Map<String, News> nameToNewsMap = new HashMap<>();

    static {
        nameToNewsMap.put("Java 21 Вышел в Продакшн", new News("Java 21 Вышел в Продакшн", "Java 21 официально выпущен и уже доступен для использования! Новая версия включает долгожданные функции: сопоставление шаблонов (Pattern Matching) в выражениях switch, улучшенные Scoped Values для работы с потоками..."));
        nameToNewsMap.put("Появился Набор Инструментов Java для Искусственного Интеллекта", new News("Появился Набор Инструментов Java для Искусственного Интеллекта", "С выходом новой библиотеки Deep Java Library (DJL), Java-разработчики теперь могут легко интегрировать возможности машинного обучения в свои приложения. DJL предоставляет простой и удобный интерфейс, поддерживающий модели PyTorch..."));
        nameToNewsMap.put("Java в Топе Популярных Языков для Бэкенда", new News("Java в Топе Популярных Языков для Бэкенда", "По данным недавнего опроса разработчиков от Stack Overflow, Java вновь вошла в топ-3 самых популярных языков для бэкенда. Разработчики ценят Java за её надёжность, масштабируемость и кросс-платформенность..."));
    }

    @Override
    public void save(News news) {

        nameToNewsMap.put(news.getTitle(), news);
    }

    @Override
    public void delete(String title) {

        nameToNewsMap.remove(title);
    }

    @Override
    public List<News> getAll() {

        return nameToNewsMap.values().stream().toList();
    }


}
