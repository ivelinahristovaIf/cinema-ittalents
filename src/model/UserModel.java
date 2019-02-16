package model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import bean.Admin;
import bean.ILogger;
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

	public static ILogger getLoggerByEmailAndPassword(String email, String password)
			throws IOException, NoSuchAlgorithmException {
		UserWriter writer = new UserWriter();
		writer.getUsersFromFile();
		Set<ILogger> loggers = writer.getUsers();
		for (ILogger logger : loggers) {
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
			user = new User();
		}
		user.setId(savedUser.getId());
		user.setEmail(savedUser.getEmail());
//		user.setCinema(CinemaModel.fill(user.getCinema(), savedUser.getCinema()));//TODO
		return user;
	}

}
