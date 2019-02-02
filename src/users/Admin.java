package users;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import cinema.Cinema;
import cinema.DemoCinema;
import cinema.Movie;
import cinema.NotValidMovieGenreException;
import crypt.Cryptography;

public class Admin {
	private static final int MIN_PASSWORD_LENGTH = 4;
	Scanner sc = new Scanner(System.in);
	private String username;
	private String password;

	private static Admin theAdmin = null;

	private Set<Movie> movies; // bez projekciite
	private Cinema cinema;

	private Admin(String username, String password) {
		this.username = username;
		try {
			this.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException from password");
			e.printStackTrace();
		}
		this.cinema = new Cinema();
		this.movies = new TreeSet<Movie>((m1, m2) -> m1.getName().compareTo(m2.getName()));
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void showMenu() {
		System.out.println("�������� ����� �� ������������������ ����:");
		// TODO add movie, change program etc
		System.out.println("0 -> �� ������� ��� ��������� ����...");
		System.out.println("1 -> �� �� ��������� ��� ����, �� �� �������� ��� �������� � �� �� �������� ��������...");
		System.out.println("2 -> �� �� ����������� ���������� �� ����...");
		System.out.println("3 -> �� ����� �� ������...");
		try {
			int option = sc.nextInt();
			switch (option) {
			// if there is more code after switch -> return
			case 1:
				this.createMovie();
				break;
			case 2:
				this.changeMovieProgram();
				break;
			case 3:
				this.changePassword();
			case 0:
				DemoCinema.menu();
				break;
			}
		} catch (InputMismatchException | NotValidMovieGenreException e) {
			System.err.println("������ �������!");
		}
	};

	private void changePassword() {

		System.out.println("����� �� ������...");
		System.out.println("�������� ������� ������:");
		String oldPass = sc.next();
		try {
			while (!Cryptography.cryptSHA256(oldPass).equals(this.password)) {
				System.err.println("������ ������! �������� ���: ");
				oldPass = sc.next();
			}
			System.out.println("�������� ���� ������...");
			String pass = sc.next();
			this.setPassword(pass);
			System.out.println("�������� � ������� �������!");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	public Movie createMovie() throws NotValidMovieGenreException {
		Movie movie = Movie.getInstance();
		this.cinema.addMovieToCatalogue(movie);
		movie.setTimes();
		return movie;
	}

	private void changeMovieProgram() {
		Movie movie = null;
		if (this.movies.size() > 0) {
			System.out.println("�������� ���� �� ��������: ");// TODO data base
			this.movies.stream().map(m -> m.getName()).forEachOrdered(m -> System.out.println(m));
			String name = sc.next();
			movie = this.movies.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().get();
			System.out.println(movie);
		} else {
			System.out.println("���� �������� �����!\n�������� ��� ����: ");
			try {
				movie = this.createMovie();
			} catch (NotValidMovieGenreException e) {
				e.getMessage();
			}
		}
		try {
			movie.setTimes();
		} catch (Exception e) {
			System.err.println("Null movie in Admin!");
			e.printStackTrace();
		}
	}

	public static Admin createAdmin(String username, String password) {
		if (Admin.theAdmin == null) {
			Admin.theAdmin = new Admin(username, password);
		}

		return Admin.theAdmin;
	}

	private void setPassword(String password) throws NoSuchAlgorithmException {
		while (!isValidPassword(password)) {
			System.out.println("Try again: ");
			password = sc.next();
		}
		this.password = Cryptography.cryptSHA256(password);
	}

	private boolean isValidPassword(String password) {
		// TODO validate password
		if (password != null && password.trim().length() >= MIN_PASSWORD_LENGTH) {
			return true;
		}
		System.err.println("Password must be at least with " + MIN_PASSWORD_LENGTH + " characters!");
		return false;
	}

}
