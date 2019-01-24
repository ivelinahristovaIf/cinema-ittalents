package users;

public class Admin {
	private String username;
	private String password;
	
	private static Admin theAdmin = null;
	
	private Admin(String username, String password) {
		this.username = username;
		this.password = password;
		System.out.println("adminche");
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
	
	public static Admin createAdmin(String username, String password) {
		if(Admin.theAdmin == null) {
			Admin.theAdmin = new Admin(username, password);
		}
		
		return Admin.theAdmin;
	}

}
