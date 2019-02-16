package bean;

import java.util.HashSet;
import java.util.Set;

import helper.MovieGenres;
import helper.UserProfileHelper;
import helper.UserProfileHelper.Interests;

public class UserProfile {
	private String phoneNumber;
	private String adress;
	private String education;

	private Set<String> favouriteGenres;
	private Set<String> personalInterests;
	private Set<Movie> favouriteMovies; // TODO add movie to favorites
	
	public UserProfile() {
		super();
	}

	public UserProfile(String phoneNumber, String adress, String education) {
		super();
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.education = education;
		this.favouriteGenres = new HashSet<String>();
		this.personalInterests = new HashSet<String>();
		this.favouriteMovies = new HashSet<Movie>();
		
	}
	public void addFavouriteGenre() {
		String genre = UserProfileHelper.getInstance().addFavouriteGenre();
		boolean isAdded = this.favouriteGenres.add(genre);
		if (isAdded) {
			System.out.println(genre + " бе добавен в любими");
		} else {
			System.out.println(genre + " вече е добавен в любими жанрове");
		}
	}
	public void addPersonalInterest(String interes) {
		Interests interest = UserProfileHelper.getInstance().addPersonalInterest();
		boolean isAdded = this.personalInterests.add(interest.getName());
		if (isAdded) {
			System.out.println(interest.getName() + " бе добавен в интереси");
		} else {
			System.out.println(interest.getName() + " вече е добавен в интереси");
		}
	}
	
	void addFavouriteMovie(Movie movie) {
		this.favouriteMovies.add(movie);// TODO thread
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
