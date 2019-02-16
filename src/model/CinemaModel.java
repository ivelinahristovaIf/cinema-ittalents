package model;

import bean.Cinema;
import bean.MovieTheather;

public class CinemaModel {

	public static Cinema fill(Cinema cinema, Cinema savedCinema) {
		if (cinema == null) {//if no cinema into file
			cinema = new Cinema();
		}
		cinema.setId(savedCinema.getId());
		cinema.setName(savedCinema.getName());
		cinema.setAddress(savedCinema.getAddress());
//		cinema.addMovieTheather(new MovieTheather());//TODO not empty movie theather
		
		return cinema;
	}

}
