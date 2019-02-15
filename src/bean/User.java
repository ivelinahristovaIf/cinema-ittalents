package bean;

import java.time.LocalDate;

import cinema.Cinema;

public class User {
	public static final int CONSUMER = 1;
	public static final int ADMIN = 2;

	private int id;
	private static int nextId = 1;
	private String email;
	private String password;
	private String firstName;
	private String surName;
	private String lastName;
	private LocalDate birthDate;
	private String city;

	private Cinema cinema;

	private int type;// CONSUMER ADMIN

	public User() {
		super();
	}

	public User(String email, String password, String firstName, String surName, String lastName, LocalDate birthDate,
			String city, int type) {
		super();
		this.id = nextId++;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.surName = surName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.city = city;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cinema getCinema() {
		return this.cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public int getType() {
		// ADMIN USER
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surName;
	}

	public void setSurname(String surName) {
		this.surName = surName;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", surName=" + surName
				+ ", lastName=" + lastName + ", birthDate=" + birthDate + ", city=" + city + "]";
	}

	
}
