package project.web.app.service.user;

import project.web.app.bean.AuthNews;
import project.web.app.bean.User;

public interface UserService {

	User authenticate(AuthNews userRequest);

	void save(User user);

}