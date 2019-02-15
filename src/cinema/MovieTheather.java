package cinema;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import bean.Cinema;
import helper.NotValidMovieTheatherTypeException;
import tickets.NotValidTicketTypeException;
import tickets.Ticket;

public class MovieTheather implements Comparable<MovieTheather> {
	public static final String[] MOVIE_THEATHER_TYPE = { "IMAX", "VIP", "LUXE", "PREMIUM" };
	public static final String[] VIDEO_FORMAT = { "Real-D-3D", "2D", "HFR-3D", "4D" };
	public static final String[] AUDIO_FORMAT = { "Digital", "Atmos" };
	private static final char MAX_ROWS_IN_THEATHER = 'M';
	private static final int MAX_COLS_IN_ONE_ROW = 15;

	private final int id;
	private static int nextId = 1;
	private Map<Character, TreeSet<Integer>> seats;
	private Set<Ticket> bookedTickets;
	private String type;
	private String videoFormat;
	private String audioFormat;

	public MovieTheather(String type, String videoFormat, String audioFormat) {
		this.id = nextId++;
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
		System.out.println("Изберете формат на залата от: ");
		for (int index = 1; index <= MOVIE_THEATHER_TYPE.length; index++) {
			System.out.println(index + " - " + MOVIE_THEATHER_TYPE[index - 1]);
		}
		
		String type = MOVIE_THEATHER_TYPE[inputMovieTheaterFields(MOVIE_THEATHER_TYPE)];

		System.out.println("Изберете видео формат от: ");
		for (int index = 1; index <= VIDEO_FORMAT.length ; index++) {
			System.out.println(index + " - " + VIDEO_FORMAT[index - 1]);
		}
		String videoFormat = VIDEO_FORMAT[inputMovieTheaterFields(VIDEO_FORMAT)];
		
		System.out.println("Изберете аудио формат от: ");
		for (int index = 1; index <= AUDIO_FORMAT.length; index++) {
			System.out.println(index + " - " + AUDIO_FORMAT[index - 1]);
		}
		String audioFormat = AUDIO_FORMAT[inputMovieTheaterFields(AUDIO_FORMAT)];

		
		return new MovieTheather(type, videoFormat, audioFormat);

	}
	//try again.. sushto taka izbqgva arrayIndexOutOfBoundsException 
	//i InputMismatchException ako se vuvede neshto gadno
	private static int inputMovieTheaterFields(String[] str) {
		int lenght = str.length;
		String typeOption;
		String regex = "[0-9]+";
		int count = 0;
		do{
			if(count > 0) {
				System.out.println("Няма зала с такъв номер. Моля опитайте пак");
			}
			typeOption = DemoCinema.sc.next();
			if(typeOption.matches(regex)) {
				if(Integer.parseInt(typeOption) >= 1 && Integer.parseInt(typeOption) <= lenght) {
					break;
				}
			} else {
				System.out.println("Невалидно въвеждане, моля въведете число");
			}
			count++;
		}while(true);
		return Integer.parseInt(typeOption)-1;
	}

	private boolean isValidTheatherType(String type) {
		for (int i = 0; i < MOVIE_THEATHER_TYPE.length; i++) {
			if (type.equalsIgnoreCase(MOVIE_THEATHER_TYPE[i])) {
				return true;
			}
		}
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
		System.out.println("Места в киното: ");
		System.out.println("        ==============ЕКРАН==============");
		System.out.println();
		for (Entry<Character, TreeSet<Integer>> entry : this.seats.entrySet()) {
			System.out.print("Ред " + entry.getKey() + ": ");
			for (Integer col : entry.getValue()) {
				String seat = entry.getKey() + ("" + col);
				Ticket t = Ticket.getInstance(Ticket.ticketType.CHILD_TICKET, seat, Cinema.getInstance());
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
			System.out.println("Не може да се добави NULL билет");
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
		return "MovieTheather [ type=" + type + ", videoFormat=" + videoFormat + ", audioFormat=" + audioFormat + "]";
	}

	@Override
	public int compareTo(MovieTheather m) {
		return this.id - m.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		MovieTheather other = (MovieTheather) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
