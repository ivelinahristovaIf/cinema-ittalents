package bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import cinema.DemoCinema;
import helper.CalendarHelper;
import helper.NotValidMovieTheatherTypeException;
import tickets.Ticket;

public class Cinema {
	private String name;
	private String address;
	private List<MovieTheather> movieTheathers;
	// type->date->movie
	private Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> moviesCatalogue;

	public Cinema() {
		super();
	}

	public Cinema(String name, String address, List<MovieTheather> movieTheathers) {
		this.name = name;
		this.address = address;
		this.movieTheathers = movieTheathers;
		
		
	}
	public Set<MovieTheather> getAllMovieTheathers(){
		return this.moviesCatalogue.keySet();
	}
	public Set<LocalDate> getAllDatesByTheather(MovieTheather mt){
		if(this.moviesCatalogue.isEmpty()) {
			System.out.println("Няма зали в киното");
			return null;
		}
		return this.moviesCatalogue.get(mt).keySet();
	}
	public void chooseMovieFromCinema() {
		//TODO
	}
	public MovieTheather addMovieTheatherInCinema(MovieTheatherType type) {
		if (this.movieTheathers == null) {
			this.movieTheathers = new ArrayList<MovieTheather>();
		}
		MovieTheather theather = null;
		theather = new MovieTheather(type, this);
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
	public void showAllMoviesByDate(LocalDate date) {
		System.out.println("Всички филми на " + date);
		for (MovieTheather movieTheather : this.moviesCatalogue.keySet()) {
			TreeSet<Movie> movies = this.moviesCatalogue.get(movieTheather).get(date);
			if (movies != null) {
				for (Movie movie : movies) {
					System.out.println(movie.getId() + " " + movie);
				}
			} else {
				System.out.println("Няма филми за този ден в зала " + movieTheather);
			}
		}
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

}
