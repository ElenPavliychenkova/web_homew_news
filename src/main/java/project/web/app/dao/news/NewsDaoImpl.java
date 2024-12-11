package project.web.app.dao.news;

import project.web.app.bean.News;
import project.web.app.connections.ConnectionPool;
import project.web.app.connections.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {

//    private static final Map<Integer, News> nameToNewsMap = new HashMap<>();
//
//    private static final AtomicInteger id = new AtomicInteger(0);

//    static {
//        nameToNewsMap.put(id.get(), new News(id.getAndIncrement(), "Официальные \"лыжники\" кошачьего мира", "Лапы мейн-кунов покрыты густым мехом между пальцами...", "Лапы мейн-кунов покрыты густым мехом между пальцами, который помогает им ходить по снегу, словно в природных \"снежных ботинках\". Это наследие их происхождения из сурового климата Новой Англии, где они использовались для охоты на грызунов даже в снежные зимы.", "Милый котик мейн-кун в вязаном стиле Generative AI AIG16 _ Премиум Фото.jpg"));
//        nameToNewsMap.put(id.get(), new News(id.getAndIncrement(), "\"Собаки\" среди кошек", "Мейн-куны невероятно дружелюбные и преданные. Они часто...", "Мейн-куны невероятно дружелюбные и преданные. Они часто следуют за хозяевами из комнаты в комнату и могут даже приносить игрушки, как собаки. Благодаря их общительности, их называют кошками для тех, кто любит собак.", "4fec197c-8dbc-4bfe-8a3b-6f1138f6a149.jpg"));
//        nameToNewsMap.put(id.get(), new News(id.getAndIncrement(), "Самый длинный кот в мире — мейн-кун", "Мейн-кун по имени Баривель из Италии был признан самым...", "Мейн-кун по имени Баривель из Италии был признан самым длинным домашним котом в мире: его длина от носа до кончика хвоста составляет 120 см! Их хвосты сами по себе могут достигать длины до 40 см, что помогает этим красавцам балансировать при прыжках и лазании.", "Как великолепные огромные коты мейн-куны завоевали всепланетную любовь.jpg"));
//    }

    private final ConnectionPool connectionPool;

    public NewsDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void save(News news) {


        String INSERT_NEWS_SQL = """
                insert into news (title, brief, text, author_id) values
                (?,?,?,?)
                """;


        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS_SQL)) {

            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getBrief());
            preparedStatement.setString(3, news.getText());
            preparedStatement.setLong(4, news.getAuthorId());

            preparedStatement.executeUpdate();
            System.out.println("News " + news.getTitle() + " saved to database");

        } catch (SQLException | ConnectionPoolException e) {
            printSQLException((SQLException) e);
        }
    }

    @Override
    public void delete(int newsId) {

        System.out.println("Deleting news with ID: " + newsId);

        String DELETE_NEWS_SQL = """
                DELETE FROM news WHERE id = ?
                """;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NEWS_SQL)) {

            preparedStatement.setInt(1, newsId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("News with ID " + newsId + " was successfully deleted.");
            } else {
                System.out.println("No news found with ID " + newsId + ".");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ConnectionPoolException e) {
            System.err.println("Failed to get connection from pool: " + e.getMessage());
        }
    }

    @Override
    public List<News> getAll() {

        String GET_ALL_NEWS = """
                select id, title, brief, text, author_id
                from news
                """;

        List<News> newsList = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NEWS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getInt("id"));
                news.setTitle(resultSet.getString("title"));
                news.setBrief(resultSet.getString("brief"));
                news.setText(resultSet.getString("text"));
                news.setAuthorId(resultSet.getLong("author_id"));
                newsList.add(news);
            }
        } catch (SQLException | ConnectionPoolException e) {
            printSQLException((SQLException) e);
        }
        return newsList;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
