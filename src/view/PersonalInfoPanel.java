package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import bean.Movie;
import bean.User;
import bean.UserProfile;
import helper.InvalidPersonException;
import helper.MovieHelper;
import helper.UserHelper;
import helper.UserProfileHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import writers.MovieWriter;
import writers.UserWriter;

public class PersonalInfoPanel extends GridPane {

	private TextField fnameField;
	private TextField surnameField;
	private TextField lnameField;
	private TextField emailField;
	private PasswordField passwField;
	private DatePicker datePicker;
	private Label phone;
	private Label adress;
	private Label education;
	private TextField phoneField;
	private TextField adressField;
	private TextField educationField;
	private Label favouriteGenre;
	private Label favouriteMovie;
	private Label personalInterest;

	private ComboBox<String> citiesComboBox;
	private ComboBox<String> favouriteGenreCmb;
	private ComboBox<Movie> favouriteMovieCmb;
	private ComboBox<String> personalInterestCmb;

	private User user;

	public PersonalInfoPanel(User user) {
		this.user = user;
		this.init();
	}

	public void init() {
		if (user != null) {
			fnameField = new TextField(user.getFirstname());
			surnameField = new TextField(user.getSurname());
			lnameField = new TextField(user.getLastname());
			emailField = new TextField(user.getEmail());
			passwField = new PasswordField();
			passwField.setText(user.getPassword());
			datePicker = new DatePicker((user).getBirthDate());

			this.phone = new Label("Телефон: ");
			this.adress = new Label("Адрес:");
			this.education = new Label("Образование:");

			this.phoneField = user.getProfile() != null ? new TextField(user.getProfile().getPhoneNumber())
					: new TextField("Въведете телефон");
			this.adressField = user.getProfile() != null ? new TextField(user.getProfile().getAdress())
					: new TextField("Въведете адрес");
			this.educationField = user.getProfile() != null ? new TextField(user.getProfile().getEducation())
					: new TextField("Въведете образование");

			ObservableList<String> options = FXCollections.observableArrayList(UserHelper.CITIES);
			citiesComboBox = new ComboBox<String>(options);
			citiesComboBox.setVisibleRowCount(5);
			citiesComboBox.setValue(((User) user).getCity());

			this.favouriteGenre = new Label("Любим жанр:");
			this.favouriteMovie = new Label("Любим филм:");
			this.personalInterest = new Label("Интереси:");

			ObservableList<String> genres = FXCollections.observableArrayList(MovieHelper.MOVIE_GENRE);
			favouriteGenreCmb = new ComboBox<String>(genres);
			favouriteGenreCmb.setValue(genres.get(0));
			try {
				MovieWriter.getInstance().getMoviesFromFile();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObservableList<Movie> movies = FXCollections.observableArrayList(MovieWriter.getInstance().getMovies());
			favouriteMovieCmb = new ComboBox<Movie>(movies);
			favouriteMovieCmb.setValue(movies.get(0));
			
			ObservableList<String> interests = FXCollections.observableArrayList(UserProfileHelper.INTEREST);
			personalInterestCmb = new ComboBox<String>(interests);
			personalInterestCmb.setValue(interests.get(0));
			

			Image image = null;
			try {
				image = new Image(new FileInputStream("files\\profile_image.jpeg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(700);
			imageView.setFitWidth(400);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);

			Button save = new Button("Запиши");

			add(fnameField, 0, 0);
			add(surnameField, 1, 0);
			add(lnameField, 0, 1);
			add(emailField, 0, 2);
			add(passwField, 1, 2);
			add(datePicker, 0, 3);
			add(citiesComboBox, 1, 3);
			add(new Label("Лични данни"), 0, 4);
			add(phone, 0, 5);
			add(phoneField, 1, 5);
			add(adress, 0, 6);
			add(adressField, 1, 6);
			add(education, 0, 7);
			add(educationField, 1, 7);
			add(favouriteGenre, 0, 8);
			add(favouriteGenreCmb, 1, 8);
			add(favouriteMovie, 0, 9);
			add(favouriteMovieCmb, 1, 9);
			add(personalInterest, 0, 10);
			add(personalInterestCmb, 1, 10);
			add(imageView, 5, 0, 3, 14);
			add(save, 0, 13);
			save.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					handleSaveButton(event);
				}
			});
		} else {
			System.out.println("Null user");
		}
	}

	protected void handleSaveButton(ActionEvent event) {
		if (user == null) {
			System.out.println("new user");
			user = new User(false);
		}
		user.setFirstname(fnameField.getText());
		user.setSurname(surnameField.getText());
		user.setLastname(lnameField.getText());
		user.setEmail(emailField.getText());
		user.setBirthDate(datePicker.getValue());
		try {
			user.setPassword(passwField.getText());
		} catch (InvalidPersonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		user.setCity(citiesComboBox.getValue());
		if(user.getProfile()==null) {
			user.setProfile(new UserProfile());
		}
		user.getProfile().setPhoneNumber(phoneField.getText());
		user.getProfile().setAdress(adressField.getText());
		user.getProfile().setEducation(educationField.getText());
		user.getProfile().addFavouriteGenre(favouriteGenreCmb.getValue().toString());
		user.getProfile().addPersonalInterest(personalInterestCmb.getValue().toString());
		user.getProfile().addFavouriteMovie(favouriteMovieCmb.getValue());

		try {
			UserWriter writer = new UserWriter();
			writer.getUsersFromFile();
			writer.addUser(user);
			writer.saveUsersToFile();
			citiesComboBox.setValue(user.getCity());
			Alert alert = new Alert(AlertType.INFORMATION, "Променихте профила си успешно!");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}

}
