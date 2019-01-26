package cinema;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


import tickets.NotValidTicketTypeException;
import tickets.Ticket;
import tickets.Ticket.ticketType;
import users.Consumer;

public class Cinema {
	public enum movieGenres {
		ÄĞÀÌÀ, ÓÆÀÑÈ, ÊÎÌÅÄÈß, ÀÍÈÌÀÖÈß, ÅÊØÚÍ, ÔÀÍÒÀÑÒÈÊÀ, ÁÈÎÃĞÀÔÈ×ÅÍ, ÏĞÈÊËŞ×ÅÍÑÊÈ, ĞÎÌÀÍÒÈ×ÅÍ, ÊĞÈÌÈÍÀËÅÍ, ÂÎÅÍÅÍ,
		ÍÀÓ×ÍÎ_ÏÎÏÓËßĞÅÍ, ÌŞÇÈÊÚË
	}

	public enum movieCategories {
		A, B, C, D
	}

	private static final char MAX_ROWS_IN_CINEMA = 'M';
	private static final int MAX_COLS_IN_ONE_ROW = 15;
	public static Set<Consumer> consumers; // TO DO private
	//TODO remove tickets
	private Set<Ticket> tickets;
	private Set<MovieTheather> theathers;

	public Cinema() {
		consumers = new HashSet<Consumer>();
		//TODO comparator
		this.theathers = new TreeSet<MovieTheather>((mt1,mt2) -> - mt1.getId() - mt2.getId());
		this.tickets = new HashSet<>();
	}
			
	public void addMovieTheater(MovieTheather mt) {
		this.theathers.add(mt);
	}

	public void addBookedTicket(MovieTheather mt, Ticket t) {
		if(mt != null && t != null) {
			mt.addBookedTicket(t);
		}
	}
	
	public void addTicket(Ticket ticket) {
		if(ticket != null) {
			this.tickets.add(ticket);
		} else {
			System.out.println("Íå ìîæå äà ñå äîáàâè NULL áèëåò");
		}
	}

	public Set<Ticket> getTickets() {
		return Collections.unmodifiableSet(tickets);
	}
	
	
	//MAIN
	public static void main(String[] args) throws NotValidTicketTypeException {
		Cinema c = new Cinema();

		Ticket t = Ticket.getInstance(Ticket.ticketType.CHILD_TICKET, "J3", c);
		c.addTicket(t);
		Ticket t1 = Ticket.getInstance(Ticket.ticketType.INVALID_TICKET, "j3", c);
		System.out.println(t.isTicketsEquals(t1));
		MovieTheather mt = new MovieTheather();
		mt.showSeatsInTheathre();
		Consumer con = new Consumer(123);
		c.addMovieTheater(mt);
		
		Consumer con1 = new Consumer(20);
		con1.setMoney(100);
		
		con.setMoney(10000);
		con.buyTicket(c, mt);
		System.out.println(c.theathers.size());
		System.out.println(mt.getTickets().size());
		System.out.println(con.getMoney());
		
		con1.buyTicket(c, mt);
		System.out.println(c.theathers.size());
		System.out.println(mt.getTickets().size());
		System.out.println(con1.getMoney());
		
	}
	
	
}
