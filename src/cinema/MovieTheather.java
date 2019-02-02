package cinema;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import tickets.NotValidTicketTypeException;
import tickets.Ticket;

public class MovieTheather {
	private static final char MAX_ROWS_IN_THEATHER = 'M';
	private static final int MAX_COLS_IN_ONE_ROW = 15;
	
	//TODO id
	private int id;
	private Map<Character, TreeSet<Integer>> seats;
	private Set<Ticket> tickets;//booked tickets
	private String type;
	
	public MovieTheather(String type) {
		this.type = type;
		this.seats = new TreeMap<Character,TreeSet<Integer>>();
		this.fillMovieTheatre();
		this.tickets = new HashSet<>();
	}
	
	public void addBookedTicket(Ticket t) {
		if(t != null) {
			this.tickets.add(t);
		}
	}
	
	private void fillMovieTheatre() {
		Set<Integer> cols = new TreeSet<>((i1,i2) -> i1 - i2);
		
		for (char row = 'A'; row <= MAX_ROWS_IN_THEATHER; row++) {
			for (int i = 1; i <= MAX_COLS_IN_ONE_ROW; i++) {
				cols.add(i);
			}
			this.seats.put(row, (TreeSet<Integer>) cols);
		}
	}

	public void showSeatsInTheathre() throws NotValidTicketTypeException {
		System.out.println("Места в киното: ");
		System.out.println("        ==============ЕКРАН==============");
		System.out.println();
		for(Entry<Character, TreeSet<Integer>> entry : this.seats.entrySet()) {
			System.out.print("Ред " + entry.getKey() + ": ");
			for(Integer col : entry.getValue()) {
				System.out.print(col + " ");
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

	public int getId() {
		return this.id;
	}

	public Set<Ticket> getBookedTickets() {
		return Collections.unmodifiableSet(tickets);
	}

	public String getType() {
		return this.type;
	}
}
