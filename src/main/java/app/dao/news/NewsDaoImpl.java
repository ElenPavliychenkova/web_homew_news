package app.dao.news;

import app.bean.News;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NewsDaoImpl implements NewsDao {

    private static final Map<Integer, News> nameToNewsMap = new HashMap<>();

    private static final AtomicInteger id = new AtomicInteger(0);

    static {
        nameToNewsMap.put(id.getAndIncrement(), new News(1, "Официальные \"лыжники\" кошачьего мира" , "Лапы мейн-кунов покрыты густым мехом между пальцами...", "Лапы мейн-кунов покрыты густым мехом между пальцами, который помогает им ходить по снегу, словно в природных \"снежных ботинках\". Это наследие их происхождения из сурового климата Новой Англии, где они использовались для охоты на грызунов даже в снежные зимы."));
        nameToNewsMap.put(id.getAndIncrement(),  new News(2, "\"Собаки\" среди кошек" , "Мейн-куны невероятно дружелюбные и преданные. Они часто...", "Мейн-куны невероятно дружелюбные и преданные. Они часто следуют за хозяевами из комнаты в комнату и могут даже приносить игрушки, как собаки. Благодаря их общительности, их называют кошками для тех, кто любит собак."));
        nameToNewsMap.put(id.getAndIncrement(),  new News(3, "Самый длинный кот в мире — мейн-кун", "Мейн-кун по имени Баривель из Италии был признан самым...", "Мейн-кун по имени Баривель из Италии был признан самым длинным домашним котом в мире: его длина от носа до кончика хвоста составляет 120 см! Их хвосты сами по себе могут достигать длины до 40 см, что помогает этим красавцам балансировать при прыжках и лазании."));
    }

    @Override
    public void save(News news) {

        nameToNewsMap.put(id.getAndIncrement(), news);
    }

    @Override
    public void delete(Integer id) {

        nameToNewsMap.remove(id);
    }

    @Override
    public List<News> getAll() {

        return nameToNewsMap.values().stream().toList();
    }


}
