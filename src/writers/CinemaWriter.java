package writers;

import bean.Cinema;

public class CinemaWriter {
	
	public static Cinema fill(Cinema cinema, Cinema savedCinema) {
		cinema = new Cinema();
		cinema.setId(savedCinema.getId());
		cinema.setName(savedCinema.getAddress());
		cinema.setAddress(savedCinema.getAddress());
		cinema.setMovieTheathers(savedCinema.getMovieTheathers());//TODO movie theathers set in saved
		return cinema;
	}
	public static Cinema getCinema(int id) {
		return null;
		//TODO get user -> get cinema
	}
}
