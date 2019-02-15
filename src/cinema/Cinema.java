package cinema;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import bean.Admin;
import bean.Movie;
import helper.NotValidMovieTheatherTypeException;
import tickets.Ticket;
import users.Consumer;

public class Cinema {
	private static final int NUMBER_DAYS_IN_CALENDAR = 7;


<<<<<<< HEAD
	// TODO remove tickets
	private Set<Ticket> tickets;
=======
		private String name;

		private MovieGenres(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	
	public enum movieCategories {
		A, B, C, D
	}

	public static Set<Consumer> consumers; // TO DO private non static
	private Set<MovieTheather> theathers;
>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6
	// type->date->movie
	private Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> moviesCatalogue;
	private Comparator<Movie> movieComparator;
	private static Cinema instance = null;

<<<<<<< HEAD
	private Cinema() {
		this.tickets = new HashSet<>();
=======
	public Cinema() {
		consumers = new HashSet<Consumer>();
		// TODO comparator consumers
		// TODO compare by theather type
		this.theathers = new TreeSet<MovieTheather>((mt1, mt2) -> mt1.getType().compareToIgnoreCase(mt2.getType()));
>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6
		// TODO maybe compare movies by id
		this.movieComparator = (movie1, movie2) -> movie1.getName().compareToIgnoreCase(movie2.getName());
		this.moviesCatalogue = new HashMap<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>>();

		for (int i = 0; i < MovieTheather.MOVIE_THEATHER_TYPE.length; i++) {
			MovieTheather mt = new MovieTheather(MovieTheather.MOVIE_THEATHER_TYPE[i],
					MovieTheather.VIDEO_FORMAT[(int) (MovieTheather.VIDEO_FORMAT.length * Math.random())],
					MovieTheather.AUDIO_FORMAT[(int) (MovieTheather.AUDIO_FORMAT.length * Math.random())]);
			this.moviesCatalogue.put(mt, new TreeMap<>());
		}
	}

<<<<<<< HEAD
	public static Cinema getInstance() {
		if (instance == null) {
			instance = new Cinema();
		}
		return instance;
	}

	public void showAllMoviesByDate(LocalDate date) {
		System.out.println("Всички филми на " + date);
		for (MovieTheather movieTheather : this.moviesCatalogue.keySet()) {
			TreeSet<Movie> movies = this.moviesCatalogue.get(movieTheather).get(date);
			if (movies != null) {
				for (Movie movie : movies) {
					System.out.println(movie.getId() + " " + movie);
				}
			}else {
				System.out.println("Няма филми за този ден в зала "+movieTheather);
			}
=======
	public MovieTheather addMovieTheatherToCinema() {
		MovieTheather theather = null;
		try {
			theather = MovieTheather.getInstance();
		} catch (NotValidMovieTheatherTypeException e) {
			e.printStackTrace();
		}
		if (theather != null) {
			this.theathers.add(theather);
		} else {
			System.err.println("Null theather!"); 
>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6
		}
	}

	public MovieTheather addMovieTheatherToCinema(String type, String videoFormat, String audioFormat) {
		MovieTheather theather = null;
		theather = new MovieTheather(type, videoFormat, audioFormat);
		this.moviesCatalogue.put(theather, new TreeMap<>());

		return theather;
	}

	public void addMovieToCatalogue(Movie movie) throws NotValidMovieTheatherTypeException {
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
				// TODO
//				movieTheater = this.addMovieTheatherToCinema();
			}
			if (!this.moviesCatalogue.containsKey(movieTheater)) {
				this.moviesCatalogue.put(movieTheater, new TreeMap<>());
				System.out.println("Нова зала беше току що добавена в киното!");
				System.out.println();
			}
			this.showWeeksCalendar();
			LocalDate date = this.inputDate();
			System.out.println(date);
			if (this.moviesCatalogue.get(movieTheater).containsKey(date)) {
				System.out.println("Залата е заета за тази дата");// TODO proverka za svobodni chasove
				// TODO freeHours moved in MovieTheather
			}
			this.moviesCatalogue.get(movieTheater).put(date, new TreeSet<Movie>(this.movieComparator));
			// add movie in TreeSet
			boolean isAdded = this.moviesCatalogue.get(movieTheater).get(date).add(movie);
			System.out.println("dobaven li e:" + isAdded);
		} else {
			System.err.println("null movie or catalogue");
		}
	}

	private void showWeeksCalendar() {
		LocalDate localDate = LocalDate.now();
		System.out.println("Календар за седмицата: ");
		while (localDate.isBefore(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
			System.out.println(
					localDate.getDayOfMonth() + "." + localDate.getMonthValue() + " - " + localDate.getDayOfWeek());
			localDate = localDate.plusDays(1);
		}
	}

<<<<<<< HEAD
=======
//	public void addMovieTheater(MovieTheather mt) {
//		this.theathers.add(mt);
//	}

>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6
	public void addBookedTicket(MovieTheather mt, Ticket ticket) {
		if (mt != null && ticket != null) {
			mt.bookTicketInTheather(ticket);
		}
	}


	private LocalDate inputDate() {
		System.out.print("Моля изберете дата, на която да добавите прожекциите: ");
		boolean retry = false;
		LocalDate date = null;
		while (!retry) {
			try {
				String d = DemoCinema.sc.next();
				String[] n = d.split("\\.");
				Byte day = Byte.parseByte(n[0]);
				Byte month = Byte.parseByte(n[1]);
				date = LocalDate.of(LocalDate.now().getYear(), month, day);
				if ((date.isAfter(LocalDate.now()) || date.equals(LocalDate.now()))
						&& !date.isAfter(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
					retry = true;
					break;
				}
				System.out.println("Невалидна дата моля опитайте отново");
			} catch (InputMismatchException | NumberFormatException e) {
				System.err.println("Невалиден формат на датата! Опитайте отново:");
				retry = false;
			}
		}
		return date;
	}


	public Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> getMoviesCatalogue() {
		return Collections.unmodifiableMap(this.moviesCatalogue);
	}
}
