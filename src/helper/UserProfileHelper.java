package helper;

import cinema.DemoCinema;

public class UserProfileHelper {
	public static final String[] INTEREST = { "лсгхйю", "ймхцх", "хцпх", "гюаюбкемхъ", "юбрнлнахкх", "мюсйю",
			"реумнкнцхх", "хярнпхъ", "яелеиярбн", "гдпюбе", "ахгмея", "янжхюкмх лпефх", "яонпр", "озрсбюме", "лндю",
			"упюмю", "днлюьмх_кчахлжх", "яепхюкх", "юдпемюкхм" };
	

	private static UserProfileHelper instance = null;

	public static UserProfileHelper getInstance() {
		if (instance == null) {
			instance = new UserProfileHelper();
		}
		return instance;
	}

	public String addFavouriteGenre() {
		System.out.println("лНКЪ ХГАЕПЕРЕ КЧАХЛ ФЮМП НР ЯОХЯЗЙЮ: ");
		for (int i = 1; i < MovieHelper.MOVIE_GENRE.length; i++) {
			System.out.println(i + " - " + MovieHelper.MOVIE_GENRE[i - 1]);
		}
		String favGenre = MovieHelper.MOVIE_GENRE[DemoCinema.sc.nextInt() - 1];
		return favGenre;
	}
	
	public boolean isValidInterest(String interest) {
		for (String string : INTEREST) {
			if(string.equalsIgnoreCase(interest)) {
				return true;
			}
		}
		System.out.println("мЕБЮКХДЕМ КХВЕМ ХМРЕПЕЯ!");
		return false;
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber != null && phoneNumber.trim().length() == 10 && phoneNumber.startsWith("08");
	}
}
