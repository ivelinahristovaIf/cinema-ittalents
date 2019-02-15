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

import bean.User;

public class UserWriter {
	private Set<User> users;

	// TODO singleton

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;

	public UserWriter() throws IOException {
		this.users = new LinkedHashSet<User>();
		this.file = new File("Users.json");
		if (!this.file.exists()) {
			new File("Users.json").createNewFile();
		}

	}

	public void saveUsersToFile() {
		if (!users.isEmpty()) {
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
				writer.println(gson.toJson(users));
			} catch (IOException e) {
				return;
			}
			System.out.println("Zapisah gi vyv file");
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

	public void addUser(User user) {
		if (user != null) {
			for (User u : users) {
				if (u.equals(user) && (!u.getFirstname().equals(user.getFirstname())
						|| !u.getSurname().equals(user.getSurname()) || !u.getLastname().equals(u.getLastname())
						|| !u.getEmail().equals(user.getEmail()) || !u.getPassword().equals(user.getPassword())
						|| !u.getBirthDate().equals(user.getBirthDate()) || !u.getCity().equals(user.getCity()))) {
					user = u;
					//TODO ne se promenq ako promenim profila
				}
			}
			this.users.add(user);
		}
	}

//	public static void main(String[] args) throws IOException {
//		UserWriter uw = new UserWriter();
//		uw.getUsersFromFile();
//		uw.addUser(new User("ivelina@abv.bg", "12345", "Ivelina", "Ivaylova", "Hristova", LocalDate.of(1997, 11, 20), "София", 1));
//		uw.saveUsersToFile();
//		
//		for (User user : uw.users) {
//			System.out.println(user);
//		}
//	}

	public Set<User> getUsers() {
		return this.users;
	}
}
