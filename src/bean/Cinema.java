package bean;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import helper.CalendarHelper;
import writers.MovieTheaterTypeWriter;

public class Cinema {
	private String name;
	private String address;
	private String phoneNumber;
	private List<MovieTheather> movieTheathers = new ArrayList<>();
	private static Cinema instance = null;
	// type->date->movie
	private Map<MovieTheatherType, TreeMap<LocalDate, TreeSet<Movie>>> moviesCatalogue = new HashMap<>();
	// TODO movie setTheather

	public Cinema() {
		super();
	}

	private Cinema(String name, String address, String phoneNumber) throws FileNotFoundException {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		
		MovieTheaterTypeWriter.getInstance().getMovieTheaterTypesFromFile();
		Set<MovieTheatherType> types = MovieTheaterTypeWriter.getInstance().getTypes();
		for(MovieTheatherType type : types) {
			LocalDate start = LocalDate.now();
			TreeMap<LocalDate, TreeSet<Movie>> dates = new TreeMap<>();
			while (start.isBefore(LocalDate.now().plusDays(CalendarHelper.NUMBER_DAYS_IN_CALENDAR))) {
				dates.put(start, new TreeSet<Movie>());
				start = start.plusDays(1);
			}
			this.moviesCatalogue.put(type, dates);
		}
	}

	public static Cinema getInstance() throws FileNotFoundException {
		if (instance == null) {
			instance = new Cinema("Кино Арена", "България, София, бул. Цариградско шосе, 115", "555-012-413");
		}
		return instance;
	}

	public HashSet<MovieTheatherType> getAllMovieTheathers() {
		HashSet<MovieTheatherType> theathers = new HashSet<>(this.moviesCatalogue.keySet());
		return theathers;
	}
	public MovieTheatherType getMovieTheatherByType(MovieTheatherType type) {
		for (MovieTheatherType movieTheatherType : this.moviesCatalogue.keySet()) {
			if(movieTheatherType.equals(type)) {
				return movieTheatherType;
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

	public Set<Movie> getAllMoviesByTheatherAndDate(MovieTheatherType mt, LocalDate date) {
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

	public void addMovieToCatalogue(MovieTheatherType theater, LocalDate date, Movie movie) {
		if (movie != null && this.moviesCatalogue != null) {
			if (!this.moviesCatalogue.isEmpty()) {
				System.out.println("CLASS CINEMA: kataloga ne e prazen");
			} else {
				System.out.println("Все още няма добавени зали в киното! Създайте зала");
//				movieTheater = this.addMovieTheatherInCinema();TODO
			}
			if (!this.moviesCatalogue.containsKey(theater)) {
				this.moviesCatalogue.put(theater, new TreeMap<>());
				System.out.println("Нова зала беше току що добавена в киното!");
				System.out.println();
			}
			System.out.println(date);
			if (!this.moviesCatalogue.get(theater).containsKey(date)) {
				System.out.println("Залата е свободна за тази дата");// TODO proverka za svobodni chasove
				// TODO freeHours moved in MovieTheather
				this.moviesCatalogue.get(theater).put(date, new TreeSet<Movie>());
			}
			// add movie in TreeSet
			boolean isAdded = this.moviesCatalogue.get(theater).get(date).add(movie);
			System.out.println("dobaven li e:" + isAdded);
		} else {
			System.err.println("null movie or catalogue");
		}
	}

	public List<Movie> showAllMoviesByDate(LocalDate date) {// TODO Cinema singleton
		System.out.println("Всички филми на " + date);
		List<Movie> movies = new ArrayList<Movie>();
		if (!this.moviesCatalogue.isEmpty()) {
			for (MovieTheatherType movieTheatherType : this.moviesCatalogue.keySet()) {
				if (this.moviesCatalogue.get(movieTheatherType).containsKey(date)) {
					movies.addAll(this.moviesCatalogue.get(movieTheatherType).get(date));
					if (!movies.isEmpty()) {
						for (Movie movie : movies) {
							System.out.println(movie.getId() + " " + movie);
						}
					} else {
						System.out.println("Няма филми за този ден в зала " + movieTheatherType);
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

	public Map<MovieTheatherType, TreeMap<LocalDate, TreeSet<Movie>>> getMoviesCatalogue() {
		return Collections.unmodifiableMap(this.moviesCatalogue);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Cinema cinema = new Cinema("Arena", "Sofia", "0885546512");
		System.out.println(cinema.getMoviesCatalogue());
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
