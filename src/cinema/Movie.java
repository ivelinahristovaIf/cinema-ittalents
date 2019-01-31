package cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Movie {
	private static final int MAX_MINUTES = 220;
	private static final int MIN_MINUTES = 30;
	private int id;
	private String name;
	private short minutes;
	private LocalDate premiere;
	private Cinema.movieGenres genre;
	private Cinema.movieCategories category;
	// TODO private LocalDateTime time;
	
	public Movie(int id, String name, short minutes, LocalDate premiere, Cinema.movieGenres g,
			Cinema.movieCategories c) {
		//TODO id
		this.id = id;
		if (name != null && name.trim().length() >= 2) {
			this.name = name;
		}
		if (minutes >= MIN_MINUTES && minutes <= MAX_MINUTES) {
			this.minutes = minutes;
		}
		if (isValidPremiereDate(premiere)) {
			this.premiere = premiere;
		}
		if (isValidMovieGenre(g)) {
			this.genre = g;
		}
		if (isValidMovieCategory(c)) {
			this.category = c;
		}
	}

	private boolean isValidMovieCategory(Cinema.movieCategories category) {
		if (category != null) {
			for (Cinema.movieCategories cat : Cinema.movieCategories.values()) {
				if (cat.name().equalsIgnoreCase(category.name())) {
					return true;
				}
			}
		}
		System.err.println("Invalid movie category!");
		return false;
	}

	private boolean isValidMovieGenre(Cinema.movieGenres genre) {
		if (genre != null) {
			for (Cinema.movieGenres g : Cinema.movieGenres.values()) {
				if (g.name().equalsIgnoreCase(genre.name())) {
					return true;
				}
			}
		}
		System.err.println("Invalid movie genre!");
		return false;
	}

	private boolean isValidPremiereDate(LocalDate premiere2) {
		// TODO validate premiere
		return true;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", minutes=" + minutes + ", premiere=" + premiere + ", genre=" + genre.name()
				+ ", category=" + category.name() + "]";
	}

	public int getId() {
		return this.id;
	}

}
