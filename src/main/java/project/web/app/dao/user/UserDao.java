package project.web.app.dao.user;

import project.web.app.bean.User;

import java.sql.SQLException;

public interface UserDao {

    void save(User user);

    Integer getRoleIdByRole(User.Role role);

    User getUser(String username);

}