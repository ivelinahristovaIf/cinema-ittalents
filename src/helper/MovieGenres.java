package helper;

public enum MovieGenres {
	drama("�����"), horor("�����"), comedy("�������"), anime("��������"), action("�����"), fantasy("����������"),
	biography("�����������"), adventure("������������"), romance("����������"), crime("����������"),
	military("������"), science("������ ���������"), musical("�������");

	private String name;

	private MovieGenres(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
