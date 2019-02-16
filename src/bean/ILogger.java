package bean;

public interface ILogger {
	public static final int ADMIN = 2;
	public static final int USER = 1;

	public abstract void setType();

	public abstract int getType();

	public abstract String getEmail();

	public abstract String getPassword();
}
