package model;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import bean.User;
import writers.UserWriter;

public class UserModel {
	private static UserModel instance = null;
	
	private UserModel() {
		super();
	}
	public static UserModel getInstance() {
		if(instance==null) {
			instance = new UserModel();
		}
		return instance;
	}

	public static User getUserByEmailAndPassword(String email, String password) throws IOException, NoSuchAlgorithmException {
		//TODO izznesi go nqkyde
		UserWriter writer = new UserWriter();
		writer.getUsersFromFile();
		Set<User> users = writer.getUsers();
		for (User user : users) {
			System.out.println(user);
			if(user.getEmail().equals(email)&&user.getPassword().equals(password)) {
				return user;
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
