package helper;

public enum MovieGenres {
	drama("дпюлю"), horor("сфюях"), comedy("йнледхъ"), anime("юмхлюжхъ"), action("ейьзм"), fantasy("тюмрюярхйю"),
	biography("ахнцпютхвем"), adventure("опхйкчвемяйх"), romance("пнлюмрхвем"), crime("йпхлхмюкем"),
	military("бнемем"), science("мюсвмн оноскъпем"), musical("лчгхйзк");

	private String name;

	private MovieGenres(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
