package bean;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import cinema.Cinema;
import tickets.NotValidTicketTypeException;
import tickets.Ticket;

public class MovieTheather {
	private static final char MAX_ROWS_IN_THEATHER = 0;
	private static final int MAX_COLS_IN_ONE_ROW = 0;
	
	private int id;
//	private static int nextId = 1;//TODO
	
	private Map<Character, TreeSet<Integer>> seats;
	private Set<Ticket> bookedTickets;
	
	private MovieTheatherType type;
	
	public MovieTheather() {
		super();
		this.seats = new TreeMap<Character, TreeSet<Integer>>();
		this.bookedTickets = new HashSet<>();
	}
	
	
	public MovieTheather(int id, Set<Ticket> bookedTickets, MovieTheatherType type) {
		super();
		this.id = id;
		this.bookedTickets = bookedTickets;
		this.type = type;
		this.fillInMovieTheatreSeats();
	}


	private void fillInMovieTheatreSeats() {
		Set<Integer> cols = new TreeSet<>((i1, i2) -> i1 - i2);

		for (char row = 'A'; row <= MAX_ROWS_IN_THEATHER; row++) {
			for (int i = 1; i <= MAX_COLS_IN_ONE_ROW; i++) {
				cols.add(i);
			}
			this.seats.put(row, (TreeSet<Integer>) cols);
		}
	}
	public void addBookedTicket(Ticket ticket) {
			if (this.bookedTickets == null) {
				this.bookedTickets = new HashSet<Ticket>();
			}
			this.bookedTickets.add(ticket);
	}
	
	public void showSeatsInTheathre() throws NotValidTicketTypeException {
		System.out.println("Места в киното: ");
		System.out.println("        ==============ЕКРАН==============");
		System.out.println();
		for (Entry<Character, TreeSet<Integer>> entry : this.seats.entrySet()) {
			System.out.print("Ред " + entry.getKey() + ": ");
			for (Integer col : entry.getValue()) {
				String seat = entry.getKey() + ("" + col);
				Ticket t = Ticket.getInstance(Ticket.ticketType.CHILD_TICKET, seat, bean.Cinema.getInstance());
				boolean isFreeSeat = true;
				for (Ticket ticket : this.getBookedTickets()) {
					if (t.isTicketsEquals(ticket)) {
						isFreeSeat = false;
						break;
					}
				}
				if (isFreeSeat) {
					System.out.print(col + " ");
				} else {
					System.out.print("X" + " ");
				}
			}
			System.out.println();
		}
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Character, TreeSet<Integer>> getSeats() {
		return seats;
	}

	public void setSeats(Map<Character, TreeSet<Integer>> seats) {
		this.seats = seats;
	}

	public Set<Ticket> getBookedTickets() {
		return bookedTickets;
	}

	public void setBookedTickets(Set<Ticket> bookedTickets) {
		this.bookedTickets = bookedTickets;
	}

	public MovieTheatherType getType() {
		return type;
	}

	public void setType(MovieTheatherType type) {
		this.type = type;
	}


	public void bookTicketInTheather(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
