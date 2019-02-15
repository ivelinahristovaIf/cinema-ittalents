package users;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Cinema;
import bean.MovieTheather;
import tickets.NotValidTicketTypeException;
import tickets.Ticket;

public class Consumer {
	private static final int MIN_PASSWORD_LENGTH = 8;
//	Scanner sc = new Scanner(System.in);
//	private final int id;
	private static int nextId = 1;
	private String email;
	private String password;
//	private Cinema personalCinema; // the cinema he choosed; composition
//	private Set<Cinema> allCinemas;// choose from another cinema; agregation
	private double money;

	private ConsumerProfile myProfile;

	public Consumer() {
//		this.id = nextId++;
//		System.out.println("���� �������� ������ �����: ");
//		this.setEmail();
//		try {
//			this.setPassword();
//		} catch (NoSuchAlgorithmException e) {
//			System.out.println("NoSuchAlgorithmException");
//			e.printStackTrace();
//		}

		// TODO personal cinema
//		if (isValidCinema(personalCinema)) {
//			this.personalCinema = personalCinema;
//		}
		this.myProfile = new ConsumerProfile();
		// TODO hashCode and equals
<<<<<<< HEAD
//		this.allCinemas = new HashSet<Cinema>();

	}

//	public void showConsumerMenu() {
//		System.out.println("����� ����� � ��������������� ����!\n����, �������� �����: ");
//		System.out.println("1 -> ��������� �� ����� �����...");
//		System.out.println("2 -> �������� ���� �� �������� �� ����...");
//		System.out.println("3 -> ����� �� ������...");
//		System.out.println("4 -> �������� �� �������� � ������ �������...");
//		System.out.println("5 -> ������� ��� �������� ���� =>");
//		System.out.println("0 -> �����...");
//		try {
//			int option = sc.nextInt();
//			switch (option) {
//			case 0:
//				System.exit(0);
//				break;
//			case 1:
//				this.setUpMyProfile();
//				break;
//			case 2:
//				this.chooseMovieFromAllToday();
//				break;
//			case 3:
//				this.changePassword();
//				break;
//			case 4:
//				this.setInterests();
//				break;
//			case 5:
//				DemoCinema.menu();
//				break;
//			}
//			System.out.println("1 -> �� �� ���������� ��������...");
//			System.out.println("2 -> �� �� �� ��������...");
//			System.out.println("0 -> �����...");
//
//			int next = sc.nextInt();
//			switch (next) {
//			case 0:
//				System.exit(0);
//				break;
//			case 1:
//				showConsumerMenu();
//				break;
//			case 2:
//				DemoCinema.menu();
//				break;
//			}
//		} catch (InputMismatchException e) {
=======
		this.allCinemas = new HashSet<Cinema>();
		this.money = 1000;

	}

	public void showConsumerMenu() {
		System.out.println("����� ����� � ��������������� ����!\n����, �������� �����: ");
		System.out.println("1 -> ��������� �� ����� �����...");
		System.out.println("2 -> ����� �� ������...");
		System.out.println("3 -> �������� �� �������� � ������ �������...");
		System.out.println("4 -> ������� ��� �������� ���� =>");
		//TODO System.out.println("5 -> ��������� �� �����...");
		System.out.println("0 -> �����...");
		try {
			String strOption = sc.next();
			String reg = "[0-9]+";
			while(!(strOption.matches(reg) && Integer.parseInt(strOption) >= 0 && Integer.parseInt(strOption) <= 4)) {
				System.out.println("��������� �����. ���� �������� ������");
				strOption = sc.next();
			}
			int option = Integer.parseInt(strOption);
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				this.setUpMyProfile();
				break;
			case 2:
				this.changePassword();
				break;
			case 3:
				this.setInterests();
				break;
			case 4:
				DemoCinema.menu();
>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6
//			showConsumerMenu();
//			System.out.println("Wrong command!");
//			System.out.println(e.getMessage());
//		}
//	}
	
//	private void chooseMovieFromAllToday() {
//		Cinema.getInstance().showAllMoviesByDate(LocalDate.now());
//		//TODO choose movie
//	}

	private void setUpMyProfile() {
		this.myProfile.setPhoneNumber();
		this.myProfile.setAdress();
		this.myProfile.setEducation();
	}

//	private void changePassword() {
//		System.out.println("����� �� ������...");
//		System.out.println("�������� ������� ������:");
//		String oldPass = sc.next();
//		try {
//			while (!oldPass.equals(this.password)) {
//				System.err.println("������ ������! �������� ���: ");
//				oldPass = sc.next();
//			}
//			System.out.println("�������� ���� ������...");
//			this.setPassword();
//			System.out.println("�������� � ������� �������!");
//		
//	}

	private void setInterests() {
		// TODO choose multiple
		this.myProfile.addFavouriteGenre();
		this.myProfile.addPersonalInterest();
	}

//	private void setPassword() {
//		System.out.println("������:");
//		String password = sc.next();
//		while (!isValidPassword(password)) {
//			System.out.println("Try again: ");
//			password = sc.next();
//		}
//		this.password =password;
//	}

//	private void setEmail() {
//		System.out.println("E-mail:");
//		String email = sc.next();
//		while (!isValidEmail(email)) {
//			System.out.println("Try again: ");
//			email = sc.next();
//		}
//		this.email = email;
//
//	}

//	private boolean isValidCinema(Cinema personalCinema) {
//		// TODO if set contains personalCinema
//		return true;
//	}
	public void showProgramForToday() {
		System.out.println("----------������ ��������----------");
		
	}

	public void buyTicket(Cinema cinema, MovieTheather mt) throws NotValidTicketTypeException {
		Scanner sc = new Scanner(System.in);

		System.out.println("����� ������ ������ �� ������: ");
		int countTickets = sc.nextInt();

		while (countTickets > 0) {
			System.out.println("����� ����� ������ �� ������: ");
			System.out.println("�� ������ ����� ��������� 1");
			System.out.println("�� ���������� ����� ��������� 2");
			System.out.println("�� ����� �� �������� ��������� 3");
			System.out.println("�� ���������� ����� ��������� 4");

			int ticketType = sc.nextInt();
			System.out.println("�������� �����");
			mt.showSeatsInTheathre();
			String seat = sc.next();
			Ticket ticket = null;
			switch (ticketType) {
			case 1:
				ticket = Ticket.getInstance(Ticket.ticketType.CHILD_TICKET, seat, cinema);
				break;

			case 2:
				ticket = Ticket.getInstance(Ticket.ticketType.STANDART_TICKET, seat, cinema);
				break;

			case 3:
				ticket = Ticket.getInstance(Ticket.ticketType.INVALID_TICKET, seat, cinema);
				break;

			case 4:
				ticket = Ticket.getInstance(Ticket.ticketType.STUDENT_TICKET, seat, cinema);
				break;
			default:
				break;
			}
			if (this.money > ticket.getPrice()) {
				boolean isAlreadyReserved = false;
				for (Ticket t : mt.getBookedTickets()) {
					if (t != null && ticket != null && ticket.isTicketsEquals(t)) {
						isAlreadyReserved = true;
						break;
					}
				}
				if (!isAlreadyReserved) {
					mt.bookTicketInTheather(ticket);
					ticket.reservedTicket();
					this.money -= ticket.getPrice();
					System.out.println("������������ ��� ��������� �����");
					countTickets--;
				} else {
					System.out.println("���������� ���� ����� ���� � ��� �������");
					continue;
				}
			} else {
				System.out.println("������ ���������� ����");
				break;
			}
		}
	}

	private boolean isValidPassword(String password) {
		if (password != null && password.trim().length() >= MIN_PASSWORD_LENGTH) {
			return true;
		}
		System.err.println("Password must be at least with " + MIN_PASSWORD_LENGTH + " characters!");
		return false;
	}

	private boolean isValidEmail(String email) {
		Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		Matcher regMatcher = regexPattern.matcher(email);
		if (regMatcher.matches()) {
			return true;
		}
		System.err.println("Not suitable email adress!");
		return false;
	}

//	@Override
//	public String toString() {
//		return "Consumer [id=" + id + ", email: " + email + ", password: " + password + ", ������������ ����: "
//				+ personalCinema + "," + myProfile + "]";
//	}

	public ConsumerProfile getMyProfile() {
		return myProfile;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
