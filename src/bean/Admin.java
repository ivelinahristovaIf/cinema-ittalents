package bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cinema.DemoCinema;
import helper.UserHelper;
import writers.MovieWriter;

public class Admin implements ILogger{
	private int type;
	private String email;
	private String password;
//	private Gson gson;
//	private File movieFile;

	private static Admin instance = null;

	private Set<Movie> movies; 

	private Admin(String email, String password)  {
		this.setType();
		this.email = email;
		this.setPassword(password);
		this.movies = new TreeSet<Movie>();
//		this.gson = new Gson();
//		this.movieFile = new File("Movies.json");
		this.fillMovies();
	}

	
	public void fillMovies() {
//		StringBuilder builder = new StringBuilder();
//		try (Scanner sc = new Scanner(this.movieFile)) {
//			while (sc.hasNextLine()) {
//				builder.append(sc.nextLine());
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Type setType = new TypeToken<LinkedHashSet<Movie>>() {
//		}.getType();
//		if (builder.length() > 0) {
//			Set<Movie> getMovies = gson.fromJson(builder.toString(), setType);
//			this.movies.addAll(getMovies);
//		} else {
//			System.out.println("Oshte nqma obekti");
//		}
		System.out.println("vzimam ot file");
		try {
			MovieWriter.getInstance().getMoviesFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("zapazvam v nov Set");
		Set<Movie> getMovies = MovieWriter.getInstance().getMovies();
		System.out.println("nalivam seta v this.movies");
		this.movies.addAll(getMovies);
		
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
		//TODO button in admin FX who starts this method (za men)
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


	private void changeMovieProgram() {
		Cinema cinema = new Cinema();//TODO get from json not from cinema
		System.out.println("В коя зала е филма?");
		// TODO if catalogue is not empty
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
//		TreeSet<Movie> movies = new TreeSet<Movie>(cinema.getMoviesCatalogue().get(theather).get(date));

		//select movies from file - DONE
		TreeSet<Movie> movies = new TreeSet<Movie>(this.movies);

		Movie movie = null;
		if (this.movies.size() > 0) {
			System.out.println("Изберете филм от каталога: ");// TODO file
			this.movies.stream().map(m -> m.getName()).forEachOrdered(m -> System.out.println(m));
			String name = DemoCinema.sc.next();
			movie = this.movies.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().get();
			System.out.println(movie);
		} else {
			System.out.println("Няма добавени филми!\nСъздайте нов филм: ");
			//TODO shte go napravq da se otvarq scenata i ot tam da se syzdava filma
		}
		try {
			//Zadava chasovete na filma
			movie.setTimes();
		} catch (Exception e) {
			System.err.println("Null movies in Admin!");
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
		this.type = 2;
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/**
	 * @return the movies
	 */
	public Set<Movie> getMovies() {
		return Collections.unmodifiableSet(movies);
	}
}
