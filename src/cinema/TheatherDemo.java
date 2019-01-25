package cinema;

import java.time.LocalDate;

import tickets.NotValidTicketTypeException;

public class TheatherDemo {

	public static void main(String[] args) {
		MovieTheather mt = new MovieTheather();
		try {
			mt.showSeatsInTheathre();
		} catch (NotValidTicketTypeException e) {
			e.printStackTrace();
		}
		Movie m = new Movie(1, "It", (short) 125, LocalDate.of(2019, 2, 12), Cinema.movieGenres.ÓÆÀÑÈ,
				Cinema.movieCategories.C);
		System.out.println(m);

	}

}
