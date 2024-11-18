package app.service.user;

import app.bean.AuthNews;
import app.bean.User;
import app.dao.user.UserDao;
import app.dao.user.UserDaoImpl;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    public void save(User user) {

        userDao.save(user);
    }


    public User authenticate(AuthNews userRequst) {

        System.out.println("Auth via user service");
        User user = userDao.getUser(userRequst.getLogin());

        if (!user.getPassword().equals(userRequst.getPassword())) {
            System.out.println("User " + user + " not found " + user.getPassword() + " " + userRequst.getPassword());
            return null;
        }

        return user;
    }
}