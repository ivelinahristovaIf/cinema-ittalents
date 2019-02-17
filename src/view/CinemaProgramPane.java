package view;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import bean.Cinema;
import bean.Movie;
import bean.MovieTheather;
import bean.MovieTheatherType;
import helper.CalendarHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class CinemaProgramPane extends GridPane {
	private Label movieTheather;
	private Label date;
	private Label movie;
	
	private DatePicker datePicker;
	private ComboBox<MovieTheather> movieTheathersComboBox; 
	private ComboBox<Movie> moviesComboBox;
	
	private Button save;
	
	public CinemaProgramPane() {
		super();
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.movieTheather = new Label("Избери зала:");
		this.date = new Label("Избери дата:");
		this.movie = new Label("Избери филм:");
		ObservableList<MovieTheather> movieTheathers = null;
		try {
			movieTheathers = FXCollections.observableArrayList(Cinema.getInstance().getAllMovieTheathers());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.movieTheathersComboBox = new ComboBox<MovieTheather>(movieTheathers);
		this.datePicker = new DatePicker();
		this.moviesComboBox = new ComboBox<Movie>();
		
		this.save = new Button("Добави");
		
		DatePicker startDate = new DatePicker();
		DatePicker endDate = new DatePicker();
		startDate.setValue(LocalDate.now());
		endDate.setValue(LocalDate.now().plusDays(CalendarHelper.NUMBER_DAYS_IN_CALENDAR));

		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						if (item.isBefore(startDate.getValue())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
						if (item.isAfter(endDate.getValue().plusDays(1))) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};

		datePicker.setDayCellFactory(dayCellFactory);
		datePicker.setValue(LocalDate.now());
		
		add(movieTheather, 0, 0);
		add(movieTheathersComboBox, 1, 0);
		add(date, 0, 1);
		add(datePicker, 1, 1);
		add(movie, 0, 2);
		add(moviesComboBox, 1, 2);
		add(save, 0, 3);
	}
}
