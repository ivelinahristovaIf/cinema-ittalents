package cinema;

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

	}

}
