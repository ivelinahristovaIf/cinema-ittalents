package view;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import bean.Cinema;
import bean.Movie;
import bean.MovieTheather;
import bean.MovieTheatherType;
import helper.CalendarHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import writers.MovieTheaterWriter;
import writers.MovieWriter;

public class CinemaProgramPane extends GridPane {
	private Label movieTheather;
	private Label date;
	private Label movie;

	private DatePicker datePicker;
	private ComboBox<MovieTheather> movieTheathersComboBox;
	private ComboBox<Movie> moviesComboBox;

	private Button save;
	private MovieTheather selectedMovieTheather;
	private LocalDate choosenDate;
	private MovieTheather choosenTheather;

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
			MovieTheaterWriter.getInstance().getMovieTheatersFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movieTheathers = FXCollections.observableArrayList(MovieTheaterWriter.getInstance().getTheaters());
		this.movieTheathersComboBox = new ComboBox<MovieTheather>(movieTheathers);
//		movieTheathersComboBox.setValue(movieTheathers.get(0));
		movieTheathersComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MovieTheather>() {

			@Override
			public void changed(ObservableValue<? extends MovieTheather> observable, MovieTheather oldValue,
					MovieTheather newValue) {
				handleMovieTheatherComboBoxChange();
			}
		});
				
		
		this.datePicker = new DatePicker();

		DatePicker startDate = new DatePicker();
		DatePicker endDate = new DatePicker();
		startDate.setValue(LocalDate.now());
		endDate.setValue(LocalDate.now().plusDays(CalendarHelper.NUMBER_DAYS_IN_CALENDAR-1));

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
						if (item.isAfter(endDate.getValue())) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};

		datePicker.setDayCellFactory(dayCellFactory);
		datePicker.setValue(LocalDate.now());
		
		datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				handleDatePickerComboBoxChange();

			}
		});

		try {
			MovieWriter.getInstance().getMoviesFromFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		ObservableList<Movie> movies = FXCollections.observableArrayList(MovieWriter.getInstance().getMovies());
		this.moviesComboBox = new ComboBox<Movie>(movies);
		moviesComboBox.setValue(movies.get(0));
		
//		moviesComboBox.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				//TODO choose hour
//			}
//		});

		this.save = new Button("Добави");

		add(movieTheather, 0, 0);
		add(movieTheathersComboBox, 1, 0);
		add(date, 0, 1);
		add(datePicker, 1, 1);
		add(movie, 0, 2);
		add(moviesComboBox, 1, 2);
		add(save, 0, 3);
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					Cinema.getInstance().addMovieToCatalogue(choosenTheather, choosenDate, moviesComboBox.getValue());
				} catch (FileNotFoundException e) {
					return;
				}
				handleSaveButton(event);
			}
		});
	}

	protected void handleSaveButton(ActionEvent event) {
		//TODO save catalogue to file
	}

	protected void handleDatePickerComboBoxChange() {
		choosenDate = datePicker.getValue();
			try {
				Cinema.getInstance().showAllMoviesByDate(choosenDate);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			}
	}

	protected void handleMovieTheatherComboBoxChange() {
//		System.out.println(choosenTheather);
		if (movieTheathersComboBox.getValue() != null) {
			choosenTheather = movieTheathersComboBox.getValue();
		}else {
			System.out.println("Не успя да избере зала");
		}
	}
}
