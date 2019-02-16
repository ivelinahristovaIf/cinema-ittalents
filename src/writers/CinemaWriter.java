package writers;

import java.io.IOException;

import bean.Cinema;

public class CinemaWriter {
	
	private static CinemaWriter instance = null;
	
	public static Cinema fill(Cinema cinema, Cinema savedCinema) {
		cinema = new Cinema();
		cinema.setId(savedCinema.getId());
		cinema.setName(savedCinema.getAddress());
		cinema.setAddress(savedCinema.getAddress());
//		cinema.setMovieTheathers(savedCinema.getMovieTheathers());//TODO movie theathers set in saved
		return cinema;
	}

	
	private CinemaWriter() {
	
	}
	
	
	public static CinemaWriter getInstance() {
		if (instance == null) {
			instance = new CinemaWriter();
		}
		return instance;
	}
	
	
	
}
