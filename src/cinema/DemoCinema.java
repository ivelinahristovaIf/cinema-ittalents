package cinema;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import tickets.NotValidTicketTypeException;
import tickets.Ticket;
import tickets.Ticket.ticketType;
import users.Admin;
import users.Consumer;

public class DemoCinema {
	
	static Admin admin = Admin.createAdmin("admin", "admin");
	
	
//	public static Admin admin = DemoCinema.createAdmin("admin", "admin");
	
	// TODO how to make consumers non static
	private static Set<Consumer> consumers;

	public static void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Изберете опция от менюто: ");
		System.out.println("1 -> Вход за администратор...");
		System.out.println("2 -> Вход за потребител...");
		System.out.println("3 -> Регистриране на нов потребител...");
		int option = sc.nextInt();
		if (option == 1) {
			System.out.println("Администратор");
			System.out.println("Въведете потребителско име: ");
			String username = sc.next();
			System.out.println("Въведете парола: ");
			String password = sc.next();
			if (isValidUsernameAndPassword(username, password)) {
				admin.showMenu();
			}
		}
		if (option == 2) {
			System.out.println("Потребител");
			System.out.println("Въведете email : ");
			String username = sc.next();
			System.out.println("Въведете парола: ");
			String password = sc.next();
		}
		if (option == 3) {
			System.out.println("Регистрирайте се като потребител...");
			Consumer consumer = new Consumer(1);
			// TODO add consumer to Cinema
			System.out.println(consumer + " е регистриран");
			consumers.add(consumer);
		}

	}

	private static boolean isValidUsernameAndPassword(String username, String password) {
		if (username != null) {
			if (password != null) {
				if (username.equals(DemoCinema.admin.getUsername()) && password.equals(admin.getPassword())) {
					System.out.println("Вписахте се успешно като администратор!");
					return true;
				}
			}
			System.out.println("Null admin password");
			return false;
		}
		System.err.println("Null admin username");
		return false;
	}

//	public static Admin createAdmin(String username, String password) {
//		// TODO Singleton
//		// TODO is correct username and password
//		return new Admin(username, password);
//	}

	public static void main(String[] args) {
		try {
			Ticket ticket = Ticket.getInstance(ticketType.STANDART_TICKET, "A15", new Cinema());
			System.out.println(ticket);
		} catch (NotValidTicketTypeException e) {
			e.printStackTrace();
		}
		consumers = new HashSet<Consumer>();
		menu();

	}

}
