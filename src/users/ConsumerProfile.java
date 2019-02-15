package users;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import bean.Movie;
import cinema.DemoCinema;
import helper.MovieGenres;

public class ConsumerProfile {
	private enum Interests {
		music("������"), books("�����"), games("����"), fun("����������"), cars("����������"), science("�����"),
		technology("����������"), history("�������"), family("���������"), health("������"), business("������"),
		socialNetworks("�������� �����"), sport("�����"), trip("��������"), fasion("����"), food("�����"),
		pets("�������_�������"), series("�������"), adrenaline("���������");
		private String name;

		private Interests(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	// TODO more cities
	private static final String[] CITIES = { "�����", "�������", "�����", "����� ������", "������", "������", "������",
			"�����������" };

	Scanner sc = new Scanner(System.in);

	private String firstName;
	private String surName;
	private String lastName;
	private LocalDate birthDate;
	private String city;
	// does not take part in registration
	private String phoneNumber;
	private String adress;
	private String education;

	private Set<MovieGenres> favouriteGenres;
	private Set<String> personalInterests;
	private Set<Movie> favouriteMovies; // TODO add movie to favorites

	public ConsumerProfile() {
		this.setFirstName();
		this.setSurName();
		this.setLastName();
		this.setBirthDate();
		this.setCity();
		this.favouriteGenres = new TreeSet<MovieGenres>();
		this.personalInterests = new TreeSet<String>();
		this.favouriteMovies = new TreeSet<Movie>((m1, m2) -> m1.getName().compareTo(m2.getName()));
	}

	void addFavouriteMovie(Movie movie) {
		this.favouriteMovies.add(movie);// TODO thread
	}

	void addFavouriteGenre() {
		System.out.println("���� �������� ����� ���� �� �������: ");
		for (int i = 1; i < MovieGenres.values().length; i++) {
			System.out.println(i + " - " + MovieGenres.values()[i - 1].getName());
		}
		
//		TODO validate sc number
		MovieGenres favGenre = MovieGenres.values()[DemoCinema.sc.nextInt() - 1];
		boolean isAdded = this.favouriteGenres.add(favGenre);
		if (isAdded) {
			System.out.println(favGenre.getName() + " �� ������� � ������");
		} else {
			System.out.println(favGenre.getName() + " ���� � ������� � ������ �������");
		}
	}

	void addPersonalInterest() {
		System.out.println("���� �������� �������: ");
		for (int index = 1; index <= Interests.values().length; index++) {
			System.out.println(index + " - " + Interests.values()[index - 1].getName());
		}
		Interests interest = Interests.values()[DemoCinema.sc.nextInt() - 1];

		boolean isAdded = this.personalInterests.add(interest.getName());
		if (isAdded) {
			System.out.println(interest.getName() + " �� ������� � ��������");
		} else {
			System.out.println(interest.getName() + " ���� � ������� � ��������");
		}
	}

	private boolean isValidName(String name) {
		if (name != null) {
			if (name.trim().length() >= 3) {
				return true;
			}
		}
		System.out.println("Invalid name");
		return false;
	}

	protected void setPhoneNumber() {
		System.out.print("�������� ��������� �����: ");
		String phoneNumber = sc.next();
		while (!isValidPhoneNumber(phoneNumber)) {
			System.err.print("��������� ������, �������� ���: ");
			phoneNumber = sc.next();
		}
		this.phoneNumber = phoneNumber;
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber != null && phoneNumber.trim().length() == 10 && phoneNumber.startsWith("08");
	}

	protected void setAdress() {
		System.out.print("�������� �����: ");
		String adress = sc.next();
		if (adress != null && adress.trim().length() > 5) {
			this.adress = adress;
		}

	}

	protected void setEducation() {
		System.out.print("��������  �����������: ");
		String education = sc.next();
		if (education != null && education.trim().length() >= 5) {
			this.education = education;
		}
	}

	void setFirstName() {
		System.out.println("���:");
		String firstName = sc.next();
		if (isValidName(firstName)) {
			this.firstName = firstName;
		}
	}

	void setSurName() {
		System.out.println("�������:");
		String surName = sc.next();
		if (isValidName(surName)) {
			this.surName = surName;
		}
	}

	void setLastName() {
		System.out.println("�������");
		String lastName = sc.next();
		if (isValidName(lastName)) {
			this.lastName = lastName;
		}
	}

	void setBirthDate() {
		System.out.println("���� �� �������: day.month.year");
		boolean retry = false;
		LocalDate date = null;
		while (!retry) {
			try {
				String d = DemoCinema.sc.next();
				String[] n = d.split("\\.");
				Byte day = Byte.parseByte(n[0]);
				Byte month = Byte.parseByte(n[1]);
				short year = Short.parseShort(n[2]);
				date = LocalDate.of(year, month, day);
				if (date.isBefore(LocalDate.now().minusYears(12))) {
					retry = true;
					break;
				}
				System.out.println("���������, �� ������ �� ����� ��������� 12 ������!");
			} catch (InputMismatchException e) {
				System.err.println("��������� ������ �� ������! �������� ������:");
				retry = false;
			}
		}
		if (date != null) {
			this.birthDate = date;
		}
	}

	void setCity() {
		System.out.println("�������� ����: ");
		for (int i = 0; i < CITIES.length; i++) {
			System.out.println(i + 1 + " -> " + CITIES[i]);
		}
		String strIndex = sc.next();
		String regex = "[0-9]+";
		while(!(strIndex.matches(regex) && Integer.parseInt(strIndex) >= 0 && Integer.parseInt(strIndex) < CITIES.length)){
			System.out.println("��������� �������. ���� �������� ������");
			strIndex = sc.next();
		}
		
		int index = Integer.parseInt(strIndex)-1;
		this.city = CITIES[index];
	}

	@Override
	public String toString() {
		return "���: " + firstName + ", �������: " + surName + ", �������: " + lastName + ", ������� ����: " + birthDate
				+ ", ���� " + city + ", �������: " + phoneNumber + ", �����: " + adress + ", �����������: " + education;
	}

}
