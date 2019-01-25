package users;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cinema.Cinema;
import cinema.DemoCinema;
import crypt.Cryptography;

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
		System.out.println("Моля въведете вашите данни: ");
		this.setEmail();
		try {
			this.setPassword();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException");
			e.printStackTrace();
		}

		// TODO personal cinema
//		if (isValidCinema(personalCinema)) {
//			this.personalCinema = personalCinema;
//		}
		this.myProfile = new ConsumerProfile();
		// TODO hashCode and equals
		this.allCinemas = new HashSet<Cinema>();

	}

	public void showConsumerMenu() {
		System.out.println("Добре дошли в потребителското меню!\nМоля, изберете опция: ");
		System.out.println("1 -> Попълване на лични данни...");
		System.out.println("2 -> Смяна на парола...");
		System.out.println("3 -> Добавяне на интереси и любими жанрове...");
		System.out.println("0 -> Връщане към главното меню / ИЗХОД =>");//TODO
		try {
		int option =  sc.nextInt();
		switch (option) {
		case 1:
			this.setUpMyProfile();
			break;
		case 2:
			this.changePassword();
			break;
		case 3:
			this.setInterests();
			break;
		case 0:
			DemoCinema.menu();
//			showConsumerMenu();
			break;
		}
		}catch(InputMismatchException e) {
			showConsumerMenu();
			System.out.println("Wrong command!");
			System.out.println(e.getMessage());
		}
	}

	private void setUpMyProfile() {
		this.myProfile.setPhoneNumber();
		this.myProfile.setAdress();
		this.myProfile.setEducation();
	}

	private void changePassword() {
		System.out.println("Смяна на парола...");
		System.out.println("Въведете старата парола:");
		String oldPass = sc.next();
		try {
			while (!Cryptography.cryptSHA256(oldPass).equals(this.password)) {
				System.err.println("Грешна парола! Опитайте пак: ");
				oldPass = sc.next();
			}
			System.out.println("Въведете нова парола...");
			this.setPassword();
			System.out.println("Паролата е сменена успешно!");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private void setInterests() {
		// TODO choose multiple
		this.myProfile.addFavouriteGenre();
		this.myProfile.addPersonalInterest();
	}

	private void setPassword() throws NoSuchAlgorithmException {
		System.out.println("Парола:");
		String password = sc.next();
		while (!isValidPassword(password)) {
			System.out.println("Try again: ");
			password = sc.next();
		}
		this.password = Cryptography.cryptSHA256(password);
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
		return /* "Consumer [id=" + id + */", email: " + email + ", password: " + password + ", предпочитано кино: "
				+ personalCinema + "," + myProfile + "]";
	}

	public ConsumerProfile getMyProfile() {
		return myProfile;
	}

}
