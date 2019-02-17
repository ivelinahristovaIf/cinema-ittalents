package bean;

import java.util.HashSet;
import java.util.Set;

import helper.MovieHelper;
import helper.UserProfileHelper;

public class UserProfile {
	private String phoneNumber;
	private String adress;
	private String education;

	private Set<String> favouriteGenres;
	private Set<String> personalInterests;
	private Set<Movie> favouriteMovies; // TODO add movie to favorites

	public UserProfile() {
		super();
		this.favouriteGenres = new HashSet<String>();
		this.personalInterests = new HashSet<String>();
		this.favouriteMovies = new HashSet<Movie>();
	}

	public UserProfile(String phoneNumber, String adress, String education) {
		super();
		if (UserProfileHelper.getInstance().isValidPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}
		if (adress != null && adress.trim().length() > 5) {
			this.adress = adress;
		}
		this.education = education;
		this.favouriteGenres = new HashSet<String>();
		this.personalInterests = new HashSet<String>();
		this.favouriteMovies = new HashSet<Movie>();

	}

	public void addFavouriteGenre(String genre) {
//		String genre = UserProfileHelper.getInstance().addFavouriteGenre();
		if (MovieHelper.getInstance().isValidMovieGenre(genre)) {
			boolean isAdded = this.favouriteGenres.add(genre);
			if (isAdded) {
				System.out.println(genre + " бе добавен в любими");
			} else {
				System.out.println(genre + " вече е добавен в любими жанрове");
			}
		}
	}

	public void addPersonalInterest(String interest) {
//		String interest = UserProfileHelper.getInstance().addPersonalInterest();
		if (UserProfileHelper.getInstance().isValidInterest(interest)) {
			boolean isAdded = this.personalInterests.add(interest);
			if (isAdded) {
				System.out.println(interest + " бе добавен в интереси");
			} else {
				System.out.println(interest + " вече е добавен в интереси");
			}
		}
	}

	public void addFavouriteMovie(Movie movie) {
		this.favouriteMovies.add(movie);// TODO thread
		System.out.println("Филма бе добавен в любими");
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

}
