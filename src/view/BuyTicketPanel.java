package view;

import java.time.LocalDate;

import bean.Movie;
import bean.MovieTheather;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import writers.MovieWriter;

public class BuyTicketPanel extends GridPane{
	private Label date;
	private Label movie;
	
	private DatePicker datePicker;
	private ComboBox<Movie> moviesComboBox;
	
	public BuyTicketPanel(){
		super();
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.date = new Label("Изберете дата:");
		this.movie = new Label("Изберете филм:");
		
		this.datePicker = new DatePicker(LocalDate.now());
		ObservableList<Movie> movies = FXCollections.observableSet(MovieWriter.getInstance().getMoviesFromFile());
		
		
	}


}
