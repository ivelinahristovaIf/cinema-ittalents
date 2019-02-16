package helper;

import cinema.DemoCinema;

public class UserProfileHelper {
	public enum Interests {
		music("ÌÓÇÈÊÀ"), books("ÊÍÈÃÈ"), games("ÈÃĞÈ"), fun("ÇÀÁÀÂËÅÍÈß"), cars("ÀÂÒÎÌÎÁÈËÈ"), science("ÍÀÓÊÀ"),
		technology("ÒÅÕÍÎËÎÃÈÈ"), history("ÈÑÒÎĞÈß"), family("ÑÅÌÅÉÑÒÂÎ"), health("ÇÄĞÀÂÅ"), business("ÁÈÇÍÅÑ"),
		socialNetworks("ÑÎÖÈÀËÍÈ ÌĞÅÆÈ"), sport("ÑÏÎĞÒ"), trip("ÏÚÒÓÂÀÍÅ"), fasion("ÌÎÄÀ"), food("ÕĞÀÍÀ"),
		pets("ÄÎÌÀØÍÈ_ËŞÁÈÌÖÈ"), series("ÑÅĞÈÀËÈ"), adrenaline("ÀÄĞÅÍÀËÈÍ");
		private String name;

		private Interests(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	private static UserProfileHelper instance = null;
	
	public static UserProfileHelper getInstance() {
		if(instance == null) {
			instance = new UserProfileHelper();
		}
		return instance;
	}
	public String addFavouriteGenre() {
		System.out.println("Ìîëÿ èçáåğåòå ëşáèì æàíğ îò ñïèñúêà: ");
		for (int i = 1; i < MovieHelper.MOVIE_GENRE.length; i++) {
			System.out.println(i + " - " + MovieHelper.MOVIE_GENRE[i - 1]);
		}
		String favGenre = MovieHelper.MOVIE_GENRE[DemoCinema.sc.nextInt() - 1];
		return favGenre;
	}
	public Interests addPersonalInterest() {
		System.out.println("Ìîëÿ èçáåğåòå èíòåğåñ: ");
		for (int index = 1; index <= Interests.values().length; index++) {
			System.out.println(index + " - " + Interests.values()[index - 1].getName());
		}
		Interests interest = Interests.values()[DemoCinema.sc.nextInt() - 1];
		return interest;
	}
}
