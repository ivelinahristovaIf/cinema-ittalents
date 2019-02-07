package cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import cinema.Cinema.MovieGenres;

public class Movie {
	private static final int NUMBER_OF_DAYS_BEFORE_TODAY = 30;
	private static final int NUMBER_OF_DAYS_FROM_TODAY = 1;
	private static final short BREAK_BETWEEN_MOVIES = 20;
	private static int maxProjections = 5;
	private static final int MAX_LENGTH = 200;
	private static final int MIN_LENGTH = 60;
	private int id;
	private String name;
	private short length;
	private LocalDate premiere;
	private Cinema.MovieGenres genre; // TODO in getInstance set genre
	private Cinema.movieCategories category;
	private Set<LocalTime> projections;
	private LocalTime startTimes;
	private LocalTime endTimes;
	private Set<LocalTime> freeHours;
	// TODO list available hours by category
	// TODO private LocalDateTime time;

	private Movie(/* int id, */ String name, short length, LocalDate premiere, Cinema.movieCategories c) {
		// TODO id
//		this.id = id;

		if (name != null && name.trim().length() >= 2) {
			this.name = name;
		} else {
			System.err.println("Невалидно име");
		}
		if (length >= MIN_LENGTH && length <= MAX_LENGTH) {
			this.length = length;
		} else {
			System.err.println("Невалидна дължина");
		}
		if (isValidPremiereDate(premiere)) {
			this.premiere = premiere;
		}

		if (isValidMovieCategory(c)) {
			this.category = c;
		}
		this.projections = new TreeSet<LocalTime>();
	}

	public static Movie getInstance() throws NotValidMovieGenreException {
		System.out.println("Изберете жанр от: ");
		for (int index = 0; index < Cinema.MovieGenres.values().length; index++) {
			System.out.println(index + " - " + Cinema.MovieGenres.values()[index].getName());
		}
		// TODO throw and catch exceptions
		int index = DemoCinema.sc.nextInt();
		Cinema.MovieGenres genre = Cinema.MovieGenres.values()[index];
		System.out.println("Въведете име: ");
		String name = DemoCinema.sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		System.out.println("Въведете премиерна дата: (ден/месец/година)");
		LocalDate premiere = null; // TODO null pointer exception
		boolean retry = false;
		// Date input
		while (!retry) {
			try {
				String date = DemoCinema.sc.next();
				premiere = LocalDate.parse(date, formatter);
				retry = true;
			} catch (DateTimeParseException e) {
				System.out.print("Невалиден формат. Опитайте отново:");
				retry = false;
			}
		}
		System.out.println("Изберете категория от: ");
		for (Cinema.movieCategories c : Cinema.movieCategories.values()) {
			System.out.println(c.name());
		}
		Cinema.movieCategories category = Cinema.movieCategories.valueOf(DemoCinema.sc.next().toUpperCase());

		Movie movie;
		switch (genre) {
		case anime:
			movie = new Movie(name, (short) 90, premiere, category);
			movie.setGenre(genre);
			movie.startTimes = LocalTime.of(9, 0);
			movie.endTimes = LocalTime.of(18, 20).minusMinutes(90);
			movie.freeHours = movie.fillInFreeHours();
			return movie;
		case musical:
		case comedy:
		case romance:
			movie = new Movie(name, (short) 120, premiere, category);
			movie.setGenre(genre);
			movie.startTimes = LocalTime.of(12, 20);
			movie.endTimes = LocalTime.of(00, 00).minusMinutes(120);
			movie.freeHours = movie.fillInFreeHours();
			return movie;
		case biography:
		case military:
		case drama:
		case action:
		case crime:
		case science:
		case adventure:
		case horor:
		case fantasy:
			Movie.maxProjections = 4;
			System.out.println("Въведете дължина на филма: ");
			short length = DemoCinema.sc.nextShort();
			movie = new Movie(name, length, premiere, category);
			movie.setGenre(genre);
			movie.endTimes = LocalTime.of(23, 50);// TODO work after 12
			movie.startTimes = movie.endTimes.minusMinutes(maxProjections * (movie.length + BREAK_BETWEEN_MOVIES));
			movie.freeHours = movie.fillInFreeHours();
			return movie;
		}
		throw new NotValidMovieGenreException("Няма такъв жанр филм!");

	}

	public void setTimes() {
		// TODO choose theather
		if (this.projections.size() > 0) {
			System.out.println("Настоящи прожекции: ");
			this.projections.forEach(projection -> System.out.println(projection));
		} else {
			System.out.println("Все още няма добавени прожекции за този филм, в тази зала");
		}
		if (this.projections.size() >= maxProjections) {
			System.out.println("Няма свободни часове за този ден!");
			return;
		}
		System.out.println(
				"Колко прожекции искате да добавите? Не повече от " + (maxProjections - this.projections.size()));
		byte number = DemoCinema.sc.nextByte();
		while (number > maxProjections - this.projections.size()) {
			System.out.println("Не повече от " + (maxProjections - this.projections.size()));

			System.out.println("Колко прожекции искате да добавите: ");
			number = DemoCinema.sc.nextByte();
		}
		while (this.projections.size() < number) {
//			System.out.println(this.projections.size());
			this.listFreeHours();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
			System.out.print("Изберете час и минути: (h:mm)");
			boolean retry = false;
			LocalTime time = null;
			while (!retry) {
				String inputTime;
				try {
					inputTime = DemoCinema.sc.next();
					time = LocalTime.parse(inputTime, formatter);
					retry = true; // break the loop
					if (!this.freeHours.contains(time)) {
						throw new InvalidHourException();
					}
				} catch (Exception e) {
					System.out.print("Невалиден час! Опитайте отново. ");
					retry = false;
				}
			}
			if (time != null) {
				this.projections.add(time);
				this.freeHours.remove(time);
			}
		}
	}

	private Set<LocalTime> fillInFreeHours() {
		Set<LocalTime> freeHours = new TreeSet<LocalTime>();
		if (this.startTimes != null && this.endTimes != null) {
			LocalTime time = this.startTimes;
			int count = 0;
			while (count < maxProjections) {
				freeHours.add(time);
				time = time.plusMinutes(this.length + BREAK_BETWEEN_MOVIES);
				count++;
			}
			return freeHours;
		}
		System.err.println("Null starting or ending hour!");
		return new TreeSet<>();
	}

	private void listFreeHours() {
		System.out.println("Списък със свободни начални часове: ");
		this.freeHours.forEach(hour -> System.out.println(hour));
	}

	private boolean isValidMovieCategory(Cinema.movieCategories category) {
		if (category != null) {
			for (Cinema.movieCategories cat : Cinema.movieCategories.values()) {
				if (cat.name().equalsIgnoreCase(category.name())) {
					return true;
				}
			}
		}
		System.err.println("Invalid movie category!");
		return false;
	}

	private boolean isValidPremiereDate(LocalDate premiere) {
		return premiere.isBefore(LocalDate.now().plusDays(NUMBER_OF_DAYS_FROM_TODAY))
				&& premiere.isAfter(LocalDate.now().minusDays(NUMBER_OF_DAYS_BEFORE_TODAY));
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", length: " + length + ", premiere=" + premiere + ", genre="
				+ this.getClass().getSimpleName() + ", category=" + category.name() + "]";
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public Cinema.MovieGenres getGenre() {
		return genre;
	}

	public void setGenre(Cinema.MovieGenres genre) {
		this.genre = genre;
	}

}
