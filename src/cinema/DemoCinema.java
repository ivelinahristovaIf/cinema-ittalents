package cinema;

import java.util.Scanner;

import bean.Admin;
import helper.NotValidMovieTheatherTypeException;
import helper.NotValidTicketTypeException;

public class DemoCinema {

	public static Scanner sc = new Scanner(System.in);

	public static void menu() {
		System.out.println("�������� ����� �� �������� ������: ");
		System.out.println("1 -> ���� �� �������������...");
		System.out.println("2 -> ���� �� ����������...");
		System.out.println("3 -> ������������ �� ��� ����������...");
		System.out.println("0 -> �����...");
//		try {
//			int option = sc.nextInt();
//			if (option == 1) {
//				System.out.println("�������������");
//				System.out.println("�������� ������������� ���: ");
//				String username = sc.next();
//				System.out.println("�������� ������: ");
//				String password = sc.next();
//			
//					if (isValidUsernameAndPassword(username, password)) {
//						Admin.getInstance().showMenu();
//					}
//			
//			}
//			if (option == 2) {
//				System.out.println("����������");
//				System.out.println("�������� email : ");
//				String email = sc.next();
//				System.out.println("�������� ������: ");
//				String password = sc.next();
//
////				if (consumer != null) {
////					consumer.showConsumerMenu();
////				}
//
//			}
//			if (option == 3) {
//				System.out.println("������������� �� ���� ����������...");
////				Consumer consumer = new Consumer();//TODO open register scene
////				System.out.println(consumer + " � �����������");
//
//				System.out.println("1 -> �� �� ���������� ��������...");
//				System.out.println("2 -> �� �� �� ��������...");
//				System.out.println("0 -> �����...");
//
//				int next = sc.nextInt();
//				switch (next) {
//				case 0:
//				//TODO
//					break;
//				case 1:
////					consumer.showConsumerMenu();
//					break;
//				case 2:
//					DemoCinema.menu();
//					break;
//				}
//			}
//			if (option == 0) {
//				System.exit(0);
//
			String option = sc.next();
			String regex = "[0-3]+";
			if(option.matches(regex)) {
				if (Integer.parseInt(option) == 1) {
					System.out.println("�������������");
					System.out.println("�������� ������������� ���: ");
					String username = sc.next();
					System.out.println("�������� ������: ");
					String password = sc.next();
						if (isValidEmailAndPassword(username, password)) {
//							Cinema.admin.showMenu();
						}

				}
				if (Integer.parseInt(option) == 2) {
					System.out.println("����������");
					System.out.println("�������� email : ");
					String email = sc.next();
					System.out.println("�������� ������: ");
					String password = sc.next();
					if (isValidEmailAndPassword(email, password)) {
						// TODO get consumer by email and password
						// TODO show menu for consumer
						
					}
				}
				if (Integer.parseInt(option) == 3) {
					System.out.println("������������� �� ���� ����������...");
//					Consumer consumer = new Consumer(1);
					// TODO add consumer to Cinema
//					System.out.println(consumer + " � �����������");
//					if (Cinema.consumers == null) {
//						Cinema.consumers = new HashSet<Consumer>();
//					}
//					Cinema.consumers.add(consumer);
//					// TODO showConsumerMenu is in option 2
//					consumer.showConsumerMenu();
				}
				if (Integer.parseInt(option) == 0) {
					System.exit(0);
				} 
			}else {
				System.out.println("��������� �����, �������� ���");
				menu();

			}
	}

	private static boolean isValidEmailAndPassword(String email, String password) {
		if (email != null) {
			if (password != null) {
				while (!(email.equals(Admin.getInstance().getEmail())
						&& password.equals(Admin.getInstance().getPassword()))) {
					System.err.println("������ ������������� ��� ��� ������!");
					System.err.println("�������� � �� ������� ����� ��� ������ ��� �������� ������...");

					System.out.println("�������� ������������� ��� : ");
					email = sc.next();
					if (email.equalsIgnoreCase("x")) {
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
//		Cinema cinema = new Cinema();
//		MovieTheather mt = new MovieTheather();
//		try {
//			Ticket.getInstance(ticketType.STANDART_TICKET, "A15", mt);
//		} catch (NotValidTicketTypeException e) {
//			e.printStackTrace();
//		}
		System.out.println("����� ����� � ���� �����!");
		//TODO load all from file into storage
		System.out.println();
		menu();

		//TODO save all to file
		System.out.println("����");
	}

}
