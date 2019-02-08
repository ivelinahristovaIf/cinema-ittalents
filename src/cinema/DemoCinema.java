package cinema;

import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import crypt.Cryptography;
import tickets.NotValidTicketTypeException;
import tickets.Ticket;
import tickets.Ticket.ticketType;
import users.Admin;
import users.Consumer;

public class DemoCinema {

	public static Scanner sc = new Scanner(System.in);

	// TODO how to make consumers non static
//	private static Set<Consumer> consumers;

	public static void menu() {
		System.out.println("Изберете опция от главното менюто: ");
		System.out.println("1 -> Вход за администратор...");
		System.out.println("2 -> Вход за потребител...");
		System.out.println("3 -> Регистриране на нов потребител...");
		System.out.println("0 -> Изход...");
		try {
			String option = sc.next();
			String regex = "[0-3]+";
			if(option.matches(regex)) {
				if (Integer.parseInt(option) == 1) {
					System.out.println("Администратор");
					System.out.println("Въведете потребителско име: ");
					String username = sc.next();
					System.out.println("Въведете парола: ");
					String password = sc.next();
					try {
						if (isValidUsernameAndPassword(username, password)) {
							Cinema.admin.showMenu();
						}
					} catch (NoSuchAlgorithmException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
				if (Integer.parseInt(option) == 2) {
					System.out.println("Потребител");
					System.out.println("Въведете email : ");
					String email = sc.next();
					System.out.println("Въведете парола: ");
					String password = sc.next();
					if (isValidEmailAndPasswor(email, password)) {
						// TODO get consumer by email and password
						// TODO show menu for consumer
						
					}
				}
				if (Integer.parseInt(option) == 3) {
					System.out.println("Регистрирайте се като потребител...");
					Consumer consumer = new Consumer(1);
					// TODO add consumer to Cinema
					System.out.println(consumer + " е регистриран");
					if (Cinema.consumers == null) {
						Cinema.consumers = new HashSet<Consumer>();
					}
					Cinema.consumers.add(consumer);
					// TODO showConsumerMenu is in option 2
					consumer.showConsumerMenu();
				}
				if (Integer.parseInt(option) == 0) {
					System.exit(0);
				} 
			}else {
				System.out.println("Невалиден номер, опитайте пак");
				menu();
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private static boolean isValidEmailAndPasswor(String email, String password) {
		return true;
	}

	private static boolean isValidUsernameAndPassword(String username, String password)
			throws NoSuchAlgorithmException {
		if (username != null) {
			if (password != null) {
				while (!(username.equals(Cinema.admin.getUsername())
						&& Cryptography.cryptSHA256(password).equals(Cinema.admin.getPassword()))) {
					System.err.println("Грешно потребителско име или парола!");
					System.err.println("Въведете Х за връщане назад към менюто или опитайте отново...");

					System.out.println("Въведете потребителско име : ");
					username = sc.next();
					if (username.equalsIgnoreCase("x")) {
						menu();
						return false;
					}
					System.out.println("Въведете парола: ");
					password = sc.next();

					if (password.equalsIgnoreCase("x")) {
						menu();
						return false;
					}

				}
				System.out.println("Вписахте се успешно като администратор!");
				return true;
			}
			System.out.println("Null admin password");
			return false;
		}
		System.err.println("Null admin username");
		return false;
	}

	public static void main(String[] args) throws NotValidMovieTheatherTypeException, NotValidTicketTypeException {
		Cinema cinema = new Cinema();


		
		try {
			Ticket.getInstance(ticketType.STANDART_TICKET, "A15", new Cinema());
//			System.out.println(ticket);
		} catch (NotValidTicketTypeException e) {
			e.printStackTrace();
		}
		System.out.println("Добре дошли в Кино Арена!");
		System.out.println();
		menu();
		System.out.println();



	}

}
