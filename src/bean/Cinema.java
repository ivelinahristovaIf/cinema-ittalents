package bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import tickets.Ticket;

public class Cinema {
	private int id;
	private String name;
	private String address;
	private List<MovieTheather> movieTheathers;
	private Set<Ticket> tickets;
	// type->date->movie
	private Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> moviesCatalogue;

	public Cinema() {
		super();
	}

	public Cinema(int id, String name, String address, List<MovieTheather> movieTheathers) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.movieTheathers = movieTheathers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void addMovieTheather(bean.MovieTheather movieTheather) {
		if (this.movieTheathers == null) {
			this.movieTheathers = new ArrayList<MovieTheather>();
		}
		this.movieTheathers.add(movieTheather);
	}

	public static Cinema getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addMovieToCatalogue(Movie movie) {
		// TODO Auto-generated method stub
		
	}

	public Set<Ticket> getTickets() {
		return Collections.unmodifiableSet(tickets);
	}

	public Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> getMoviesCatalogue() {
		return Collections.unmodifiableMap(this.moviesCatalogue);
	}

	public void addBookedTicket(bean.MovieTheather mt, Ticket ticket) {
		// TODO Auto-generated method stub
		
	}
}
