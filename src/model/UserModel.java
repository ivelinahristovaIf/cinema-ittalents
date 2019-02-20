package model;

import java.io.IOException;
import java.util.Set;

import bean.User;
import writers.UserWriter;

public class UserModel {
	private static UserModel instance = null;

	private UserModel() {
		super();
	}

	public static UserModel getInstance() {
		if (instance == null) {
			instance = new UserModel();
		}
		return instance;
	}

	public static User getLoggerByEmailAndPassword(String email, String password) throws IOException {
		UserWriter writer = new UserWriter();
		writer.getUsersFromFile();
		Set<User> loggers = writer.getUsers();
		for (User logger : loggers) {
			System.out.println("UserModel " + logger);
			if (logger.getEmail().equals(email) && logger.getPassword().equals(password)) {
				return logger;
			}
		}
		System.out.println("no such user");
		return null;
	}

	public static User fill(User user, User savedUser) {
		if (user == null) {
			user = new User(savedUser.isAdmin());
		}
		user.setId(savedUser.getId());
		user.setEmail(savedUser.getEmail());
		return user;
	}

}
