package cinema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import tickets.NotValidTicketTypeException;
import tickets.Ticket;
import tickets.Ticket.ticketType;
import users.Admin;
import users.Consumer;

public class Cinema {
	private static final int NUMBER_DAYS_IN_CALENDAR = 7;

	public enum MovieGenres {
		// enum(value)
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
	// TODO remove tickets
	private Set<Ticket> tickets;
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
		this.tickets = new HashSet<>();
		// TODO maybe compare movies by id
		this.movieComparator = (movie1, movie2) -> movie1.getName().compareToIgnoreCase(movie2.getName());
		this.moviesCatalogue = new HashMap<MovieTheather, TreeMap<LocalDate, TreeSet<Movie>>>();
	}

	// TODO admin method modify catalogue
	public void addMovieToCatalogue(Movie movie) throws NotValidMovieTheatherTypeException {
		if (movie != null && this.moviesCatalogue != null) {
			System.out.println("���� �������� ���� �� �����������: ");
			this.theathers.stream().map(theather -> theather.getType())
					.forEach(theather -> System.out.println(theather));
			MovieTheather movieTheater = MovieTheather.getInstance();
			if (!this.moviesCatalogue.containsKey(movieTheater)) {
				this.moviesCatalogue.put(movieTheater, new TreeMap<>());
				System.out.println("���� ���� ���� ���� �� ��������� � ������!");
				System.out.println();
			}
			this.showWeeksCalendar();
//			boolean flag = true;
//			try {
			LocalDate date = this.inputDate();
//				flag = false;
//				if (date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now())) {
			System.out.println(date);
			if (this.moviesCatalogue.get(movieTheater).containsKey(date)) {
				System.out.println("������ � ����� �� ���� ����");// TODO proverka za svobodni chasove
				// TODO freeHours moved in MovieTheather
			}
			this.moviesCatalogue.get(movieTheater).put(date, new TreeSet<Movie>(this.movieComparator));
			// add movie in TreeSet
			boolean isAdded = this.moviesCatalogue.get(movieTheater).get(date).add(movie);
			System.out.println("dobaven li e:" + isAdded);
//				}
//				flag = false;
//			} catch (InputMismatchException e) {
//				System.err.println("��������� ������ �� ������ 1!");
//				// TODO try again
//			}
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

	public void addMovieTheater(MovieTheather mt) {
		this.theathers.add(mt);
	}

	public void addBookedTicket(MovieTheather mt, Ticket ticket) {
		if (mt != null && ticket != null) {
			mt.bookTicketInTheather(ticket);
		}
	}

	// TODO tickets are in movieTheather only
	public void addTicket(Ticket ticket) {
		if (ticket != null) {
			this.tickets.add(ticket);
		} else {
			System.out.println("�� ���� �� �� ������ NULL �����");
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
				retry = true;
				if ((date.isAfter(LocalDate.now()) || date.equals(LocalDate.now()))
						&& !date.isAfter(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
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

	public Set<Ticket> getTickets() {
		return Collections.unmodifiableSet(tickets);
	}
}
