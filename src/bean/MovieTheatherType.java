package bean;

import cinema.DemoCinema;
import helper.NotValidMovieTheatherTypeException;

public class MovieTheatherType {
	public static final String[] MOVIE_THEATHER_TYPE = { "IMAX", "VIP", "LUXE", "PREMIUM" };
	public static final String[] VIDEO_FORMAT = { "Real-D-3D", "2D", "HFR-3D", "4D" };
	public static final String[] AUDIO_FORMAT = { "Dolby Digital", "Dolby Atmos" };

	private int id;
	private static int nextId = 1;
	private String type;
	private String videoFormat;
	private String audioFormat;

	public MovieTheatherType() {
		super();
		this.id = nextId++;
	}

	public MovieTheatherType(String type, String videoFormat, String audioFormat) {
		this.id = nextId++;
		if (isValidTheatherType(type)) {
			this.type = type;
		}
		if (isValidVideoFormat(videoFormat)) {
			this.videoFormat = videoFormat;
		}
		if (isValidAudioFormat(audioFormat)) {
			this.audioFormat = audioFormat;
		}
	}

//	public static MovieTheatherType getInstance() throws NotValidMovieTheatherTypeException {
//		System.out.println("Изберете формат на залата от: ");
//		for (int index = 1; index <= MOVIE_THEATHER_TYPE.length; index++) {
//			System.out.println(index + " - " + MOVIE_THEATHER_TYPE[index - 1]);
//		}
//
//		String type = MOVIE_THEATHER_TYPE[inputMovieTheaterFields(MOVIE_THEATHER_TYPE)];
//
//		System.out.println("Изберете видео формат от: ");
//		for (int index = 1; index <= VIDEO_FORMAT.length; index++) {
//			System.out.println(index + " - " + VIDEO_FORMAT[index - 1]);
//		}
//		String videoFormat = VIDEO_FORMAT[inputMovieTheaterFields(VIDEO_FORMAT)];
//
//		System.out.println("Изберете аудио формат от: ");
//		for (int index = 1; index <= AUDIO_FORMAT.length; index++) {
//			System.out.println(index + " - " + AUDIO_FORMAT[index - 1]);
//		}
//		String audioFormat = AUDIO_FORMAT[inputMovieTheaterFields(AUDIO_FORMAT)];
//
//		return new MovieTheatherType(type, videoFormat, audioFormat);
//
//	}

	// try again.. sushto taka izbqgva arrayIndexOutOfBoundsException
	// i InputMismatchException ako se vuvede neshto gadno
	private static int inputMovieTheaterFields(String[] str) {
		int lenght = str.length;
		String typeOption;
		String regex = "[0-9]+";
		int count = 0;
		do {
			if (count > 0) {
				System.out.println("Няма зала с такъв номер. Моля опитайте пак");
			}
			typeOption = DemoCinema.sc.next();
			if (typeOption.matches(regex)) {
				if (Integer.parseInt(typeOption) >= 1 && Integer.parseInt(typeOption) <= lenght) {
					break;
				}
			} else {
				System.out.println("Невалидно въвеждане, моля въведете число");
			}
			count++;
		} while (true);
		return Integer.parseInt(typeOption) - 1;
	}

	private boolean isValidTheatherType(String type) {
		for (int i = 0; i < MOVIE_THEATHER_TYPE.length; i++) {
			if (type.equalsIgnoreCase(MOVIE_THEATHER_TYPE[i])) {
				return true;
			}
		}
		return false;
	}

	private boolean isValidVideoFormat(String videoFormat) {
		if (videoFormat != null && videoFormat.length() > 1) {
			for (int i = 0; i < VIDEO_FORMAT.length; i++) {
				if (videoFormat.equalsIgnoreCase(VIDEO_FORMAT[i])) {
					return true;
				}
			}
		}
		System.err.println("Невалиден видео формат!");
		return false;
	}

	private boolean isValidAudioFormat(String audioFormat) {
		if (audioFormat != null && audioFormat.length() > 3) {
			for (int i = 0; i < AUDIO_FORMAT.length; i++) {
				if (audioFormat.equalsIgnoreCase(AUDIO_FORMAT[i])) {
					return true;
				}
			}
		}
		System.err.println("Невалиден аудио формат!");
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVideoFormat() {
		return videoFormat;
	}

	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}

	public String getAudioFormat() {
		return audioFormat;
	}

	public void setAudioFormat(String audioFormat) {
		this.audioFormat = audioFormat;
	}

	@Override
	public String toString() {
		return "MovieTheatherType [id=" + id + ", type=" + type + ", videoFormat=" + videoFormat + ", audioFormat="
				+ audioFormat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((audioFormat == null) ? 0 : audioFormat.hashCode());
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((videoFormat == null) ? 0 : videoFormat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieTheatherType other = (MovieTheatherType) obj;
		if (audioFormat == null) {
			if (other.audioFormat != null)
				return false;
		} else if (!audioFormat.equals(other.audioFormat))
			return false;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (videoFormat == null) {
			if (other.videoFormat != null)
				return false;
		} else if (!videoFormat.equals(other.videoFormat))
			return false;
		return true;
	}

}
