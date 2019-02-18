package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bean.Admin;
import bean.ILogger;
import bean.User;
import helper.InvalidPersonException;

public class UserWriter {
	private Set<ILogger> users;
//	private static UserWriter instance = null;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;

	public UserWriter() throws IOException {
		this.users = new LinkedHashSet<ILogger>();
		this.file = new File("Users.json");
		if (!this.file.exists()) {
			new File("Users.json").createNewFile();
		}

	}
	
//	public static UserWriter getInstance() {
//		if (instance == null) {
//			try {
//				instance = new UserWriter();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return instance;
//	}

	public void saveUsersToFile() {
		if (!users.isEmpty()) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
				writer.println(gson.toJson(users));
			} catch (IOException e) {
				return;
			}
			System.out.println("Zapisah gi vyv file "+file.getName());
		}
	}

	public void getUsersFromFile() throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				builder.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Type setType = new TypeToken<LinkedHashSet<User>>() {
		}.getType();
		if (builder.length() > 0) {
			Set<User> getUsers = gson.fromJson(builder.toString(), setType);
			this.users.addAll(getUsers);
		} else {
			System.out.println("Oshte nqma obekti");
		}
	}

	public void addUser(ILogger user) {
		if (user != null) {
			for (ILogger u : users) {
				if (u.equals(user)) {
					this.users.remove(u);
					System.out.println("Вече има");
				}
			}
			this.users.add(user);
		}
	}

//	public static void main(String[] args) throws IOException {
//		UserWriter uw = new UserWriter();
//		uw.getUsersFromFile();
//		try {
//			uw.addUser(Admin.getInstance());
//		} catch (InvalidPersonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		uw.saveUsersToFile();
//		
//		for (ILogger user : uw.users) {
//			System.out.println(user);
//		}
//	}

	public Set<ILogger> getUsers() {
		return this.users;
	}
}
