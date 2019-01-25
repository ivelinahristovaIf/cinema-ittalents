package users;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import cinema.Cinema;
import cinema.Movie;

class ConsumerProfile {

	private enum interests {
		������, �����, ����, ����������, ����������, �����, ����������, �������, ���������, ������, ������,
		��������_�����, �����, ��������, ����, �����, �������_�������, �������, ���������
	}

	// TODO more cities
	private static final String[] CITIES = { "�����", "�������", "�����", "����� ������", "������", "������" };

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

	private Set<String> favouriteGenres;
	private Set<String> personalInterests;
	private Set<Movie> favouriteMovies;

	public ConsumerProfile() {
		this.setFirstName();
		this.setSurName();
		this.setLastName();
		this.setBirthDate();
		this.setCity();
		this.favouriteGenres = new TreeSet<String>();
		this.personalInterests = new TreeSet<String>();
		// TODO Movie comparator
		this.favouriteMovies = new TreeSet<Movie>((m1,m2)->m1.getId()-m2.getId());
	}

	void addFavouriteGenre() {
		System.out.println("���� �������� ����� ���� �� �������: ");
		for (Cinema.movieGenres genre : Cinema.movieGenres.values()) {
			System.out.println(genre);
		}
		String genre = sc.next();
		boolean isValid = false;
		for (Cinema.movieGenres favouriteGenre : Cinema.movieGenres.values()) {
			if (favouriteGenre.name().equalsIgnoreCase(genre)) {
				isValid = true;
				break;
			}
		}
		if (isValid) {
			boolean isAdded = this.favouriteGenres.add(genre.toUpperCase());
			if (isAdded) {
				System.out.println(genre.toUpperCase() + " �� ������� � ������");
			} else {
				System.out.println(genre.toUpperCase() + " ���� � ������� � ������ �������");
			}
		} else {
			System.out.println("��������� ����!");
		}
	}

	void addPersonalInterest() {
		System.out.println("���� �������� �������: ");
		for (interests interest : interests.values()) {
			System.out.println(interest);
		}
		String interest = sc.next();
		boolean isValid = false;
		for (interests personalInterest : interests.values()) {
			if (personalInterest.name().equalsIgnoreCase(interest)) {
				isValid = true;
				break;
			}
		}
		if (isValid) {
			boolean isAdded = this.personalInterests.add(interest.toUpperCase());
			if (isAdded) {
				System.out.println(interest.toUpperCase() + " �� ������� � ��������");
			} else {
				System.out.println(interest.toUpperCase() + " ���� � ������� � ��������");
			}
		} else {
			System.out.println("��������� �������!");
		}
	}

	private boolean isValidName(String name) {
		if (name != null) {
			if (name.trim().length() >= 3) {
//				if (name.contains("[�-��-�]+")) {
				return true;
//				}
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
		// TODO adress validation
		if (adress != null && adress.trim().length() > 5) {
			this.adress = adress;
		}

	}

	protected void setEducation() {
		// TODO validate education
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
		System.out.println("���� �� �������: day/month/year");
		String date = sc.next();
		// TODO regex
		String[] splitDate = date.split("/");
		if (splitDate.length == 3) {
			try {
				int y = Integer.parseInt(splitDate[2].length() == 4 ? splitDate[2] : "19" + splitDate[2]);
				int m = Integer.parseInt(splitDate[1].length() == 2 ? splitDate[1] : "0" + splitDate[1]);
				int d = Integer.parseInt(splitDate[0].length() == 2 ? splitDate[0] : "0" + splitDate[0]);
				LocalDate ld = LocalDate.of(y, m, d);
				this.birthDate = ld;
			} catch (Exception e) {
				System.err.println("Invalid date format!");
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("Invalid date!");
		}
	}

	void setCity() {
		System.out.println("�������� ����: ");
		for (int i = 0; i < CITIES.length; i++) {
			System.out.println(i + 1 + " -> " + CITIES[i]);
		}
		int index = sc.nextInt();
		this.city = CITIES[index];
	}

	@Override
	public String toString() {
		return "���: " + firstName + ", �������: " + surName + ", �������: " + lastName + ", ������� ����: " + birthDate
				+ ", ���� " + city + ", �������: " + phoneNumber + ", �����: " + adress + ", �����������: " + education;
	}

}
