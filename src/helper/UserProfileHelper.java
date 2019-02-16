package helper;

import cinema.DemoCinema;

public class UserProfileHelper {
	public enum Interests {
		music("������"), books("�����"), games("����"), fun("����������"), cars("����������"), science("�����"),
		technology("����������"), history("�������"), family("���������"), health("������"), business("������"),
		socialNetworks("�������� �����"), sport("�����"), trip("��������"), fasion("����"), food("�����"),
		pets("�������_�������"), series("�������"), adrenaline("���������");
		private String name;

		private Interests(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	private static UserProfileHelper instance = null;
	
	public static UserProfileHelper getInstance() {
		if(instance == null) {
			instance = new UserProfileHelper();
		}
		return instance;
	}
	public MovieGenres addFavouriteGenre() {
		System.out.println("���� �������� ����� ���� �� �������: ");
		for (int i = 1; i < MovieGenres.values().length; i++) {
			System.out.println(i + " - " + MovieGenres.values()[i - 1].getName());
		}
		
//		TODO validate sc number
		MovieGenres favGenre = MovieGenres.values()[DemoCinema.sc.nextInt() - 1];
		return favGenre;
	}
	public Interests addPersonalInterest() {
		System.out.println("���� �������� �������: ");
		for (int index = 1; index <= Interests.values().length; index++) {
			System.out.println(index + " - " + Interests.values()[index - 1].getName());
		}
		Interests interest = Interests.values()[DemoCinema.sc.nextInt() - 1];
		return interest;
	}
}
