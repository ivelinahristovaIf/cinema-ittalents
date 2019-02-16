package helper;

import java.util.Scanner;

import bean.Cinema;
import bean.MovieTheather;
import tickets.NotValidTicketTypeException;
import tickets.Ticket;

public class UserHelper {
	private static final int MIN_PASSWORD_LENGTH = 5;
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

		System.out.println("Колко билета искате да купите: ");
		int countTickets = sc.nextInt();

		while (countTickets > 0) {
			System.out.println("Какъв билет искате да купите: ");
			System.out.println("За детски билет натиснете 1");
			System.out.println("За стандартен билет натиснете 2");
			System.out.println("За билет за инвалиди натиснете 3");
			System.out.println("За студентски билет натиснете 4");

			int ticketType = sc.nextInt();
			System.out.println("Изберете място");
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

					System.out.println("Поздравления вие запазихте билет");
					countTickets--;
				} else {
					System.out.println("Съжаляваме този билет вече е бил запазен");
					continue;
				}
				sc.close();
		}
	}
	public boolean isValidPassword(String password) {
		if (password != null && password.trim().length() >= MIN_PASSWORD_LENGTH) {
			return true;
		}
		System.err.println("Password must be at least with " + MIN_PASSWORD_LENGTH + " characters!");
		return false;
	}


}
