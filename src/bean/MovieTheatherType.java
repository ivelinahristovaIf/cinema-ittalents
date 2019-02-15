package bean;

public class MovieTheatherType {
	
	private int id;
	private String type;
	private String videoFormat;
	private String audioFormat;
	
	public MovieTheatherType() {
		super();
	}
	
	public MovieTheatherType(int id, String type, String videoFormat, String audioFormat) {
		super();
		this.id = id;
		this.type = type;
		this.videoFormat = videoFormat;
		this.audioFormat = audioFormat;
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
	
	
}
