package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import bean.Cinema;
import bean.Movie;
import bean.MovieTheather;
import bean.MovieTheatherType;
import bean.Ticket;
import bean.User;
import helper.CalendarHelper;
import helper.NotValidTicketTypeException;
import helper.UserHelper;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import writers.MovieTheaterTypeWriter;

public class BuyTicketPanel extends GridPane {
	private Label date;
	private Label movieTheather;
	private Label type;
	private Label count;
	private Label price;
	private Label seat;
	private Label movie; // FROM cinema catalogue
	private Label hour;

	private DatePicker datePicker;
	private ComboBox<MovieTheatherType> movieTheathersComboBox; // TODO setOnAction da zaredi filmite za tazi zala
	private ComboBox<String> typesComboBox;
	private TextField countField;
	private TextField priceField;
	private TextField seatField;
	private ComboBox<Movie> moviesComboBox;
	private ComboBox<LocalTime> projections;

	private Button save;
	private Button buy;

	private MovieTheatherType movieTheatherByType;
	private LocalDate choosenDate;
	private Set<Movie> moviePicker = new TreeSet<>();
	private String choosenTicketType;

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
		middleDate.setValue(LocalDate.now().plusDays(CalendarHelper.NUMBER_DAYS_IN_CALENDAR-1));

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
						if (item.isAfter(middleDate.getValue())) {
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
			MovieTheaterTypeWriter.getInstance().getMovieTheaterTypesFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		HashSet<MovieTheatherType> types = new HashSet<>();
//		types.addAll(Cinema.getInstance().getAllMovieTheathers()); //TODO get all type by cinema
		// TODO stash them in cinema
		ObservableList<MovieTheatherType> movieTheathers = FXCollections
				.observableArrayList(MovieTheaterTypeWriter.getInstance().getTypes());
		this.movieTheathersComboBox = new ComboBox<MovieTheatherType>(movieTheathers);
		// TODO make sure dates in theather are as dates here

		movieTheathersComboBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<MovieTheatherType>() {

					@Override
					public void changed(ObservableValue<? extends MovieTheatherType> observable,
							MovieTheatherType oldValue, MovieTheatherType newValue) {
						handleMovieTheatherComboBoxChange();
					}
				});

		
		ObservableList<Movie> movies = FXCollections.observableArrayList();// TODO Cinema.getMoviesByTheatherAndDate
		// date.setOnAction
		this.moviesComboBox = new ComboBox<Movie>();
		ObservableList<LocalTime> times = FXCollections.observableArrayList();// TODO times by movie movie
		// getProjections
		this.projections = new ComboBox<LocalTime>(times);
		projections.setVisibleRowCount(4);

		ObservableList<String> type = FXCollections.observableArrayList(Ticket.getTicketType());
		this.typesComboBox = new ComboBox<String>(type);
		typesComboBox.setVisibleRowCount(4);
		typesComboBox.setValue(Ticket.TICKET_TYPE[0]);
		
		typesComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				handleTicketTypesComboBoxChange();
			}
		});

		
		
		this.seatField = new TextField("A15");
		this.countField = new TextField();
	
		
		this.priceField = new TextField();// TODO get price by type
		priceField.setDisable(true);

		this.save = new Button("Запази");
		this.buy = new Button("Купи");
		
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO message window
//				handleButton(event);
				try {
					Ticket ticket = Ticket.getInstance(choosenTicketType, seatField.getText(), movieTheatherByType, null);
					System.out.println(ticket);
					UserHelper.getInstance().buyTicket(movieTheatherByType, choosenTicketType, Integer.parseInt(countField.getText()), seatField.getText());
				} catch (NotValidTicketTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO message window
				
			}
		});

		Image image = null;
		try {
			image = new Image(new FileInputStream("files\\popcorn.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(900);
		imageView.setFitWidth(500);
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
		add(imageView, 5, 0, 2, 12);

		add(save, 0, 10);
		add(buy, 1, 10);

	}

	protected void handleTicketTypesComboBoxChange() {
		String ticketType = typesComboBox.getValue().toString();
		choosenTicketType = ticketType;
		priceField.setText(String.valueOf(Ticket.getPriceByType(choosenTicketType)+"лв."));
	}

	protected void handleDatePickerComboBoxChange() {
		LocalDate date = datePicker.getValue();
		System.out.println(date);
		try {
			Cinema.getInstance().showAllMoviesByDate(date);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void handleMovieTheatherComboBoxChange() {
		MovieTheatherType type = movieTheathersComboBox.getValue();
		System.out.println(type);
		MovieTheatherType mt = null;
		if (type != null) {
			try {
//				movieTheatherByType 
				mt = Cinema.getInstance().getMovieTheatherByType(type);
				System.out.println("movie theather " + mt);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			movieTheatherByType = mt;
		}
	}

	protected void handleDatePicker(ActionEvent event) {
		choosenDate = datePicker.getValue();
		System.out.println(choosenDate);
		try {
			Cinema.getInstance().showAllMoviesByDate(choosenDate);
			moviePicker = Cinema.getInstance().getAllMoviesByTheatherAndDate(movieTheatherByType, choosenDate);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Movie> movies = FXCollections.observableArrayList(moviePicker);
//		moviesComboBox.getItems().addAll(movies);
	}

}
