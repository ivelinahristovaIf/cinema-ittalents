package controller;

import bean.User;
import model.UserModel;

public class LoginController {

	public LoginController() {
		super();
	}

	public User validateLogin(String email, String password) throws Exception {
		User user = UserModel.getLoggerByEmailAndPassword(email, password);
		return user;
	}
}
