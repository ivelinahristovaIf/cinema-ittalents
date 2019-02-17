package bean;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import cinema.DemoCinema;
import helper.CalendarHelper;
import helper.NotValidMovieTheatherTypeException;
import writers.MovieTheaterTypeWriter;
import writers.MovieTheaterWriter;

public class Cinema {
	private String name;
	private String address;
	private String phoneNumber;
	private List<MovieTheather> movieTheathers = new ArrayList<>();
	private static Cinema instance = null;
	// type->date->movie
	private Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> moviesCatalogue = new HashMap<>();
	// TODO movie setTheather

	public Cinema() {
		super();
	}

	private Cinema(String name, String address, String phoneNumber) throws FileNotFoundException {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;

		MovieTheaterTypeWriter.getInstance().getMovieTheaterTypesFromFile();// LOAD TYPES
		MovieTheaterWriter.getInstance().getMovieTheatersFromFile();// LOAD THEATERS
		Set<MovieTheatherType> types = MovieTheaterTypeWriter.getInstance().getTypes();// GET TYPES
		MovieTheather mt;
		for (MovieTheatherType movieTheatherType : types) {// FOR EVERY TYPE CREATE THEATHER
			mt = new MovieTheather(movieTheatherType); // TODO remove cinema from construktor
			MovieTheaterWriter.getInstance().addMovieTheater(mt);
			this.movieTheathers.add(mt);// TODO dali da ima movieTheathers kato field
			if (!this.moviesCatalogue.containsKey(mt)) {// if not contains theather
				LocalDate start = LocalDate.now();// FILL DATES
				TreeMap<LocalDate, TreeSet<Movie>> dates = new TreeMap<>();
				while (!start.isAfter(LocalDate.now().plusDays(CalendarHelper.NUMBER_DAYS_IN_CALENDAR))) {
					dates.put(start, new TreeSet<Movie>());
					start = start.plusDays(1);
				}
				this.moviesCatalogue.put(mt, dates);
			}
		}
		MovieTheaterWriter.getInstance().saveMovieTheaterToFile();
	}

	public static Cinema getInstance() throws FileNotFoundException {
		if (instance == null) {
			instance = new Cinema("Кино Арена", "България, София, бул. Цариградско шосе, 115", "555-012-413");
		}
		return instance;
	}

	public HashSet<MovieTheather> getAllMovieTheathers() {
		HashSet<MovieTheather> theathers = new HashSet<>(this.moviesCatalogue.keySet());
		return theathers;
	}
	public MovieTheather getMovieTheatherByType(MovieTheatherType type) {
		for (MovieTheather movieTheather : this.moviesCatalogue.keySet()) {
			if(movieTheather.equals(type)) {
				return movieTheather;
			}
		}
		System.out.println("No theather by this type");
		return null;
	}

	public Set<LocalDate> getAllDatesByTheather(MovieTheather mt) {
		if (this.moviesCatalogue.isEmpty()) {
			System.out.println("Няма зали в киното");
			return null;
		}
		if (!this.moviesCatalogue.containsKey(mt)) {
			System.out.println();
			return null;
		}
		return this.moviesCatalogue.get(mt).keySet();
	}

	public Set<Movie> getAllMoviesByTheatherAndDate(MovieTheather mt, LocalDate date) {
		if (!this.moviesCatalogue.containsKey(mt)) {
			System.out.println("no movie theather");
			return null;
		}
		if (!this.moviesCatalogue.get(mt).containsKey(date)) {
			System.out.println("no date");
			return null;
		}
		return this.moviesCatalogue.get(mt).get(date);
	}

	public MovieTheather addMovieTheatherInCinema(MovieTheatherType type) {
		if (this.movieTheathers == null) {
			this.movieTheathers = new ArrayList<MovieTheather>();
		}
		MovieTheather theather = null;
		theather = new MovieTheather(type);
		this.movieTheathers.add(theather);
		return theather;
	}

	public void addMovieToCatalogue(Movie movie) {
		if (movie != null && this.moviesCatalogue != null) {
			System.out.println("Моля изберете зала за прожекцията: ");
			MovieTheather movieTheater = null;
			if (!this.moviesCatalogue.isEmpty()) {
				List<MovieTheather> listOfTheathers = new LinkedList<MovieTheather>(this.moviesCatalogue.keySet());
				for (int index = 1; index <= listOfTheathers.size(); index++) {
					System.out.println(index + " - " + listOfTheathers.get(index - 1));
				}
				int index = DemoCinema.sc.nextInt();
				movieTheater = listOfTheathers.get(index - 1);
			} else {
				System.out.println("Все още няма добавени зали в киното! Създайте зала");
//				movieTheater = this.addMovieTheatherInCinema();TODO
			}
			if (!this.moviesCatalogue.containsKey(movieTheater)) {
				this.moviesCatalogue.put(movieTheater, new TreeMap<>());
				System.out.println("Нова зала беше току що добавена в киното!");
				System.out.println();
			}
			CalendarHelper.getInstance().showWeeksCalendar();
			LocalDate date = CalendarHelper.getInstance().inputDate();
			System.out.println(date);
			if (this.moviesCatalogue.get(movieTheater).containsKey(date)) {
				System.out.println("Залата е заета за тази дата");// TODO proverka za svobodni chasove
				// TODO freeHours moved in MovieTheather
			}
			this.moviesCatalogue.get(movieTheater).put(date, new TreeSet<Movie>());
			// add movie in TreeSet
			boolean isAdded = this.moviesCatalogue.get(movieTheater).get(date).add(movie);
			System.out.println("dobaven li e:" + isAdded);
		} else {
			System.err.println("null movie or catalogue");
		}
	}

	public List<Movie> showAllMoviesByDate(LocalDate date) {// TODO Cinema singleton
		System.out.println("Всички филми на " + date);
		List<Movie> movies = new ArrayList<Movie>();
		if (!this.moviesCatalogue.isEmpty()) {
			for (MovieTheather movieTheather : this.moviesCatalogue.keySet()) {
				if (this.moviesCatalogue.get(movieTheather).containsKey(date)) {
					movies.addAll(this.moviesCatalogue.get(movieTheather).get(date));
					if (!movies.isEmpty()) {
						for (Movie movie : movies) {
							System.out.println(movie.getId() + " " + movie);
						}
					} else {
						System.out.println("Няма филми за този ден в зала " + movieTheather);
					}
					return movies;
				} else {
					System.out.println("Съжаляваме, но няма филми за тази дата!");
					// TODO choose again
					return null;
				}
			}
		}
		return movies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<MovieTheather> getMovieTheathers() {
		return movieTheathers;
	}

	public void setMovieTheathers(List<MovieTheather> movieTheathers) {
		this.movieTheathers = movieTheathers;
	}

	public Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> getMoviesCatalogue() {
		return Collections.unmodifiableMap(this.moviesCatalogue);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Cinema cinema = new Cinema("Arena", "Arena", "555-012-413");
		for (MovieTheather mt : cinema.moviesCatalogue.keySet()) {
			System.out.println(mt);
			System.out.println(mt.getType());
		}
	}

	@Override
	public String toString() {
		return "Cinema [name=" + name + ", address=" + address + "]";
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
