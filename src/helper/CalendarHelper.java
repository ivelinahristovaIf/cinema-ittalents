package helper;

import java.time.LocalDate;
import java.util.InputMismatchException;

import cinema.DemoCinema;

public class CalendarHelper {
	private static final long NUMBER_DAYS_IN_CALENDAR = 10;
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
		System.out.println("�������� �� ���������: ");
		while (localDate.isBefore(LocalDate.now().plusDays(NUMBER_DAYS_IN_CALENDAR))) {
			System.out.println(
					localDate.getDayOfMonth() + "." + localDate.getMonthValue() + " - " + localDate.getDayOfWeek());
			localDate = localDate.plusDays(1);
		}
	}
	public LocalDate inputDate() {
		System.out.print("���� �������� ����, �� ����� �� �������� �����������: ");
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
				System.out.println("��������� ���� ���� �������� ������");
			} catch (InputMismatchException | NumberFormatException e) {
				System.err.println("��������� ������ �� ������! �������� ������:");
				retry = false;
			}
		}
		return date;
	}
}
