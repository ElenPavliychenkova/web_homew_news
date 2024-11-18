package app.service.user;

import app.bean.AuthNews;
import app.bean.User;

public interface UserService {

	User authenticate(AuthNews userRequst);

	void save(User user);
}
