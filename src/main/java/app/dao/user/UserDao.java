package app.dao.user;

import app.bean.User;

public interface UserDao {

    void save(User user);

    User getUser(String username);

}