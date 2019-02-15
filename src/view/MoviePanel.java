package view;

import bean.Movie;
import helper.NotValidMovieGenreException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MoviePanel extends GridPane{
	private Label name;
	private Label length;
	private Label premiere;
	private Label genre;
	private Label category;
	private String choosenGenre;
	
	ComboBox<String> genresComboBox;
	
	public MoviePanel() {
		super();
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.name = new Label("Èìå:");
		this.length = new Label("Âğåìåòğàåíå:");
		this.premiere = new Label("Ïğåìèåğíà äàòà");
		this.genre = new Label("Æàíğ:");
		this.category = new Label("Êàòåãîğèÿ:");
		
		TextField nameField = new TextField();
		TextField lengthField = new TextField();
//		lengthField.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				  if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
//	                    lengthField.setText(oldValue);
//	                }
//			}
//        });
		
		DatePicker datePicker = new DatePicker();
		ObservableList<String> options = FXCollections.observableArrayList("ÄĞÀÌÀ", "ÓÆÀÑÈ", "ÊÎÌÅÄÈß","ÀÍÈÌÀÖÈß","ÅÊØÚÍ","ÔÀÍÒÀÑÒÈÊÀ",
				"ÁÈÎÃĞÀÔÈ×ÅÍ", "ÏĞÈÊËŞ×ÅÍÑÊÈ", "ĞÎÌÀÍÒÈ×ÅÍ", "ÊĞÈÌÈÍÀËÅÍ",
				"ÂÎÅÍÅÍ", "ÍÀÓ×ÍÎ ÏÎÏÓËßĞÅÍ", "ÌŞÇÈÊÚË");
		 genresComboBox = new ComboBox<String>(options);
		genresComboBox.setVisibleRowCount(7);
		
		Button save = new Button("Çàïàçè");
		
		genresComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getChoosenGenre(event);
			}
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				short l = Short.parseShort(lengthField.getText());
				Movie movie = null;
				try {
					movie = Movie.getInstance(/*choosenGenre, nameField.getText(), l, datePicker.getValue(), movieCategories.C*/);
				} catch (NotValidMovieGenreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(movie);
			}
		});
			
	
		add(name, 0, 0);
		add(nameField, 1, 0);
		add(length, 0, 1);
		add(lengthField, 1, 1);
		add(premiere, 0, 2);
		add(datePicker, 1, 2);
		add(genre, 0, 3);
		add(genresComboBox, 1, 3);
		add(category, 0, 4);
		add(save, 0, 5);
		
	}

//	protected void handleSaveButton(ActionEvent event) {
//		// TODO Auto-generated method stub
//		
//	}

	protected void getChoosenGenre(ActionEvent event) {
		this.choosenGenre = genresComboBox.getValue().toString();	}

	public String getChoosenGenre() {
		return choosenGenre;
	}
}
