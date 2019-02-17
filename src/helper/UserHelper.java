package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.MovieTheatherType;
import bean.Ticket;

public class UserHelper {
	private static final int MIN_PASSWORD_LENGTH = 5;
	public static final String[] CITIES = { "София", "Пловдив", "Варна", "Стара Загора", "Плевен", "Смолян", "Бургас",
			"Благоевград" };
	private static UserHelper instance = null;

	private UserHelper() {
		super();
	}

	public static UserHelper getInstance() {
		if (instance == null) {
			instance = new UserHelper();
		}
		return instance;
	}

	public void buyTicket(MovieTheatherType mt, String type, int count, String seat) throws NotValidTicketTypeException {
		// TODO get movieTheather by movieTheather type
		Ticket ticket = Ticket.getInstance(type, seat, mt, null);
//		boolean isAlreadyReserved = false;
		if (mt != null) {
//			for (Ticket t : mt.getBookedTickets()) {
//				if (t != null && ticket != null && ticket.isTicketsEquals(t)) {
//					isAlreadyReserved = true;
//					break;
//				}
//			}
//			if (!isAlreadyReserved) {
//				mt.bookTicketInTheather(ticket);
				ticket.reservedTicket();
				System.out.println("Поздравления вие запазихте билет");
//			} else {
//				System.out.println("Съжаляваме този билет вече е бил запазен");
//			}
		}else {
		System.out.println("Няма такава зала!");
		}
	}

	public boolean isValidPassword(String password) throws InvalidPersonException {
		if (password != null && password.trim().length() >= MIN_PASSWORD_LENGTH) {
			return true;
		}
		System.err.println("Password must be at least with " + MIN_PASSWORD_LENGTH + " characters!");
		throw new InvalidPersonException("Невалидна парола!");
	}

	public boolean isValidEmail(String email) throws InvalidPersonException {
		Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		Matcher regMatcher = regexPattern.matcher(email);
		if (regMatcher.matches()) {
			return true;
		}
		System.err.println("Not suitable email adress!");
		throw new InvalidPersonException("Невалиден e-mail!");
	}
}
