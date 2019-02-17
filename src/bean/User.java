package bean;

import java.time.LocalDate;

import helper.InvalidPersonException;
import helper.UserHelper;

public class User implements ILogger {

	private int id;
	private int type;
	private static int nextId = 1;
	private String email;
	private String password;
	private String firstname;
	private String surname;
	private String lastname;
	private LocalDate birthDate;
	private String city;
	private UserProfile profile;

	private Cinema cinema;

	public User() {
		super();
		this.setType();
		this.profile = new UserProfile();
	}

	public User(String email, String password, String firstname, String surname, String lastname, LocalDate birthDate,
			String city) throws InvalidPersonException {
		super();
		this.setType();
		this.id = nextId++;
		if(UserHelper.getInstance().isValidEmail(email)) {
		this.email = email;
		}
		if(UserHelper.getInstance().isValidPassword(password)) {
		this.password = password;
		}
		this.firstname = firstname;
		this.surname = surname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.city = city;
		this.profile = new UserProfile();

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surName) {
		this.surname = surName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastName) {
		this.lastname = lastName;
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
	public String toString() {
		return "User:  email: " + email + ", password: " + password + ", firstname: " + firstname + ", surname: " + surname
				+ ", lastname: " + lastname + ", birth date: " + birthDate + ", city: " + city;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void setType() {
		this.type = 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
