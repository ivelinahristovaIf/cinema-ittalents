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
		String adress = sc.nextLine();
		// TODO adress validation
		if (adress != null && adress.trim().length() > 5) {
			this.adress = adress;
		}
		
	}

	protected void setEducation() {
		// TODO validate education
		System.out.print("\n��������  �����������: ");
		String education = sc.nextLine();
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
		// TODO wont work with 1/1/97!
		System.out.println("���� �� �������: day/month/year");
		String date = sc.next();
		// 20/11/1997

		int y = Integer.parseInt(date.substring(6));
		int m = Integer.parseInt(date.substring(3, 5)); // 1-12 for January-December.
		int d = Integer.parseInt(date.substring(0, 2));

		LocalDate ld = LocalDate.of(y, m, d);
		this.birthDate = ld;
	}

	void setCity() {
		// TODO seting city
		this.city = "Sofia";

//		if (city != null && city.trim().length() >= 4) {
//			// TODO choose from enum
//			this.city = city;
//		}
	}

	@Override
	public String toString() {
		return "firstName=" + firstName + ", surName=" + surName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", city=" + city + ", phoneNumber=" + phoneNumber + ", adress=" + adress + ", education=" + education
				+ "]";
	}

}
