package writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import bean.Admin;
import bean.ILogger;
import bean.User;
import helper.UserHelper;

public class UserWriter {
	private Set<ILogger> users;

	// TODO singleton

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private File file;

	public UserWriter() throws IOException {
		this.users = new LinkedHashSet<ILogger>();
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

	public void addUser(ILogger user) {
		if (user != null) {
			for (ILogger u : users) {
				if(u.getType()==ILogger.USER) {
				if (u.equals(user)) {
//					((User) u).setFirstname(((User) user).getFirstname());
//					((User) u).setSurname(((User) user).getSurname());
//					((User) u).setLastname(((User) user).getLastname());
//					((User) u).setBirthDate(((User) user).getBirthDate());
//					((User) u).setCity(((User) user).getCity());
//					((User) u).setEmail(((User) user).getEmail());
//					((User) u).setPassword(((Admin) user).getPassword());
					//TODO
				}
				}else {
					//TODO admin
				}
			}
			this.users.add(user);
		}
	}

	public static void main(String[] args) throws IOException {
		UserWriter uw = new UserWriter();
		uw.getUsersFromFile();
		uw.addUser(new User("ivelina@abv.bg", "12345", "Ivelina", "Ivaylova", "Hristova", LocalDate.of(1997, 11, 20), "София"));
		uw.addUser(Admin.getInstance());
		uw.saveUsersToFile();
		
		for (ILogger user : uw.users) {
			System.out.println(user);
		}
	}

	public Set<ILogger> getUsers() {
		return this.users;
	}
}
