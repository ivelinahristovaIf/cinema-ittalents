package cinema;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.Scanner;

import tickets.NotValidTicketTypeException;
import tickets.Ticket;

public class MovieTheather {
	public static final String[] MOVIE_THEATHER_TYPE = { "IMAX", "VIP", "LUXE", "PREMIUM" };
	private static final String[] VIDEO_FORMAT = { "Real-D-3D", "2D", "HFR-3D", "4D" };
	private static final String[] AUDIO_FORMAT = { "Digital", "Atmos" };
	private static final char MAX_ROWS_IN_THEATHER = 'M';
	private static final int MAX_COLS_IN_ONE_ROW = 15;

	// TODO id
	private Map<Character, TreeSet<Integer>> seats;
	private Set<Ticket> bookedTickets;// booked tickets
	private String type;
	private String videoFormat;
	private String audioFormat;

	private MovieTheather(String type, String videoFormat, String audioFormat) {
		if (isValidTheatherType(type)) {
			this.type = type;
		}
		if (isValidVideoFormat(videoFormat)) {
			this.videoFormat = videoFormat;
		}
		if (isValidAudioFormat(audioFormat)) {
			this.audioFormat = "Dolby" + audioFormat;
		}
		this.seats = new TreeMap<Character, TreeSet<Integer>>();
		this.fillMovieTheatre();
		this.bookedTickets = new HashSet<>();
	}

	public static MovieTheather getInstance() throws NotValidMovieTheatherTypeException {
		System.out.println("�������� ������ �� ������ ��: ");
		for (int index = 1; index <= MOVIE_THEATHER_TYPE.length; index++) {
			System.out.println(index + " - " + MOVIE_THEATHER_TYPE[index - 1]);
		}
		String type = MOVIE_THEATHER_TYPE[DemoCinema.sc.nextInt() - 1];

		System.out.println("�������� ����� ������ ��: ");
		for (int index = 1; index <= VIDEO_FORMAT.length ; index++) {
			System.out.println(index + " - " + VIDEO_FORMAT[index - 1]);
		}
		String videoFormat = VIDEO_FORMAT[DemoCinema.sc.nextInt() - 1];

		System.out.println("�������� ����� ������ ��: ");
		for (int index = 1; index <= AUDIO_FORMAT.length; index++) {
			System.out.println(index + " - " + AUDIO_FORMAT[index - 1]);
		}
		String audioFormat = AUDIO_FORMAT[DemoCinema.sc.nextInt() - 1];

		return new MovieTheather(type, videoFormat, audioFormat);
		// TODO try again

	}

	private boolean isValidTheatherType(String type) {
		for (int i = 0; i < MOVIE_THEATHER_TYPE.length; i++) {
			if (type.equalsIgnoreCase(MOVIE_THEATHER_TYPE[i])) {
				return true;
			}
		}
//		System.err.println("��������� ����!");
		// TODO try again
		return false;
	}

	private boolean isValidVideoFormat(String videoFormat) {
		if (videoFormat != null && videoFormat.length() > 1) {
			for (int i = 0; i < VIDEO_FORMAT.length; i++) {
				if (videoFormat.equalsIgnoreCase(VIDEO_FORMAT[i])) {
					return true;
				}
			}
		}
		System.err.println("��������� ����� ������!");
		// TODO try again
		return false;
	}

	private boolean isValidAudioFormat(String audioFormat) {
		if (audioFormat != null && audioFormat.length() > 3) {
			for (int i = 0; i < AUDIO_FORMAT.length; i++) {
				if (audioFormat.equalsIgnoreCase(AUDIO_FORMAT[i])) {
					return true;
				}
			}
		}
		System.err.println("��������� ����� ������!");
		// TODO try again
		return false;
	}

	private void fillMovieTheatre() {
		Set<Integer> cols = new TreeSet<>((i1, i2) -> i1 - i2);

		for (char row = 'A'; row <= MAX_ROWS_IN_THEATHER; row++) {
			for (int i = 1; i <= MAX_COLS_IN_ONE_ROW; i++) {
				cols.add(i);
			}
			this.seats.put(row, (TreeSet<Integer>) cols);
		}
	}

	public void showSeatsInTheathre() throws NotValidTicketTypeException {
		System.out.println("����� � ������: ");
		System.out.println("        ==============�����==============");
		System.out.println();
		for (Entry<Character, TreeSet<Integer>> entry : this.seats.entrySet()) {
			System.out.print("��� " + entry.getKey() + ": ");
			for (Integer col : entry.getValue()) {
				String seat = entry.getKey() + ("" + col);
				Ticket t = Ticket.getInstance(Ticket.ticketType.CHILD_TICKET, seat, new Cinema());
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

	public void bookTicketInTheather(Ticket ticket) {
		if (ticket != null) {
			this.bookedTickets.add(ticket);
		} else {
			System.out.println("�� ���� �� �� ������ NULL �����");
		}
	}

	public Set<Ticket> getBookedTickets() {
		return Collections.unmodifiableSet(bookedTickets);
	}

	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "MovieTheather [ type=" + type + ", videoFormat=" + videoFormat + ", audioFormat="
				+ audioFormat + "]";
	}
//	public static void main(String[] args) throws NotValidTicketTypeException {
//		MovieTheather mt = new MovieTheather("IMAX", "2D", "Digital");
//		mt.showSeatsInTheathre();
//	}
}
