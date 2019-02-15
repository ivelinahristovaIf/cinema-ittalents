package helper;

public class InvalidHourException extends Exception {
	private static final long serialVersionUID = 6501229486882563913L;
	

	public InvalidHourException() {
		super();
	}

	public InvalidHourException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidHourException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidHourException(String message) {
		super(message);
	}

	public InvalidHourException(Throwable cause) {
		super(cause);
	}
	
	
}
