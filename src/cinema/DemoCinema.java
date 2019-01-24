package cinema;


import tickets.NotValidTicketTypeException;
import tickets.Ticket;
import tickets.Ticket.ticketType;
import users.Admin;
import users.Consumer;

public class DemoCinema {
	public static void menu() {
		System.out.println("Изберете");
	}

	public static void main(String[] args) {
		try {
			Ticket ticket = Ticket.getInstance(ticketType.STANDART_TICKET, "A15", new Cinema());
			System.out.println(ticket);
		} catch (NotValidTicketTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
	
	
	
	
	}	
	
}
