package view;

import bean.Movie;
import bean.MovieTheather;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BuyTicketPanel extends GridPane{
	private Label theather;
	private Label date;
	private Label movie;
	
	private ComboBox<MovieTheather> theathersComboBox;
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
		// TODO Auto-generated method stub
		
	}


}
