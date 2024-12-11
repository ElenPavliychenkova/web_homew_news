package project.web.app.service.user;

import project.web.app.bean.AuthNews;
import project.web.app.bean.User;
import project.web.app.dao.user.UserDao;
import project.web.app.exceptions.auth.AuthenticationException;
import project.web.app.exceptions.user.UserNotFoundException;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {

        userDao.save(user);
    }

    public User authenticate(AuthNews userRequest) {

        System.out.println("Auth via user service");
        User user = userDao.getUser(userRequest.getLogin());

        if (user == null) {
            throw new UserNotFoundException("user not found " + userRequest.getLogin());
        }

        if (!user.getPassword().equals(userRequest.getPassword())) {
            System.out.println("User " + user + " not found " + user.getPassword() + " " + userRequest.getPassword());
            throw new AuthenticationException("Wrong user");
        }

        return user;
    }
}