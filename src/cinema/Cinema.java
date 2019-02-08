package cinema;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import tickets.Ticket;
import users.Admin;
import users.Consumer;

public class Cinema {
	private static final int NUMBER_DAYS_IN_CALENDAR = 7;

	public enum MovieGenres {
		drama("�����"), horor("�����"), comedy("�������"), anime("��������"), action("�����"), fantasy("����������"),
		biography("�����������"), adventure("������������"), romance("����������"), crime("����������"),
		military("������"), science("������ ���������"), musical("�������");

		private String name;

		private MovieGenres(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	
	public enum movieCategories {
		A, B, C, D
	}

	public static Set<Consumer> consumers; // TO DO private non static
	private Set<MovieTheather> theathers;
	// type->date->movie
	private Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> moviesCatalogue;
	private Comparator<Movie> movieComparator;
	public static Admin admin = Admin.createAdmin("admin", "admin");

	public Cinema() {
		consumers = new HashSet<Consumer>();
		// TODO comparator consumers
		// TODO compare by theather type
		this.theathers = new TreeSet<MovieTheather>((mt1, mt2) -> mt1.getType().compareToIgnoreCase(mt2.getType()));
		// TODO maybe compare movies by id
		this.movieComparator = (movie1, movie2) -> movie1.getName().compareToIgnoreCase(movie2.getName());
		this.moviesCatalogue = new HashMap<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>>();
	}

	public MovieTheather addMovieTheatherToCinema() {
		MovieTheather theather = null;
		try {
			theather = MovieTheather.getInstance();
		} catch (NotValidMovieTheatherTypeException e) {
			e.printStackTrace();
		}
		if (theather != null) {
			this.theathers.add(theather);
		} else {
			System.err.println("Null theather!"); 
		}

		return theather;
	}

	public void addMovieToCatalogue(Movie movie) throws NotValidMovieTheatherTypeException {
		if (movie != null && this.moviesCatalogue != null) {
			System.out.println("���� �������� ���� �� �����������: ");
			MovieTheather movieTheater = null;
			if (this.theathers.size() > 0) {
				List<MovieTheather> listOfTheathers = new LinkedList<MovieTheather>(this.theathers);
				for (int index = 1; index <= listOfTheathers.size(); index++) {
					System.out.println(index + " - " + listOfTheathers.get(index - 1));
				}
				int index = DemoCinema.sc.nextInt();
				movieTheater = listOfTheathers.get(index);
			} else {
				System.out.println("��� ��� ���� �������� ���� � ������! �������� ����");
				movieTheater = this.addMovieTheatherToCinema();
			}
			if (!this.moviesCatalogue.containsKey(movieTheater)) {
				this.moviesCatalogue.put(movieTheater, new TreeMap<>());
				System.out.println("���� ���� ���� ���� �� �������� � ������!");
				System.out.println();
			}
			this.showWeeksCalendar();
			LocalDate date = this.inputDate();
			System.out.println(date);
			if (this.moviesCatalogue.get(movieTheater).containsKey(date)) {
				System.out.println("������ � ����� �� ���� ����");// TODO proverka za svobodni chasove
				// TODO freeHours moved in MovieTheather
			}
			this.moviesCatalogue.get(movieTheater).put(date, new TreeSet<Movie>(this.movieComparator));
			// add movie in TreeSet
			boolean isAdded = this.moviesCatalogue.get(movieTheater).get(date).add(movie);
			System.out.println("dobaven li e:" + isAdded);
		} else {
			System.err.println("null movie or catalogue");
		}
	}

	private void showWeeksCalendar() {
		LocalDate localDate = LocalDate.now();
		System.out.println("�������� �� ���������: ");
		while (localDate.isBefore(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
			System.out.println(
					localDate.getDayOfMonth() + "." + localDate.getMonthValue() + " - " + localDate.getDayOfWeek());
			localDate = localDate.plusDays(1);
		}
	}

//	public void addMovieTheater(MovieTheather mt) {
//		this.theathers.add(mt);
//	}

	public void addBookedTicket(MovieTheather mt, Ticket ticket) {
		if (mt != null && ticket != null) {
			mt.bookTicketInTheather(ticket);
		}
	}


	private LocalDate inputDate() {
		System.out.print("���� �������� ����, �� ����� �� �������� �����������: ");
		boolean retry = false;
		LocalDate date = null;
		while (!retry) {
			try {
				String d = DemoCinema.sc.next();
				String[] n = d.split("\\.");
				Byte day = Byte.parseByte(n[0]);
				Byte month = Byte.parseByte(n[1]);
				date = LocalDate.of(LocalDate.now().getYear(), month, day);
				if ((date.isAfter(LocalDate.now()) || date.equals(LocalDate.now()))
						&& !date.isAfter(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
					retry = true;
					break;
				}
				System.out.println("��������� ���� ���� �������� ������");
			} catch (InputMismatchException | NumberFormatException e) {
				System.err.println("��������� ������ �� ������! �������� ������:");
				retry = false;
			}
		}
		return date;
	}


	public Map<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>> getMoviesCatalogue() {
		return Collections.unmodifiableMap(this.moviesCatalogue);
	}
}
