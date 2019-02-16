package view;

import bean.MovieTheatherType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class MovieTheatherPanel extends GridPane {
	private Label type;
	private Label video;
	private Label audio;
	private ComboBox<String> types;
	private ComboBox<String> videoFormats;
	private ComboBox<String> audioFormats;
	private Button save;

	private String choosenType;
	private String choosenVideo;
	private String choosenAudio;

	public MovieTheatherPanel() {
		super();
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.type = new Label("Тип:");
		this.video = new Label("Видео формат:");
		this.audio = new Label("Аудио формат:");
		
		ObservableList<String> types = FXCollections.observableArrayList("IMAX", "VIP", "LUXE", "PREMIUM");
		this.types = new ComboBox<String>(types);
		this.types.setVisibleRowCount(4);
		
		ObservableList<String> videos = FXCollections.observableArrayList("Real-D-3D", "2D", "HFR-3D", "4D");
		this.videoFormats = new ComboBox<String>(videos);
		this.videoFormats.setVisibleRowCount(4);
		
		ObservableList<String> audios = FXCollections.observableArrayList("Dolby Digital", "Dolby Atmos");
		this.audioFormats = new ComboBox<String>(audios);
		this.audioFormats.setVisibleRowCount(2);
		this.save = new Button("Запази");

		this.types.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleTypeComboBox(event);
			}
		});
		
		this.videoFormats.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleVideoComboBox(event);
			}
		});
		this.audioFormats.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleAudioComboBox(event);
			}
		});

		
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MovieTheatherType movieTheather = null;
//				try {
//					movieTheather = ;//TODO
		
//				} catch (NotValidMovieGenreException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				System.out.println(movieTheather);
			}
		});
		add(type, 0, 0);
		add(this.types, 1, 0);
		add(video, 0, 1);
		add(videoFormats, 1, 1);
		add(audio, 0, 2);
		add(audioFormats, 1, 2);
		add(save, 0, 3);
		
	}

	protected void handleAudioComboBox(ActionEvent event) {
		this.choosenAudio = this.audioFormats.getValue().toString();
	}

	protected void handleVideoComboBox(ActionEvent event) {
		this.choosenVideo = this.videoFormats.getValue().toString();
	}

	protected void handleTypeComboBox(ActionEvent event) {
		this.choosenType = this.types.getValue().toString();
	}
}
