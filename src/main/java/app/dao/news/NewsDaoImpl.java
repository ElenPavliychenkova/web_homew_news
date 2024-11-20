package app.dao.news;

import app.bean.News;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDaoImpl implements NewsDao {

    private static final Map<String, News> nameToNewsMap = new HashMap<>();

    static {
        nameToNewsMap.put("Почему мейн-куны такие умные? Исследования ученых", new News("Новые исследования показывают, что высокий интеллект мейн-кунов" , "связан с их генетикой и природной любознательностью..."));
        nameToNewsMap.put("Идеальный рацион для мейн-куна: чем кормить вашего гиганта?", new News("Эксперты рассказали, как сбалансировать питание для крупных кошек" , "чтобы они оставались здоровыми и активными...."));
        nameToNewsMap.put("Рекорд среди мейн-кунов: кот по кличке Тор достигает длины 123 см", new News("В Нью-Йорке был представлен самый длинный мейн-кун в мире.", "Тор, гигантский представитель породы, стал настоящей сенсацией среди любителей кошек...."));
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
