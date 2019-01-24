package tickets;

public class NotValidTicketTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5472238522669280091L;

	public NotValidTicketTypeException(String str) {
		System.out.println(str);
	}

}
