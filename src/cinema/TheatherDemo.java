package cinema;

import java.time.LocalDate;

import tickets.NotValidTicketTypeException;

public class TheatherDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovieTheather mt = new MovieTheather();
		try {
			mt.showSeatsInTheCinema();
		} catch (NotValidTicketTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Movie m = new Movie("It", (short) 125, LocalDate.of(2019, 2, 12), Movie.movieGenres.ÓÆÀÑÈ, Movie.movieCategories.C);
		System.out.println(m);

	}

}
