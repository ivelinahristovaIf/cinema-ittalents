package cinema;

import java.util.InputMismatchException;
import java.util.Scanner;

import bean.Admin;
import bean.Cinema;
import helper.NotValidMovieTheatherTypeException;
import tickets.NotValidTicketTypeException;
import tickets.Ticket;
import tickets.Ticket.ticketType;

public class DemoCinema {

	public static Scanner sc = new Scanner(System.in);

	public static void menu() {
		System.out.println("Изберете опция от главното менюто: ");
		System.out.println("1 -> Вход за администратор...");
		System.out.println("2 -> Вход за потребител...");
		System.out.println("3 -> Регистриране на нов потребител...");
		System.out.println("0 -> Изход...");
		try {
<<<<<<< HEAD
			int option = sc.nextInt();
			if (option == 1) {
				System.out.println("Администратор");
				System.out.println("Въведете потребителско име: ");
				String username = sc.next();
				System.out.println("Въведете парола: ");
				String password = sc.next();
			
					if (isValidUsernameAndPassword(username, password)) {
						Admin.getInstance().showMenu();
					}
			
			}
			if (option == 2) {
				System.out.println("Потребител");
				System.out.println("Въведете email : ");
				String email = sc.next();
				System.out.println("Въведете парола: ");
				String password = sc.next();

//				if (consumer != null) {
//					consumer.showConsumerMenu();
//				}

			}
			if (option == 3) {
				System.out.println("Регистрирайте се като потребител...");
//				Consumer consumer = new Consumer();//TODO open register scene
//				System.out.println(consumer + " е регистриран");

				System.out.println("1 -> За да продължите действия...");
				System.out.println("2 -> За да се отпишете...");
				System.out.println("0 -> Изход...");

				int next = sc.nextInt();
				switch (next) {
				case 0:
				//TODO
					break;
				case 1:
//					consumer.showConsumerMenu();
					break;
				case 2:
					DemoCinema.menu();
					break;
				}
			}
			if (option == 0) {
				System.exit(0);
=======
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
>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6
			}

		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	private static boolean isValidUsernameAndPassword(String username, String password){
		if (username != null) {
			if (password != null) {
				while (!(username.equals(Admin.getInstance().getUsername())
						&& password.equals(Admin.getInstance().getPassword()))) {
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
			Ticket.getInstance(ticketType.STANDART_TICKET, "A15", Cinema.getInstance());
		} catch (NotValidTicketTypeException e) {
			e.printStackTrace();
		}
		System.out.println("Добре дошли в Кино Арена!");
		//TODO load all from file into storage
		System.out.println();
		menu();
<<<<<<< HEAD
		//TODO save all to file
		System.out.println("Край");
=======
		System.out.println();


>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6

	}

}
