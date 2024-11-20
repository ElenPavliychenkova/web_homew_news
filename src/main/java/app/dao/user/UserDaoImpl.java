package app.dao.user;

import app.bean.User;

import java.sql.*;
import java.time.LocalDate;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {

        String INSERT_USERS_SQL = """
                insert into users (email, password, name, surname, role, registration_date) values
                (?,?,?,?,?,?)
                """;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_meincoon?useSSL=false", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User getUser(String email) {

        System.out.println("Get user by email " + email);

        String GET_USER_BY_EMAIL = """
                select *
                from users
                where email = ?
                """;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_meincoon?useSSL=false", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        User.Role.valueOf(resultSet.getString("role")),
                        null,
                        resultSet.getTimestamp("registration_date").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
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