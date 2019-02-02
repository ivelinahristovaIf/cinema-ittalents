package cinema;

public class NotValidMovieGenreException extends Exception {
	private static final long serialVersionUID = 729226502775235228L;

	public NotValidMovieGenreException() {
		super();
	}

	public NotValidMovieGenreException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NotValidMovieGenreException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotValidMovieGenreException(String arg0) {
		super(arg0);
	}

	public NotValidMovieGenreException(Throwable arg0) {
		super(arg0);
	}
	

}
