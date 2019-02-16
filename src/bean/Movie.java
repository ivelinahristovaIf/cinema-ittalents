package bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.TreeSet;
import cinema.DemoCinema;
import helper.InvalidHourException;
import helper.MovieGenres;
import helper.NotValidMovieGenreException;

public class Movie implements Comparable<Movie> {
	public static final String[] MOVIE_GENRE = { "�����", "�����", "�������", "��������", "�����", "����������",
			"�����������", "������������", "����������", "����������", "������", "������ ���������", "�������" };
	public static final String[] MOVIE_CATEGORIES = { "A", "B", "C", "D" };

	private static final int NUMBER_OF_DAYS_BEFORE_TODAY = 30;
	private static final int NUMBER_OF_DAYS_FROM_TODAY = 1;
	private static final short BREAK_BETWEEN_MOVIES = 20;
	private static int maxProjections = 5;
	private static final int MAX_LENGTH = 200;
	private static final int MIN_LENGTH = 60;
	private int id;
	private static int nextId = 1;
	private String name;
	private short length;
	private LocalDate premiere;
	private String genre;
	private String category;
	private Set<LocalTime> projections;
	private LocalTime startTimes;
	private LocalTime endTimes;
	private Set<LocalTime> freeHours;

	public Movie() {
		super();
	}

	private Movie(String name, short length, LocalDate premiere, String category) {
		this.id = nextId++;

		if (name != null && name.trim().length() >= 2) {
			this.name = name;
		} else {
			System.err.println("��������� ���");
		}
		if (length >= MIN_LENGTH && length <= MAX_LENGTH) {
			this.length = length;
		} else {
			System.err.println("��������� �������");
		}
		if (isValidPremiereDate(premiere)) {
			this.premiere = premiere;
		}

		if (isValidMovieCategory(category)) {
			this.category = category;
		}
		this.projections = new TreeSet<LocalTime>();
	}

	// FACTORY
	public static Movie getInstance(String genre, String name, short length, LocalDate premiere,
			String category) throws NotValidMovieGenreException {
//		System.out.println("�������� ���� ��: ");
//		for (int index = 0; index < MovieGenres.values().length; index++) {
//			System.out.println(index + " - " + MovieGenres.values()[index].getName());
//		}
//		// TODO throw and catch exceptions
//		String stringIndex = DemoCinema.sc.next();
//		String reg = "[0-Cinema.MovieGenres.values().length]+";
//		while(!(stringIndex.matches(reg))) {
//			System.out.println("�������� ������");
//			stringIndex = DemoCinema.sc.next();
//		}
//		int index = Integer.parseInt(stringIndex);
//		MovieGenres genre = MovieGenres.values()[index];
//
//		System.out.println("�������� ���: ");
//		String name = DemoCinema.sc.next();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//		System.out.println("�������� ��������� ����: (���/�����/������)");
//		LocalDate premiere = null; // TODO null pointer exception
//		boolean retry = false;
//		// Date input
//		while (!retry) {
//			try {
//				String date = DemoCinema.sc.next();
//				premiere = LocalDate.parse(date, formatter);
//				retry = true;
//			} catch (DateTimeParseException e) {
//				System.out.print("��������� ������. �������� ������:");
//				retry = false;
//			}
//		}
//		System.out.println("�������� ��������� ��: ");
//		for (movieCategories c : movieCategories.values()) {
//			System.out.println(c.name());
//		}
//		movieCategories category = movieCategories.valueOf(DemoCinema.sc.next().toUpperCase());
//
//		
//		String cat = DemoCinema.sc.next();
//		while(isInputCategoryWrong(cat)) {
//			System.out.println("�������� ������");
//			cat = DemoCinema.sc.next();
//		}
//
		Movie movie;
		switch (genre) {
		case "��������":
			movie = new Movie(name, length, premiere, category);
			movie.setGenre(genre);
			movie.startTimes = LocalTime.of(9, 0);
			movie.endTimes = LocalTime.of(18, 20).minusMinutes(length);
			movie.freeHours = movie.fillInFreeHours();
			return movie;
		case "�������":
		case "�������":
		case "����������":
			movie = new Movie(name, length, premiere, category);
			movie.setGenre(genre);
			movie.startTimes = LocalTime.of(12, 20);
			movie.endTimes = LocalTime.of(00, 00).minusMinutes(length);
			movie.freeHours = movie.fillInFreeHours();
			return movie;
		case "�����������":
		case "������":
		case "�����":
		case "�����":
		case "����������":
		case "������ ���������":
		case "������������":
		case "�����":
		case "����������":
			Movie.maxProjections = 4;;
			movie = new Movie(name, (short) length, premiere, category);
			movie.setGenre(genre);
			movie.endTimes = LocalTime.of(23, 50);
			movie.startTimes = movie.endTimes.minusMinutes(maxProjections * (movie.length + BREAK_BETWEEN_MOVIES));
			movie.freeHours = movie.fillInFreeHours();
			// TODO write to file
			return movie;
		}
		throw new NotValidMovieGenreException("���� ����� ���� ����!");

	}

	public void setTimes() {
		// TODO choose theather
		if (this.projections.size() > 0) {
			System.out.println("�������� ���������: ");
			this.projections.forEach(projection -> System.out.println(projection));
		} else {
			System.out.println("��� ��� ���� �������� ��������� �� ���� ����, � ���� ����");
		}
		if (this.projections.size() >= maxProjections) {
			System.out.println("���� �������� ������ �� ���� ���!");
			return;
		}
		System.out.println(
				"����� ��������� ������ �� ��������? �� ������ �� " + (maxProjections - this.projections.size()));
		String strNumber = DemoCinema.sc.next();
		String regex = "[0-9]+";
		while (!(strNumber.matches(regex) && Integer.parseInt(strNumber) <= maxProjections - this.projections.size())) {
			System.out.println("��������� ���������!");

			System.out.println("����� ��������� ������ �� ��������: ");
			strNumber = DemoCinema.sc.next();
		}
		int number = Integer.parseInt(strNumber);
		while (this.projections.size() < number) {
			this.listFreeHours();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
			System.out.print("�������� ��� � ������: (h:mm)");
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
					System.out.print("��������� ���! �������� ������. ");
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
		System.out.println("������ ��� �������� ������� ������: ");
		this.freeHours.forEach(hour -> System.out.println(hour));
	}

	private boolean isValidMovieCategory(String category) {
		if (category != null) {
			for (String cat : MOVIE_CATEGORIES) {
				if (cat.equalsIgnoreCase(category)) {
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
				+ this.getClass().getSimpleName() + ", category=" + category + "]";
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	private void setGenre(String genre) {
		this.genre = genre;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public LocalDate getPremiere() {
		return premiere;
	}

	public void setPremiere(LocalDate premiere) {
		this.premiere = premiere;
	}
	public Set<LocalTime> getFreeHours() {
		return freeHours;
	}

	public void setFreeHours(Set<LocalTime> freeHours) {
		this.freeHours = freeHours;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Movie m) {
		return this.getName().compareTo(m.getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + length;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((premiere == null) ? 0 : premiere.hashCode());
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
		Movie other = (Movie) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (length != other.length)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (premiere == null) {
			if (other.premiere != null)
				return false;
		} else if (!premiere.equals(other.premiere))
			return false;
		return true;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
