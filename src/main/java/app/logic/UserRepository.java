package app.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.bean.AuthNews;
import app.bean.User;

public class UserRepository {
	
	private static Map<String, User> nameToUserMap = new HashMap<>();
	
	static {
		nameToUserMap.put("user@mail.ru", new User("user@mail.ru", "111"));
		System.out.println(nameToUserMap);
	}
	
	public void save(User user) {
		nameToUserMap.put(user.getName(), user);
	}
	
	public User authenticate(AuthNews userRequst) {
		System.out.println(nameToUserMap);
		User user = nameToUserMap.get(userRequst.getLogin());
		if (user == null) {
			System.out.println("User not found");
			return null;
		}
		if	(!user.getPassword().equals(userRequst.getPassword())) {
			System.out.println("User " + user  + " not found " + user.getPassword() + " " + userRequst.getPassword());
			return null;
		}
	
		return user;
	}
}