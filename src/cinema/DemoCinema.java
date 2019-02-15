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
		System.out.println("�������� ����� �� �������� ������: ");
		System.out.println("1 -> ���� �� �������������...");
		System.out.println("2 -> ���� �� ����������...");
		System.out.println("3 -> ������������ �� ��� ����������...");
		System.out.println("0 -> �����...");
		try {
			int option = sc.nextInt();
			if (option == 1) {
				System.out.println("�������������");
				System.out.println("�������� ������������� ���: ");
				String username = sc.next();
				System.out.println("�������� ������: ");
				String password = sc.next();
			
					if (isValidUsernameAndPassword(username, password)) {
						Admin.getInstance().showMenu();
					}
			
			}
			if (option == 2) {
				System.out.println("����������");
				System.out.println("�������� email : ");
				String email = sc.next();
				System.out.println("�������� ������: ");
				String password = sc.next();

//				if (consumer != null) {
//					consumer.showConsumerMenu();
//				}

			}
			if (option == 3) {
				System.out.println("������������� �� ���� ����������...");
//				Consumer consumer = new Consumer();//TODO open register scene
//				System.out.println(consumer + " � �����������");

				System.out.println("1 -> �� �� ���������� ��������...");
				System.out.println("2 -> �� �� �� ��������...");
				System.out.println("0 -> �����...");

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

	public static void main(String[] args) throws NotValidMovieTheatherTypeException, NotValidTicketTypeException {
		try {
			Ticket.getInstance(ticketType.STANDART_TICKET, "A15", Cinema.getInstance());
		} catch (NotValidTicketTypeException e) {
			e.printStackTrace();
		}
		System.out.println("����� ����� � ���� �����!");
		//TODO load all from file into storage
		System.out.println();
		menu();
		//TODO save all to file
		System.out.println("����");

	}

}
