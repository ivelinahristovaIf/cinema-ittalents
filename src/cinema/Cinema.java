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

	private static final char MAX_ROWS_IN_CINEMA = 'M';
	private static final int MAX_COLS_IN_ONE_ROW = 15;
	public static Set<Consumer> consumers; // TO DO private
	private Map<Character, TreeSet<Integer>> movieTheatre;
	private Set<Ticket> tickets;

	public Cinema() {
		consumers = new HashSet<Consumer>();
		this.movieTheatre = new TreeMap<>();
		this.fillMovieTheatre();
		this.tickets = new HashSet<>();
	}
	
	
	private void fillMovieTheatre() {
		Set<Integer> cols = new TreeSet<>((i1,i2) -> i1 - i2);
		
		for (char r = 'A'; r <= MAX_ROWS_IN_CINEMA; r++) {
			for (int i = 1; i <= MAX_COLS_IN_ONE_ROW; i++) {
				cols.add(i);
			}
			this.movieTheatre.put(r, (TreeSet<Integer>) cols);
		}
	}
	
	public void showSeatsInTheCinema() throws NotValidTicketTypeException {
		System.out.println("Места в киното: ");
		for(Entry<Character, TreeSet<Integer>> entry : this.movieTheatre.entrySet()) {		
			System.out.print("Ред " + entry.getKey() + ": ");
			for(Integer col : entry.getValue()) {
				String s = (entry.getKey() + " - " + (""+col));
				Ticket t = Ticket.getInstance(ticketType.CHILD_TICKET, s, this);
				if(this.tickets.contains(t) && t.isReserved()) {
					System.err.println(col + " ");
				} else {
					System.out.print(col + " ");
				}
			}
			System.out.println();
		}
	}
			

	public void addTicket(Ticket ticket) {
		if(ticket != null) {
			this.tickets.add(ticket);
		} else {
			System.out.println("Не може да се добави NULL билет");
		}
	}


	public Map<Character, TreeSet<Integer>> getMovieTheatre() {
		return Collections.unmodifiableMap(this.movieTheatre);
	}


	public Set<Ticket> getTickets() {
		return Collections.unmodifiableSet(tickets);
	}
	
	
	public static void main(String[] args) throws NotValidTicketTypeException {
		Cinema c = new Cinema();
		Consumer con = new Consumer(123);
		Ticket t = Ticket.getInstance(Ticket.ticketType.CHILD_TICKET, "J3", c);
		c.addTicket(t);
		
		con.buyTicket(c);
	}
	
	
}
