package users;

import java.time.LocalDate;
import java.util.Scanner;

class ConsumerProfile {
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

	public ConsumerProfile() {
		this.setFirstName();
		this.setSurName();
		this.setLastName();
		this.setBirthDate();
		this.setCity();
	}

	private boolean isValidName(String name) {
		if (name != null) {
			if (name.trim().length() >= 3) {
//				if (name.contains("[а-зА-З]+")) {
					return true;
//				}
			}
		}
		System.out.println("Invalid name");
		return false;
	}

	protected void setPhoneNumber(String phoneNumber) {
		// TODO validate phone number
		this.phoneNumber = phoneNumber;
	}

	protected void setAdress(String adress) {
		// TODO validate adress
		this.adress = adress;
	}

	protected void setEducation(String education) {
		// TODO validate education
		this.education = education;
	}

	void setFirstName() {
		System.out.println("Име:");
		String firstName = sc.next();
		if (isValidName(firstName)) {
			this.firstName = firstName;
		}
	}

	void setSurName() {
		System.out.println("Презиме:");
		String surName = sc.next();
		if (isValidName(surName)) {
			this.surName = surName;
		}
	}
	
	void setLastName() {
		System.out.println("фамилия");
		String lastName = sc.next();
		if (isValidName(lastName)) {
			this.lastName = lastName;
		}
	}

	void setBirthDate() {
		// TODO wont work with 1/1/97!
		System.out.println("Дата на раждане: day/month/year");
		String date = sc.next();
		//20/11/1997

		int y = Integer.parseInt(date.substring(6));
		int m = Integer.parseInt(date.substring(3, 5)); // 1-12 for January-December.
		int d = Integer.parseInt(date.substring(0, 2));

		LocalDate ld = LocalDate.of(y, m, d);
		this.birthDate = ld;
	}

	void setCity() {
		//TODO seting city
		this.city = "Sofia";
		
//		if (city != null && city.trim().length() >= 4) {
//			// TODO choose from enum
//			this.city = city;
//		}
	}

	@Override
	public String toString() {
		return "firstName=" + firstName + ", surName=" + surName + ", lastName=" + lastName
				+ ", birthDate=" + birthDate + ", city=" + city + ", phoneNumber=" + phoneNumber + ", adress=" + adress
				+ ", education=" + education + "]";
	}

}
