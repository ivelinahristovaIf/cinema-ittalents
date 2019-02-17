package bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import cinema.DemoCinema;
import helper.InvalidHourException;
import helper.MovieHelper;

public class Movie implements Comparable<Movie> {
	private static final short BREAK_BETWEEN_MOVIES = 20;
	private static final int MAX_PROJECTIONS = 5;
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
	private int numberOfProjectionsForDay;

	public Movie() {
		super();
	}

	public Movie(String genre, String name, short length, LocalDate premiere, String category) {
		this.id = nextId++;
		this.genre = genre;
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
		if (MovieHelper.getInstance().isValidPremiereDate(premiere)) {
			this.premiere = premiere;
		}

		if (MovieHelper.getInstance().isValidMovieCategory(category)) {
			this.category = category;
		}
		this.projections = new TreeSet<LocalTime>();
		this.freeHours = new TreeSet<LocalTime>();
		this.freeHours = this.fillInFreeHours(this);
	}

	public void setTimes() {
		if (this.projections.size() > 0) {
			System.out.println("Настоящи прожекции: ");
			this.projections.forEach(projection -> System.out.println(projection));
			System.out.println("Можете да добавите " + (MAX_PROJECTIONS - numberOfProjectionsForDay));
		} else {
			System.out.println("Все още няма добавени прожекции за този филм, в тази зала");
		}
		if (this.projections.size() >= MAX_PROJECTIONS) {
			System.out.println("Няма свободни часове за този ден!");
			return;
		}
		while (this.projections.size() < MAX_PROJECTIONS - numberOfProjectionsForDay) {
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

	private Set<LocalTime> fillInFreeHours(Movie movie) {
		Set<LocalTime> freeHours = new TreeSet<LocalTime>();
		movie.endTimes = LocalTime.of(23, 50).minusMinutes(movie.length);
		movie.startTimes = movie.endTimes.minusMinutes(MAX_PROJECTIONS * (movie.length + BREAK_BETWEEN_MOVIES));
		if (movie.startTimes != null && movie.endTimes != null) {
			System.out.println(startTimes);
			System.out.println(endTimes);
			LocalTime time = this.startTimes;
			int count = 0;
			while (count < MAX_PROJECTIONS) {
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

	@Override
	public String toString() {
		return "Movie [name=" + name + ", length: " + length + ", premiere=" + premiere + ", genre=" + genre
				+ ", category=" + category + "]";
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

	public int getNumberOfProjectionsForDay() {
		return numberOfProjectionsForDay;
	}

	public void setNumberOfProjectionsForDay(int numberOfProjectionsForDay) {
		this.numberOfProjectionsForDay = numberOfProjectionsForDay;
	}

	public Set<LocalTime> getProjections() {
		return projections;
	}

	public void setProjections(Set<LocalTime> projections) {
		this.projections = projections;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
