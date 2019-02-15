package bean;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import cinema.DemoCinema;
import helper.NotValidMovieGenreException;
import helper.NotValidMovieTheatherTypeException;
import writers.MovieWriter;

public class Admin {
	private static final int MIN_PASSWORD_LENGTH = 4;
	Scanner sc = new Scanner(System.in);
	private String username;
	private String password;

	private static Admin instance = null;

	private Set<Movie> movies; // bez projekciite

	private Admin(String username, String password) {
		this.username = username;
		this.setPassword(password);
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
<<<<<<< HEAD:src/bean/Admin.java
			int option = sc.nextInt();
			switch (option) {
			case 1:
				try {
					this.createMovie();
				} catch (NotValidMovieGenreException | NotValidMovieTheatherTypeException e) {
					System.out.println("�������� ����...");
=======
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
>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6:src/users/Admin.java
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
				// TODO
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
		while (!oldPass.equals(this.password)) {
			System.err.println("������ ������! �������� ���: ");
			oldPass = sc.next();
		}
		System.out.println("�������� ���� ������...");
		String pass = sc.next();
		this.setPassword(pass);
		System.out.println("�������� � ������� �������!");
	}

	public Movie createMovie() throws NotValidMovieGenreException, NotValidMovieTheatherTypeException {
		Movie movie = Movie.getInstance();
//		MovieWriter.getInstance().addMovieToChronology(movie);//TODO
		Cinema.getInstance().addMovieToCatalogue(movie);//TODO
		movie.setTimes();
		return movie;
	}


	private void changeMovieProgram() {
		System.out.println("� ��� ���� � �����?");
		// TODO if catalogue is not empty
		Cinema cinema = Cinema.getInstance();
//		List<MovieTheather> listOfTheathers = new LinkedList<MovieTheather>(cinema.getMoviesCatalogue().keySet());
		List<MovieTheather> listOfTheathers = new LinkedList<MovieTheather>();//TODO ???
		for (int index = 1; index <= listOfTheathers.size(); index++) {
			System.out.println(index + " - " + listOfTheathers.get(index - 1));
		}
		MovieTheather theather = listOfTheathers.get(DemoCinema.sc.nextInt());

		System.out.println("�� ��� ���� ������ �� ����������� ����������?");
		List<LocalDate> dates = new LinkedList<LocalDate>(cinema.getMoviesCatalogue().get(theather).keySet());
		for (int index = 1; index < dates.size(); index++) {
			System.out.println(index + " - " + dates.get(index - 1));
		}
		LocalDate date = dates.get(DemoCinema.sc.nextInt());

//		this.cinema.getMoviesCatalogue().values();
		TreeSet<Movie> movies = new TreeSet<Movie>(cinema.getMoviesCatalogue().get(theather).get(date));
		// TODO select movies from file
		Movie movie = null;
		if (this.movies.size() > 0) {
			System.out.println("�������� ���� �� ��������: ");// TODO file
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
			return;
		}
	}

	public static Admin getInstance() {
		if (instance == null) {
			instance = new Admin("admin", "admin");
		}

		return instance;
	}

	private void setPassword(String password) {
		while (!isValidPassword(password)) {
			System.out.println("Try again: ");
			password = sc.next();
		}
		this.password = password;
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
