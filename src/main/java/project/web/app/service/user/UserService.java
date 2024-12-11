package project.web.app.service.user;

import project.web.app.bean.AuthNews;
import project.web.app.bean.User;

import java.sql.SQLException;

public interface UserService {

	User authenticate(AuthNews userRequest);

	void save(User user);
}
