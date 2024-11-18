package app.dao.user;

import java.util.HashMap;
import java.util.Map;

import app.bean.User;

public class UserDaoImpl implements UserDao {

    private static final Map<String, User> nameToUserMap = new HashMap<>();

    static {
        nameToUserMap.put("user@mail.ru", new User("user@mail.ru", "111", User.Role.ADMIN));
        nameToUserMap.put("reader@mail.ru", new User("reader@mail.ru", "111", User.Role.ADMIN));
        nameToUserMap.put("journalist@mail.ru", new User("journalist@mail.ru", "111", User.Role.JOURNALIST));
    }

    public void save(User user) {
        nameToUserMap.put(user.getName(), user);
    }

    public User getUser(String username) {

        return nameToUserMap.get(username);
    }
}