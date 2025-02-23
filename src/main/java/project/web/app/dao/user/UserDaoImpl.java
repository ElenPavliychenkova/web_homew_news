package project.web.app.dao.user;

import project.web.app.bean.User;
import project.web.app.dao.connections.ConnectionPool;
import project.web.app.dao.connections.ConnectionPoolException;

import java.sql.*;
import java.time.LocalDate;

public class UserDaoImpl implements UserDao {

    private final ConnectionPool connectionPool;

    public UserDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public void save(User user) {

        System.out.println("user = " + user.toString());
        String INSERT_USERS_SQL = """
                insert into users (email, password, name, surname, role_id, registration_date) values
                (?,?,?,?,?,?)
                """;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setInt(5, getRoleIdByRole(user.getRole()));
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));

            preparedStatement.executeUpdate();
            System.out.println("User " + user.getEmail() + " saved to database");

        } catch (SQLException e) {
            printSQLException(e);
        } catch (ConnectionPoolException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Integer getRoleIdByRole(User.Role role) {

        System.out.println("Fetch role id by role: " + role.toString());

        String get = """
                select
                       r.id
                from roles r
                where r.name = ?
                """;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(get)) {

            preparedStatement.setString(1, role.name());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            printSQLException(e);
        } catch (ConnectionPoolException e) {
            System.out.println("ConnectionPool error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUser(String email) {

        System.out.println("Fetching user by email: " + email);

        String GET_USER_BY_EMAIL = """
                select
                       u.id                as id,
                       u.email             as email,
                       u.password          as password,
                       u.name              as name,
                       u.surname           as surname,
                       r.name              as role
                from users u
                         left join roles r on r.id = u.role_id
                where u.email = ?
                """;

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet  = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setRole(User.Role.valueOf(resultSet.getString("role")));
                return user;

            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ConnectionPoolException e) {

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



