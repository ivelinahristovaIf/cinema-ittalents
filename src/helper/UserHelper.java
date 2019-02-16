package helper;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.MovieTheather;
import tickets.NotValidTicketTypeException;
import tickets.Ticket;

public class UserHelper {
	private static final int MIN_PASSWORD_LENGTH = 5;
	public static final String[] CITIES = { "�����", "�������", "�����", "����� ������", "������", "������", "������",
	"�����������" };
	private static UserHelper instance = null;
	
	private UserHelper() {
		super();
	}
	public static UserHelper getInstance() {
		if(instance==null) {
			instance = new UserHelper();
		}
		return instance;
	}

	public void buyTicket(MovieTheather mt) throws NotValidTicketTypeException {
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
				ticket = Ticket.getInstance(Ticket.ticketType.CHILD_TICKET, seat, mt);
				break;

			case 2:
				ticket = Ticket.getInstance(Ticket.ticketType.STANDART_TICKET, seat, mt);
				break;

			case 3:
				ticket = Ticket.getInstance(Ticket.ticketType.INVALID_TICKET, seat, mt);
				break;

			case 4:
				ticket = Ticket.getInstance(Ticket.ticketType.STUDENT_TICKET, seat, mt);
				break;
			default:
				break;
			}
//			if (this.user.g > ticket.getPrice()) {
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

					System.out.println("������������ ��� ��������� �����");
					countTickets--;
				} else {
					System.out.println("���������� ���� ����� ���� � ��� �������");
					continue;
				}
				sc.close();
		}
	}
	public void showProgramForToday() {
		System.out.println("----------������ ��������----------");

	}
	public boolean isValidPassword(String password) {
		if (password != null && password.trim().length() >= MIN_PASSWORD_LENGTH) {
			return true;
		}
		System.err.println("Password must be at least with " + MIN_PASSWORD_LENGTH + " characters!");
		return false;
	}

	public boolean isValidEmail(String email) {
		Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		Matcher regMatcher = regexPattern.matcher(email);
		if (regMatcher.matches()) {
			return true;
		}
		System.err.println("Not suitable email adress!");
		return false;
	}
}
