package view;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import bean.MovieTheatherType;
import helper.CalendarHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import writers.MovieTheaterTypeWriter;


public class BuyTicketPanel extends GridPane {
	private Label date;
	private Label movieTheather;
	
	private DatePicker datePicker;
	private ComboBox<MovieTheatherType> movieTheathersComboBox;
	private Button save;

	public BuyTicketPanel() {
		super();
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.date = new Label("Изберете дата:");
		this.movieTheather = new Label("Изберете зала:");

		this.datePicker = new DatePicker();

		DatePicker startDate = new DatePicker();
		DatePicker middleDate = new DatePicker();
		startDate.setValue(LocalDate.now());
		middleDate.setValue(LocalDate.now().plusDays(CalendarHelper.NUMBER_DAYS_IN_CALENDAR));

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
						if(item.isAfter(middleDate.getValue().plusDays(1))) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};

		datePicker.setDayCellFactory(dayCellFactory);
		datePicker.setValue(LocalDate.now());
		
		try {
			MovieTheaterTypeWriter.getInstance().getMovieTheaterTypesFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<MovieTheatherType> movies = FXCollections.observableArrayList((MovieTheaterTypeWriter.getInstance().getTypes()));
		this.movieTheathersComboBox = new ComboBox<>(movies);
		
		this.save = new Button("Запази");
		
		add(movieTheather, 0, 0);
		add(movieTheathersComboBox, 1, 0);
		add(date, 0, 1);
		add(datePicker, 1, 1);
		add(save, 0, 2);

	}

}
