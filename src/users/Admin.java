package users;

import java.security.NoSuchAlgorithmException;

import crypt.Cryptography;

public class Admin {
	private String username;
	private String password;

	private static Admin theAdmin = null;

	private Admin(String username, String password) {
		this.username = username;
		try {
			this.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException from password");
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void showMenu() {
		System.out.println("Изберете опция от администраторското меню:");
		// TODO add movie, change program etc

	};

	public static Admin createAdmin(String username, String password) {
		if (Admin.theAdmin == null) {
			Admin.theAdmin = new Admin(username, password);
		}

		return Admin.theAdmin;
	}

	private void setPassword(String password) throws NoSuchAlgorithmException {
		if (password != null && password.trim().length() > 4) {
			this.password = Cryptography.cryptSHA256(password);
		}
	}

}
