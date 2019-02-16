package view;

import java.io.FileNotFoundException;

import bean.Movie;
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
import writers.MovieWriter;

public class MoviePanel extends GridPane {
	private Label name;
	private Label length;
	private Label premiere;
	private Label genre;
	private Label category;
	private Label chooseFromHours;

	private String choosenGenre;
	private String choosenCategory;
	private int choosedNumberProjection;

	private ComboBox<String> genresComboBox;
	private ComboBox<String> categoriesComboBox;
	private ComboBox<Integer> numberSelect;

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
		this.chooseFromHours = new Label("Áğîé ïğîæåêöèè íà äåí: ");

		TextField nameField = new TextField();
		TextField lengthField = new TextField();

		DatePicker datePicker = new DatePicker();
		ObservableList<String> options = FXCollections.observableArrayList("ÄĞÀÌÀ", "ÓÆÀÑÈ", "ÊÎÌÅÄÈß", "ÀÍÈÌÀÖÈß",
				"ÅÊØÚÍ", "ÔÀÍÒÀÑÒÈÊÀ", "ÁÈÎÃĞÀÔÈ×ÅÍ", "ÏĞÈÊËŞ×ÅÍÑÊÈ", "ĞÎÌÀÍÒÈ×ÅÍ", "ÊĞÈÌÈÍÀËÅÍ", "ÂÎÅÍÅÍ",
				"ÍÀÓ×ÍÎ ÏÎÏÓËßĞÅÍ", "ÌŞÇÈÊÚË");
		genresComboBox = new ComboBox<String>(options);
		genresComboBox.setVisibleRowCount(7);

		ObservableList<String> categories = FXCollections.observableArrayList("A", "B", "C", "D");
		categoriesComboBox = new ComboBox<String>(categories);
		categoriesComboBox.setVisibleRowCount(4);

		genresComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getChoosenGenre(event);
			}
		});
		categoriesComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getChoosenCategory(event);
			}
		});
		 numberSelect = new ComboBox<Integer>();

		ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5);

		numberSelect.setItems(list);
		numberSelect.autosize();
		numberSelect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(numberSelect.getValue().intValue());
				handleNumberSelection(event);
			}

		});

		Button save = new Button("Çàïàçè");

		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				short l = Short.parseShort(lengthField.getText());
				Movie movie = null;
				movie = new Movie(choosenGenre, nameField.getText(), l, datePicker.getValue(), choosenCategory);
				movie.setNumberOfProjectionsForDay(choosedNumberProjection);
				try {
					MovieWriter.getInstance().getMoviesFromFile();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MovieWriter.getInstance().addMovie(movie);
				MovieWriter.getInstance().saveMoviesToFile();
				System.out.println(movie);
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
		add(categoriesComboBox, 1, 4);
		add(chooseFromHours, 0, 5);
		add(numberSelect, 1, 5);
		add(save, 0, 6);

	}

	protected void handleNumberSelection(ActionEvent event) {
		this.choosedNumberProjection = numberSelect.getValue().intValue();
	}

	protected void getChoosenCategory(ActionEvent event) {
		this.choosenCategory = categoriesComboBox.getValue().toString();
	}

	protected void getChoosenGenre(ActionEvent event) {
		this.choosenGenre = genresComboBox.getValue().toString();
	}

	public String getChoosenGenre() {
		return choosenGenre;
	}
}
