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
	public static final String[] MOVIE_THEATHER_TYPE = { "IMAX", "VIP", "LUXE", "PREMIUM" };
	private static final String[] VIDEO_FORMAT = { "Real-D-3D", "2D", "HFR-3D", "4D" };
	private static final String[] AUDIO_FORMAT = { "Digital", "Atmos" };
	private static final char MAX_ROWS_IN_THEATHER = 'M';
	private static final int MAX_COLS_IN_ONE_ROW = 15;

	// TODO id
	private int id;
	private Map<Character, TreeSet<Integer>> seats;
	private Set<Ticket> tickets;// booked tickets
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
		this.tickets = new HashSet<>();
	}

	public static MovieTheather getInstance() throws NotValidMovieTheatherTypeException {
		System.out.println("Изберете формат на залата от: ");
		for (String type : MOVIE_THEATHER_TYPE) {
			System.out.println(type);
		}
		String type = DemoCinema.sc.next();

		System.out.println("Изберете видео формат от: ");
		for (String videoFormat : VIDEO_FORMAT) {
			System.out.println(videoFormat);
		}
		String videoFormat = DemoCinema.sc.next();

		System.out.println("Изберете аудио формат от: ");
		for (String audio : AUDIO_FORMAT) {
			System.out.println(audio);
		}
		String audioFormat = DemoCinema.sc.next();
		switch (type) {
		case "IMAX":
			return new MovieTheather(type, videoFormat, audioFormat);
		case "VIP":
			return new MovieTheather(type, videoFormat, audioFormat);
		case "LUX":
			return new MovieTheather(type, videoFormat, audioFormat);
		case "PREMIUM":
			return new MovieTheather(type, videoFormat, audioFormat);
		}
		throw new NotValidMovieTheatherTypeException("Няма такава зала!");
	}

	private boolean isValidTheatherType(String type) {
		for (int i = 0; i < MOVIE_THEATHER_TYPE.length; i++) {
			if (type.equalsIgnoreCase(MOVIE_THEATHER_TYPE[i])) {
				return true;
			}
		}
//		System.err.println("Невалидна зала!");
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
		System.err.println("Невалиден видео формат!");
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
		System.err.println("Невалиден аудио формат!");
		// TODO try again
		return false;
	}

	public void addBookedTicket(Ticket t) {
		if (t != null) {
			this.tickets.add(t);
		}
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
		System.out.println("Места в киното: ");
		System.out.println("        ==============ЕКРАН==============");
		System.out.println();
		for (Entry<Character, TreeSet<Integer>> entry : this.seats.entrySet()) {
			System.out.print("Ред " + entry.getKey() + ": ");
			for (Integer col : entry.getValue()) {
				System.out.print(col + " ");
			}
			System.out.println();
		}
	}

	public void addTicket(Ticket ticket) {
		if (ticket != null) {
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

	@Override
	public String toString() {
		return "MovieTheather [id=" + id + ", type=" + type + ", videoFormat=" + videoFormat + ", audioFormat="
				+ audioFormat + "]";
	}
}
