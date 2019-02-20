package bean;

import java.time.LocalDate;

import helper.InvalidPersonException;
import helper.UserHelper;

public class User{
	private int id;
	private boolean isAdmin;
	private static int nextId = 1;
	private String email;
	private String password;
	private String firstname;
	private String surname;
	private String lastname;
	private LocalDate birthDate;
	private String city;
	private UserProfile profile;


	public User(boolean isAdmin) {
		this.isAdmin = isAdmin;
		this.profile = new UserProfile();
	}

	public User(boolean isAdmin, String email, String password, String firstname, String surname, String lastname, LocalDate birthDate,
			String city) throws InvalidPersonException {
		this.isAdmin = isAdmin;
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

	public void setPassword(String password) throws InvalidPersonException {
		if(UserHelper.getInstance().isValidPassword(password)) {
		this.password = password;
		}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + (isAdmin ? 1231 : 1237);
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
		if (isAdmin != other.isAdmin)
			return false;
		return true;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
