package bean;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import cinema.DemoCinema;
import helper.InvalidPersonException;
import helper.UserHelper;
import writers.MovieTheaterTypeWriter;
import writers.MovieWriter;

public class Admin {
	private String email;
	private String password;

	private static Admin instance = null;
	
	public Admin() {
	}

	public Admin(String email, String password) throws InvalidPersonException {
		this.email = email;
		this.setPassword(password);
	}

	public static Admin getInstance() throws InvalidPersonException {
		if (instance == null) {
			instance = new Admin("admin", "admin");
		}
		return instance;
	}

	public void changePassword() throws InvalidPersonException {
		// TODO button in admin FX who starts this method
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

	public void changeMovieProgram() {
		System.out.println("В коя зала е филма?");
		try {
			MovieTheaterTypeWriter.getInstance().getMovieTheaterTypesFromFile();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		List<MovieTheatherType> listOfTheathers = new LinkedList<MovieTheatherType>(
				MovieTheaterTypeWriter.getInstance().getTypes());
		for (int index = 1; index <= listOfTheathers.size(); index++) {
			System.out.println(index + " - " + listOfTheathers.get(index - 1));
		}
		MovieTheatherType theather = listOfTheathers.get(DemoCinema.sc.nextInt() - 1);

		System.out.println("За коя дата искате да редактирате програмата?");
		List<LocalDate> dates = null;
		try {
			dates = new LinkedList<LocalDate>(Cinema.getInstance().getMoviesCatalogue().get(theather).keySet());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int index = 1; index < dates.size(); index++) {
			System.out.println(index + " - " + dates.get(index - 1));
		}
//		LocalDate date = dates.get(DemoCinema.sc.nextInt());
		try {
			MovieWriter.getInstance().getMoviesFromFile();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		TreeSet<Movie> movies = new TreeSet<Movie>(MovieWriter.getInstance().getMovies());

		Movie movie = null;
		if (movies.size() > 0) {
			System.out.println("Изберете филм от каталога: ");
			movies.stream().map(m -> m.getName()).forEachOrdered(m -> System.out.println(m));
			String name = DemoCinema.sc.next();
			try {
				movie = movies.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().get();
				System.out.println(movie);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			System.out.println("Няма добавени филми!\nСъздайте нов филм: ");
			// TODO shte go napravq da se otvarq scenata i ot tam da se syzdava filma
		}
		try {
			// Zadava chasovete na filma
			movie.setTimes();
		} catch (Exception e) {
			System.err.println("Null movies in Admin!");
			e.printStackTrace();
			return;
		}
	}

	public void setPassword(String password) throws InvalidPersonException {
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

//	@Override
//	public int getType() {
//		return type;
//	}
//
//	@Override
//	public void setType() {
//		this.type = 2;
//	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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

		return true;
	}

//	public static void main(String[] args) {
//		try {
//			Admin.getInstance().changeMovieProgram();
//		} catch (InvalidPersonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Admin.getInstance().changePassword();
//		} catch (InvalidPersonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
