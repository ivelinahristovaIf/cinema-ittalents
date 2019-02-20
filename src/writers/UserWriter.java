package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bean.User;
import helper.InvalidPersonException;

public class UserWriter {
	private Set<User> users;
//	private static UserWriter instance = null;
	private Gson gson = new GsonBuilder().create();
	private File file;

	public UserWriter() throws IOException {
		this.users = new LinkedHashSet<User>();
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
			System.out.println("Wrote them to file " + file);
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
			System.out.println("File is empty");
		}
	}

	public void addUser(User user) {
		if (user != null) {
			for (Iterator<User> it = users.iterator(); it.hasNext();) {
				if (it.next().equals(user)) {
					it.remove();
					System.out.println("Вече има");
				}
			}
			this.users.add(user);
		}
	}

	public static void main(String[] args) throws IOException {
		UserWriter uw = new UserWriter();
		uw.getUsersFromFile();
//		try {
//			uw.addUser(
//					new User(true, "admin@abv.bg", "admin", "admin", "adminov", "adminov", LocalDate.now(), "Sofia"));
//		} catch (InvalidPersonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			uw.addUser(new User(false, "ivelina@abv.bg", "12345678", "Ivelina", "Ivaylova", "Hristova",
//					LocalDate.of(1997, 11, 20), "Sofia"));
//		} catch (InvalidPersonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		uw.saveUsersToFile();

		for (User user : uw.users) {
			System.out.println(user);
		}
	}

	public Set<User> getUsers() {
		return this.users;
	}
}
