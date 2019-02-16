package controller;

import bean.ILogger;
import model.UserModel;

public class LoginController {

	public LoginController() {
		super();
	}

	public ILogger validateLogin(String email, String password) throws Exception {
		ILogger user = UserModel.getLoggerByEmailAndPassword(email, password);
		return user;
	}
}
