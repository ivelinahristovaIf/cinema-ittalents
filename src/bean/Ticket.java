package bean;

import helper.NotValidTicketTypeException;

public class Ticket {
	public static final  String[] TICKET_TYPE={"—“¿Õƒ¿–“≈Õ","ƒ≈“— »","«¿ »Õ¬¿À»ƒ»","—“”ƒ≈Õ“— »"};

	private static final int INVALID_TICKET_PRICE = 5;
	private static final int STUDENT_TICKET_PRICE = 8;
	private static final int CHILD_TICKET_PRICE = 6;
	private static final int STANDART_TICKET_PRICE = 10;

	private MovieTheather movieTheather;
	private Movie movie;
	private int price;
	private Seat seat;
	private char row;
	private int col;
	private static int id  = 0;
	private int serialNumber;
	
	public Ticket() {
		super();
		this.serialNumber = ++id;
	}

	private Ticket(String seat, int price, MovieTheather theater  ,Movie movie ) {
		this.seat = new Seat(seat);
		setPrice(price);
		this.movie = movie;
		this.movieTheather = theater;
		this.serialNumber = ++Ticket.id; // unique serial number
	}

	public void setPrice(int price) {
		if (price > 0) {
			this.price = price;
		} else {
			System.out.println("Ticket price must be a positive value");
		}
	}

	public boolean isTicketsEquals(Ticket t) {
		if (t != null) {
			return this.seat.getSeatValue().equals(t.seat.getSeatValue());
		}
		return false;
	}

	public boolean isTwoSeatsEquals(String s1, String s2) {
		return s1 != null && s2 != null && s1.equals(s2);
	}

	public static Ticket getInstance(String type, String seat, MovieTheather theater,Movie movie)
			throws NotValidTicketTypeException {
		switch (type) {
		case "—“¿Õƒ¿–“≈Õ":
			return new Ticket(seat, STANDART_TICKET_PRICE, theater,movie);
		case "ƒ≈“— »":
			return new Ticket(seat, CHILD_TICKET_PRICE, theater,movie);
		case "—“”ƒ≈Õ“— »":
			return new Ticket(seat, STUDENT_TICKET_PRICE, theater,movie);
		case "«¿ »Õ¬¿À»ƒ»":
			return new Ticket(seat, INVALID_TICKET_PRICE, theater,movie);
		}
		throw new NotValidTicketTypeException("ÕˇÏ‡ Ú‡Í˙‚ ·ËÎÂÚ");
	}

	public Seat getSeat() {
		return seat;
	}

	public void reservedTicket() {
		this.seat.setFree(false);
	}

	public int getPrice() {
		return price;
	}
	public static int getPriceByType(String type) {
		switch (type) {
		case "—“¿Õƒ¿–“≈Õ":
			return STANDART_TICKET_PRICE;
		case "ƒ≈“— »":
			return CHILD_TICKET_PRICE;
		case "«¿ »Õ¬¿À»ƒ»":
			return INVALID_TICKET_PRICE;
		case "—“”ƒ≈Õ“— »":
			return STUDENT_TICKET_PRICE;
		}
		return 0;
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

	// INNER CLASS

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

		@Override
		public String toString() {
			return "Seat [row=" + row + ", place=" + place + "]";
		}

	}

	public char getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public MovieTheather getMovieTheather() {
		return movieTheather;
	}

	public void setMovieTheather(MovieTheather movieTheather) {
		this.movieTheather = movieTheather;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public void setRow(char row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public static String[] getTicketType() {
		return TICKET_TYPE;
	}

	@Override
	public String toString() {
		return "Ticket [movieTheather=" + movieTheather + ", movie=" + movie + ", price=" + price + ", seat=" + seat
				+ "]";
	}

}
