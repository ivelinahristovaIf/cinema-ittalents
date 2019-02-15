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
<<<<<<< HEAD
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
=======
			String option = sc.next();
			String regex = "[0-3]+";
			if(option.matches(regex)) {
				if (Integer.parseInt(option) == 1) {
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
				if (Integer.parseInt(option) == 2) {
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
				if (Integer.parseInt(option) == 3) {
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
				if (Integer.parseInt(option) == 0) {
					System.exit(0);
				} 
			}else {
				System.out.println("��������� �����, �������� ���");
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
		Cinema cinema = new Cinema();


		
		try {
			Ticket.getInstance(ticketType.STANDART_TICKET, "A15", Cinema.getInstance());
		} catch (NotValidTicketTypeException e) {
			e.printStackTrace();
		}
		System.out.println("����� ����� � ���� �����!");
		//TODO load all from file into storage
		System.out.println();
		menu();
<<<<<<< HEAD
		//TODO save all to file
		System.out.println("����");
=======
		System.out.println();


>>>>>>> fb06fde8333fae600dcfb72f91cb2840e2cfbfe6

	}

}
