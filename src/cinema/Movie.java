package cinema;

import java.time.LocalDate;

public class Movie {
	enum movieGenres {
		ÄÐÀÌÀ, ÓÆÀÑÈ, ÊÎÌÅÄÈß, ÀÍÈÌÀÖÈß, ÅÊØÚÍ, ÔÀÍÒÀÑÒÈÊÀ, ÁÈÎÃÐÀÔÈ×ÅÍ, ÏÐÈÊËÞ×ÅÍÑÊÈ, ÐÎÌÀÍÒÈ×ÅÍ, ÊÐÈÌÈÍÀËÅÍ, ÂÎÅÍÅÍ,
		ÍÀÓ×ÍÎ_ÏÎÏÓËßÐÅÍ, ÌÞÇÈÊÚË
	}

   enum movieCategories {
		A, B, C, D
	}

	private static final int MAX_MINUTES = 220;
	private static final int MIN_MINUTES = 30;
	private String name;
	private short minutes;
	private LocalDate premiere;
	private movieGenres genre;
	private movieCategories category;

	public Movie(String name, short minutes, LocalDate premiere, movieGenres g, movieCategories c) {
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

	private boolean isValidMovieCategory(movieCategories category) {
		if (category != null) {
			for (movieCategories cat : movieCategories.values()) {
				if (cat.name().equalsIgnoreCase(category.name())) {
					return true;
				}
			}
		}
		System.err.println("Invalid movie category!");
		return false;
	}

	private boolean isValidMovieGenre(movieGenres genre) {
		if (genre != null) {
			for (movieGenres g : movieGenres.values()) {
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

}
