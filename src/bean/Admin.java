package bean;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cinema.DemoCinema;
import helper.NotValidMovieGenreException;
import helper.NotValidMovieTheatherTypeException;
import helper.UserHelper;

public class Admin implements ILogger{
	private int type;
	private String email;
	private String password;

	private static Admin instance = null;

	private Set<Movie> movies; // bez projekciite

	private Admin(String email, String password) {
		this.setType();
		this.email = email;
		this.setPassword(password);
		this.movies = new TreeSet<Movie>();
	}
	
	public static Admin getInstance() {
		if (instance == null) {
			instance = new Admin("admin", "admin");
		}

		return instance;
	}


	public void showMenu() {
		System.out.println("Изберете опция от администраторското меню:");
		System.out.println("0 -> За връщане към началното меню...");
		System.out.println("1 -> За да създадете нов филм, да го добавите към каталога и да му зададете програма...");
		System.out.println("2 -> За да редактирате програмата на филм...");
		System.out.println("3 -> За смяна на парола...");
		try {
			String regex = "[0-3]+";
			String option = DemoCinema.sc.next();
			if (option.matches(regex)) {
				switch (Integer.parseInt(option)) {
				case 1:
//					try {
////						this.createMovie();
//					} catch (NotValidMovieGenreException | NotValidMovieTheatherTypeException e) {
//						System.out.println("Повторен опит...");
//					}
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
			}

			System.out.println("1 -> За да продължите действия...");
			System.out.println("2 -> За да се отпишете...");
			System.out.println("0 -> Изход...");

			String stringNumber = DemoCinema.sc.next();
			String regex1 = "[0-2]+";
			while (!stringNumber.matches(regex1)) {
				System.out.println("Моля опитайте пак");
				stringNumber = DemoCinema.sc.next();
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
			System.err.println("Грешна команда!");
		}
	};

	private void changePassword() {

		System.out.println("Смяна на парола...");
		System.out.println("Въведете старата парола:");
		String oldPass = DemoCinema.sc.next();
		while (!oldPass.equals(this.password)) {
			System.err.println("Грешна парола! Опитайте пак: ");
			oldPass = DemoCinema.sc.next();
		}
		System.out.println("Въведете нова парола...");
		String pass = DemoCinema.sc.next();
		this.setPassword(pass);
		System.out.println("Паролата е сменена успешно!");
	}

//	public Movie createMovie() throws NotValidMovieGenreException, NotValidMovieTheatherTypeException {
//		Movie movie = Movie.getInstance();
////		MovieWriter.getInstance().addMovieToChronology(movie);//TODO
////		this.cinema.addMovieToCatalogue(movie);// TODO
//		movie.setTimes();
//		return movie;
//	}

	private void changeMovieProgram() {//TODO FX
		//TODO choose cinema
		Cinema cinema = new Cinema();
		System.out.println("В коя зала е филма?");
		// TODO if catalogue is not empty
//		List<MovieTheather> listOfTheathers = new LinkedList<MovieTheather>(cinema.getMoviesCatalogue().keySet());
		List<MovieTheather> listOfTheathers = new LinkedList<MovieTheather>(cinema.getMoviesCatalogue().keySet());
		for (int index = 1; index <= listOfTheathers.size(); index++) {
			System.out.println(index + " - " + listOfTheathers.get(index - 1));
		}
		MovieTheather theather = listOfTheathers.get(DemoCinema.sc.nextInt());

		System.out.println("За коя дата искате да редактирате програмата?");
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
			System.out.println("Изберете филм от каталога: ");// TODO file
			this.movies.stream().map(m -> m.getName()).forEachOrdered(m -> System.out.println(m));
			String name = DemoCinema.sc.next();
			movie = this.movies.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().get();
			System.out.println(movie);
		} else {
			System.out.println("Няма добавени филми!\nСъздайте нов филм: ");
//			try {
////				movie = this.createMovie();
//			} catch (NotValidMovieGenreException | NotValidMovieTheatherTypeException e) {
//				e.getMessage();
//			}
		}
		try {
			movie.setTimes();
		} catch (Exception e) {
			System.err.println("Null movie in Admin!");
			e.printStackTrace();
			return;
		}
	}

	private void setPassword(String password) {
		while (!UserHelper.getInstance().isValidPassword(password)) {
			System.out.println("Try again: ");
			password = DemoCinema.sc.next();
		}
		this.password = password;
	}

	public void setEmail(String username) {
		this.email = username;
	}
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void setType() {
		this.type = ILogger.ADMIN;
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", password=" + password + "]";
	}

}
