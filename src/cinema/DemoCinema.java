package cinema;

import java.security.NoSuchAlgorithmException;
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

	static Scanner sc = new Scanner(System.in);

	// TODO how to make consumers non static
//	private static Set<Consumer> consumers;

	public static void menu() {
		System.out.println("�������� ����� �� �������� ������: ");
		System.out.println("1 -> ���� �� �������������...");
		System.out.println("2 -> ���� �� ����������...");
		System.out.println("3 -> ������������ �� ��� ����������...");
		try {
			int option = sc.nextInt();

			if (option == 1) {
				System.out.println("�������������");
				System.out.println("�������� ������������� ���: ");
				String username = sc.next();
				System.out.println("�������� ������: ");
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
			if (option == 2) {
				System.out.println("����������");
				System.out.println("�������� email : ");
				String email = sc.next();
				System.out.println("�������� ������: ");
				String password = sc.next();
				if (isValidEmailAndPasswor(email, password)) {
					// TODO get consumer by email and password
					// TODO show menu for consumer
				}
			}
			if (option == 3) {
				System.out.println("������������� �� ���� ����������...");
				Consumer consumer = new Consumer(1);
				// TODO add consumer to Cinema
				System.out.println(consumer + " � �����������");
				if (Cinema.consumers == null) {
					Cinema.consumers = new HashSet<Consumer>();
				}
				Cinema.consumers.add(consumer);
				// TODO showConsumerMenu is in option 2
				consumer.showConsumerMenu();
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private static boolean isValidEmailAndPasswor(String email, String password) {
		// TODO validate email and password
		return true;
	}

	private static boolean isValidUsernameAndPassword(String username, String password)
			throws NoSuchAlgorithmException {
		if (username != null) {
			if (password != null) {
				while (!(username.equals(Cinema.admin.getUsername())
						&& Cryptography.cryptSHA256(password).equals(Cinema.admin.getPassword()))) {
					System.err.println("������ ������������� ��� ��� ������!");
					System.err.println("�������� � �� ������� ����� ��� ������ ��� �������� ������...");

					System.out.println("�������� ������������� ��� : ");
					username = sc.next();
					if (username.equalsIgnoreCase("x")) {
						menu();
						return false;
					}
					System.out.println("�������� ������: ");
					password = sc.next();

					if (password.equalsIgnoreCase("x")) {
						menu();
						return false;
					}

				}
				System.out.println("�������� �� ������� ���� �������������!");
				return true;
			}
			System.out.println("Null admin password");
			return false;
		}
		System.err.println("Null admin username");
		return false;
	}

	public static void main(String[] args) {
		try {
			Ticket.getInstance(ticketType.STANDART_TICKET, "A15", new Cinema());
//			System.out.println(ticket);
		} catch (NotValidTicketTypeException e) {
			e.printStackTrace();
		}
		System.out.println("����� ����� � ���� �����!");
		menu();
		System.out.println();

	}

}
