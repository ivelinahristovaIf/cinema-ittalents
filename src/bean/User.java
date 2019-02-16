package bean;

import java.time.LocalDate;

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
	}

	public User(String email, String password, String firstname, String surname, String lastname, LocalDate birthDate,
			String city) {
		super();
		this.setType();
		this.id = nextId++;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.city = city;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		return true;
	}

	@Override
	public String toString() {
		return "User "+type+" [email=" + email + ", password=" + password + ", firstName=" + firstname + ", surName=" + surname
				+ ", lastName=" + lastname + ", birthDate=" + birthDate + ", city=" + city + "]";
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

}
