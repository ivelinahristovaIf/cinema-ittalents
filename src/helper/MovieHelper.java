package helper;

import java.time.LocalDate;

public class MovieHelper {
	public static final String[] MOVIE_GENRE = { "дпюлю", "сфюях", "йнледхъ", "юмхлюжхъ", "ейьзм", "тюмрюярхйю",
			"ахнцпютхвем", "опхйкчвемяйх", "пнлюмрхвем", "йпхлхмюкем", "бнемем", "мюсвмн оноскъпем", "лчгхйзк" };
	public static final String[] MOVIE_CATEGORIES = { "A", "B", "C", "D" };
	private static final long NUMBER_OF_DAYS_FROM_TODAY = 1;
	private static final long NUMBER_OF_DAYS_BEFORE_TODAY = 30;
	private static MovieHelper instance = null;

	public static MovieHelper getInstance() {
		if (instance == null) {
			instance = new MovieHelper();
		}
		return instance;
	}
	public boolean isValidPremiereDate(LocalDate premiere) {
		return premiere.isBefore(LocalDate.now().plusDays(NUMBER_OF_DAYS_FROM_TODAY))
				&& premiere.isAfter(LocalDate.now().minusDays(NUMBER_OF_DAYS_BEFORE_TODAY));
	}
	

	public boolean isValidMovieCategory(String category) {
		if (category != null) {
			for (String cat : MOVIE_CATEGORIES) {
				if (cat.equalsIgnoreCase(category)) {
					return true;
				}
			}
		}
		System.err.println("Invalid movie category!");
		return false;
	}
	public boolean isValidMovieGenre(String genre) {
		for (String string : MOVIE_GENRE) {
			if(string.equalsIgnoreCase(genre)) {
				return true;
			}
		}
		return false;
	}

}
