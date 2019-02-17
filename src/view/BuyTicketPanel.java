package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;

import bean.Movie;
import bean.MovieTheatherType;
import bean.Ticket;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import writers.MovieTheaterTypeWriter;

public class BuyTicketPanel extends GridPane {
	private Label date;
	private Label movieTheather;
	private Label type;
	private Label count;
	private Label price;
	private Label seat;
	private Label movie; //FROM cinema catalogue
	private Label hour;
	
	private DatePicker datePicker;
	private ComboBox<MovieTheatherType> movieTheathersComboBox; //TODO setOnAction da zaredi filmite za tazi zala
	private ComboBox<String> typesComboBox;
	private TextField countField;
	private TextField priceField;
	private TextField seatField;
	private ComboBox<Movie> moviesComboBox;
	private ComboBox<LocalTime> projections;
	
	private Button save;
	private Button buy;

	public BuyTicketPanel() {
		super();
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.movieTheather = new Label("Изберете зала:");
		this.date = new Label("Изберете дата:");
		this.datePicker = new DatePicker();
		this.movie = new Label("Изберете филм");
		this.hour = new Label("Изберете час: ");
		
		this.type = new Label("Изберете тип на билета:");
		this.count = new Label("Колко билета искате да закупите? ");
		this.price = new Label("Единична цена: ");
		this.seat = new Label("Въведете място: ");

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
		//TODO stash them in cinema
		ObservableList<MovieTheatherType> movieTheathers = FXCollections.observableArrayList((MovieTheaterTypeWriter.getInstance().getTypes()));
		this.movieTheathersComboBox = new ComboBox<MovieTheatherType>(movieTheathers);
		//TODO make sure dates in theather are as dates here
		
		ObservableList<Movie> movies = FXCollections.observableArrayList();//TODO Cinema.getMoviesByTheatherAndDate date.setOnAction
		this.moviesComboBox = new ComboBox<Movie>(movies);
		
		ObservableList<LocalTime> times = FXCollections.observableArrayList();//TODO times by movie movie getProjections
		this.projections = new ComboBox<LocalTime>(times);
		projections.setVisibleRowCount(4);
		
		ObservableList<String> type = FXCollections.observableArrayList(Ticket.getTicketType());
		this.typesComboBox = new ComboBox<String>(type);
		typesComboBox.setVisibleRowCount(4);
		typesComboBox.setValue(Ticket.TICKET_TYPE[0]);
		
		this.seatField = new TextField("A15");
		this.countField = new TextField();
		this.priceField = new TextField();//TODO get price by type
		priceField.setDisable(true);
		
		this.save = new Button("Запази");
		this.buy = new Button("Купи");
		

		Image image = null;
		try {
			image = new Image(new FileInputStream("files\\popcorn.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(900);
		imageView.setFitWidth(400);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);

		
		add(movieTheather, 0, 0);
		add(movieTheathersComboBox, 1, 0);
		add(date, 0, 1);
		add(datePicker, 1, 1);
		add(movie, 0, 2);
		add(moviesComboBox, 1, 2);
		add(hour, 0, 3);
		add(projections, 1, 3);
		add(new Label(), 0, 4);
		add(this.type, 0, 5);
		add(typesComboBox, 1, 5);
		add(count, 0, 6);
		add(countField, 1, 6);
		add(price, 0, 7);
		add(priceField, 1, 7);
		add(seat, 0, 8);
		add(seatField, 1, 8);
		add(imageView, 5, 0,1,12);
		
		add(save, 0, 10);
		add(buy, 1, 10);

	}

}
