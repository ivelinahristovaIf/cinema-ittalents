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
		System.out.println("Ìåñòà â êèíîòî: ");
		for(Entry<Character, TreeSet<Integer>> entry : this.movieTheatre.entrySet()) {
			
			
			System.out.print("Ğåä " + entry.getKey() + ": ");
			for(Integer col : entry.getValue()) {
				String s = (entry.getKey() + " - " + (""+col));
				System.out.print(col + " ");
			}
			System.out.println();
		}
	}
			
	
	public void addTicket(Ticket ticket) {
		if(ticket != null) {
			this.tickets.add(ticket);
		} else {
			System.out.println("Íå ìîæå äà ñå äîáàâè NULL áèëåò");
		}
	}


	public Map<Character, TreeSet<Integer>> getMovieTheatre() {
		return Collections.unmodifiableMap(this.movieTheatre);
	}


	public Set<Ticket> getTickets() {
		return Collections.unmodifiableSet(tickets);
	}
	

	
	
}
