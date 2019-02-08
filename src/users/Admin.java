package users;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.sun.javafx.collections.MappingChange.Map;

import cinema.Cinema;
import cinema.DemoCinema;
import cinema.Movie;
import cinema.MovieTheather;
import cinema.NotValidMovieGenreException;
import cinema.NotValidMovieTheatherTypeException;
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
		System.out.println("0 -> �� ������� ��� ��������� ����...");
		System.out.println("1 -> �� �� ��������� ��� ����, �� �� �������� ��� �������� � �� �� �������� ��������...");
		System.out.println("2 -> �� �� ����������� ���������� �� ����...");
		System.out.println("3 -> �� ����� �� ������...");
		try {
			String regex = "[0-3]+";
			String option = sc.next();
			if(option.matches(regex)) {
				switch (Integer.parseInt(option)) {
				// if there is more code after switch -> return
				case 1:
					try {
						this.createMovie();
					} catch (NotValidMovieGenreException | NotValidMovieTheatherTypeException e) {
						System.out.println("�������� ����...");
					}
					break;
				case 2:
					this.changeMovieProgram();
					break;
				case 3:
					this.changePassword();
					break;
				case 0:
					DemoCinema.menu();
					break;
				}
			} else {
				System.out.println("������ �������!");
			}
			
			
			System.out.println("1 -> �� �� ���������� ��������...");
			System.out.println("2 -> �� �� �� ��������...");
			System.out.println("0 -> �����...");

			String stringNumber = sc.next();
			String regex1 = "[0-2]+";
			while(!stringNumber.matches(regex1)) {
				System.out.println("���� �������� ���");
				stringNumber = sc.next();
			}
			int next = Integer.parseInt(stringNumber);
			switch (next) {
			case 0:
				System.exit(0);
				break;
			case 1:
				showMenu();
				break;
			case 2:
				DemoCinema.menu();
				break;
			}
		} catch (InputMismatchException e) {
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

	public Movie createMovie() throws NotValidMovieGenreException, NotValidMovieTheatherTypeException {
		Movie movie = Movie.getInstance();
		this.cinema.addMovieToCatalogue(movie);
		movie.setTimes();
		return movie;
	}


	private void changeMovieProgram() {
		System.out.println("� ��� ���� � �����?");
		// TODO if catalogue is not empty
		List<MovieTheather> listOfTheathers = new LinkedList<MovieTheather>(this.cinema.getMoviesCatalogue().keySet());
		for (int index = 1; index <= listOfTheathers.size(); index++) {
			System.out.println(index + " - " + listOfTheathers.get(index - 1));
		}
		MovieTheather theather = listOfTheathers.get(DemoCinema.sc.nextInt());

		System.out.println("�� ��� ���� ������ �� ����������� ����������?");
		List<LocalDate> dates = new LinkedList<LocalDate>(this.cinema.getMoviesCatalogue().get(theather).keySet());
		for (int index = 1; index < dates.size(); index++) {
			System.out.println(index + " - " + dates.get(index - 1));
		}
		LocalDate date = dates.get(DemoCinema.sc.nextInt());
		
//		this.cinema.getMoviesCatalogue().values();
		TreeSet<Movie> movies = new TreeSet<Movie>(this.cinema.getMoviesCatalogue().get(theather).get(date));
		//TODO select movies from table
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
			} catch (NotValidMovieGenreException | NotValidMovieTheatherTypeException e) {
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
