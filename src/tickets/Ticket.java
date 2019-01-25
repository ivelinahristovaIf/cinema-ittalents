package tickets;

import cinema.Cinema;

public class Ticket {
	public enum ticketType {
		STANDART_TICKET, CHILD_TICKET, INVALID_TICKET, STUDENT_TICKET
	}

	private static final int INVALID_TICKET_PRICE = 5;
	private static final int STUDENT_TICKET_PRICE = 8;
	private static final int CHILD_TICKET_PRICE = 6;
	private static final int STANDART_TICKET_PRICE = 10;

	private Cinema cinema;
//	private Movie movie;
	private int price;
	private Seat seat;
	private char row;
	private int col;
//	private String type;
	private static int id = 0;
	private int serialNumber;

	private Ticket(String seat, int price, Cinema cinema /* ,M	ovie movie */) {
		this.seat = new Seat(seat);
		setPrice(price);

		if (cinema != null) {
			this.cinema = cinema;
		} else {
			System.out.println("Cinema is null");
		}

		this.serialNumber = ++Ticket.id; // unique serial number
		// TO DO this.movie = movie
	}
	public void setPrice(int price) {
		if (price > 0) {
			this.price = price;
		} else {
			System.out.println("Ticket price must be a positive value");
		}
	}
	public static Ticket getInstance(ticketType type, String seat, Cinema cinema) throws NotValidTicketTypeException {
		switch (type) {
		case STANDART_TICKET:
			return new Ticket(seat, STANDART_TICKET_PRICE, cinema);
		case CHILD_TICKET:
			return new Ticket(seat, CHILD_TICKET_PRICE, cinema);
		case STUDENT_TICKET:
			return new Ticket(seat, STUDENT_TICKET_PRICE, cinema);
		case INVALID_TICKET:
			return new Ticket(seat, INVALID_TICKET_PRICE, cinema);
		}
		throw new NotValidTicketTypeException("Няма такъв билет");
	}

	public Seat getSeat() {
		return seat;
	}
	
	
	@Override
	public String toString() {
		return "Ticket [cinema=" + cinema + ", price=" + price + ", seat=" + seat.getSeatValue() + ", serialNumber="
				+ serialNumber + "]";
	}
	public void reservedTicket() {
		this.seat.setFree(false);
	}
	public int getPrice() {
		return price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + serialNumber;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (serialNumber != other.serialNumber)
			return false;
		return true;
	}
	
	//INNER CLASS

	private class Seat {

		private static final char MAX_ROW_SMALL_LETTERS = 'z';
		private static final char MIN_ROW_SMALL_LETTERS = 'a';
		private static final char MAX_ROW = 'Z';
		private static final char MIN_ROW = 'A';

		private static final int MAX_NUMBER_PLACE = 15;

		private String row;
		private int place;
		private boolean isFree;

		public Seat(String stringSeat) {
			stringSeat = stringSeat.toUpperCase();
			if (isValidRow(stringSeat)) {
				this.row = stringSeat.substring(0, 1);
			} else {
				System.out.println("That is invalid row");
			}
			if (isValidPlace(stringSeat)) {
				this.place = Integer.parseInt(stringSeat.substring(1));
			} else {
				System.out.println("That is invalid place");
			}
			
			this.isFree = true;
		}
		
		private boolean isValidPlace(String stringSeat) {
			return Integer.parseInt(stringSeat.substring(1, stringSeat.length())) <= MAX_NUMBER_PLACE
					&& Integer.parseInt(stringSeat.substring(1, stringSeat.length())) >= 0;
		}
		

		
		private boolean isValidRow(String stringSeat) {
			return (stringSeat.charAt(0) >= MIN_ROW && stringSeat.charAt(0) <= MAX_ROW)
					|| (stringSeat.charAt(0) >= MIN_ROW_SMALL_LETTERS && stringSeat.charAt(0) <= MAX_ROW_SMALL_LETTERS);
		}

		public String getSeatValue() {
			if (this.row != null) {
				return (this.row + " - " + ("" + this.place));
			} else {
				return "Sorry the ticket is null";
			}
		}

		public boolean isFree() {
			return isFree;
		}

		public void setFree(boolean isFree) {
			this.isFree = isFree;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + place;
			result = prime * result + ((row == null) ? 0 : row.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Seat other = (Seat) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (place != other.place)
				return false;
			if (row == null) {
				if (other.row != null)
					return false;
			} else if (!row.equals(other.row))
				return false;
			return true;
		}

		private Ticket getOuterType() {
			return Ticket.this;
		}


	}

	
	

}
