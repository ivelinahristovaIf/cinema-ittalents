package users;

public class Admin {
	private String username;
	private String password;
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void showMenu() {
		System.out.println("Изберете опция от администраторското меню:");
		//TODO add movie, change program etc
		
	};
	

}
