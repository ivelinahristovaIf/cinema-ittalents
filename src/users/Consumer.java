package users;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cinema.Cinema;

public class Consumer {
	private static final int MIN_PASSWORD_LENGTH = 8;
	Scanner sc = new Scanner(System.in);
	private int id;
	private String email;
	private String password;
	private Cinema personalCinema; // the cinema he choosed; composition
	private Set<Cinema> allCinemas;// choose from another cinema; agregation

	private ConsumerProfile myProfile;

	public Consumer(int id) {

		this.id = id;
		System.out.println("Регистрация");
		System.out.println("Моля въведете вашите данни: ");
		this.setEmail();
		this.setPassword();

		// TODO personal cinema
//		if (isValidCinema(personalCinema)) {
//			this.personalCinema = personalCinema;
//		}
		this.myProfile = new ConsumerProfile();
		// TODO hashCode and equals
		this.allCinemas = new HashSet<Cinema>();

	}

	private void setPassword() {
		System.out.println("Парола:");
		String password = sc.next();
		while (!isValidPassword(password)) {
			System.out.println("Try again: ");
			password = sc.next();
		}
		this.password = password;
	}

	private void setEmail() {
		System.out.println("E-mail:");
		String email = sc.next();
		while (!isValidEmail(email)) {
			System.out.println("Try again: ");
			email = sc.next();
		}
		this.email = email;

	}

//	private boolean isValidCinema(Cinema personalCinema) {
//		// TODO if set contains personalCinema
//		return true;
//	}

	private boolean isValidPassword(String password) {
		// TODO validate password
		if (password != null && password.trim().length() >= MIN_PASSWORD_LENGTH) {
			return true;
		}
		System.err.println("Password must be at least with " + MIN_PASSWORD_LENGTH + " characters!");
		return false;
	}

	private boolean isValidEmail(String email) {
		Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		Matcher regMatcher = regexPattern.matcher(email);
		if (regMatcher.matches()) {
			return true;
		}
		System.err.println("Not suitable email adress!");
		return false;
	}

	@Override
	public String toString() {
		return "Consumer [id=" + id + ", email=" + email + ", password=" + password + ", personalCinema="
				+ personalCinema + "," + myProfile + "]";
	}

}
