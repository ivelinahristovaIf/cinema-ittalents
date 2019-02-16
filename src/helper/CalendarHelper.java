package helper;

import java.time.LocalDate;
import java.util.InputMismatchException;

import cinema.DemoCinema;

public class CalendarHelper {
	public static final long NUMBER_DAYS_IN_CALENDAR = 10;
	private static CalendarHelper instance = null;

	public CalendarHelper() {
	}

	public static CalendarHelper getInstance() {
		if (instance == null) {
			instance = new CalendarHelper();
		}
		return instance;
	}
	public void showWeeksCalendar() {
		LocalDate localDate = LocalDate.now();
		System.out.println("Календар за седмицата: ");
		while (localDate.isBefore(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
			System.out.println(
					localDate.getDayOfMonth() + "." + localDate.getMonthValue() + " - " + localDate.getDayOfWeek());
			localDate = localDate.plusDays(1);
		}
	}
	public LocalDate inputDate() {
		System.out.print("Моля изберете дата, на която да добавите прожекциите: ");
		boolean retry = false;
		LocalDate date = null;
		while (!retry) {
			try {
				String d = DemoCinema.sc.next();
				String[] n = d.split("\\.");
				Byte day = Byte.parseByte(n[0]);
				Byte month = Byte.parseByte(n[1]);
				date = LocalDate.of(LocalDate.now().getYear(), month, day);
				if ((date.isAfter(LocalDate.now()) || date.equals(LocalDate.now()))
						&& !date.isAfter(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
					retry = true;
					break;
				}
				System.out.println("Невалидна дата моля опитайте отново");
			} catch (InputMismatchException | NumberFormatException e) {
				System.err.println("Невалиден формат на датата! Опитайте отново:");
				retry = false;
			}
		}
		return date;
	}
}
