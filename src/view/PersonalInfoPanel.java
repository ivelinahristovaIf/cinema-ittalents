package view;

import bean.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import writers.UserWriter;

public class PersonalInfoPanel extends GridPane {

	TextField fnameField;
	TextField surnameField;
	TextField lnameField;
	TextField emailField;
	TextField passwField;
	DatePicker datePicker;

	private ComboBox<String> citiesComboBox;

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
			 passwField = new TextField(user.getPassword());
			datePicker = new DatePicker(user.getBirthDate());

			add(fnameField, 0, 0);
			add(surnameField, 1, 0);
			add(lnameField, 0, 1);
			add(emailField, 0, 2);
			add(passwField, 1, 2);
			add(datePicker, 0, 3);

			ObservableList<String> options = FXCollections.observableArrayList("София", "Варна", "Бургас");
			citiesComboBox = new ComboBox<String>(options);
			citiesComboBox.setVisibleRowCount(5);
			citiesComboBox.setValue(user.getCity());
			add(citiesComboBox, 1, 3);
		}

		Button save = new Button("Запиши");

		add(save, 0, 4);
		save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handleSaveButton(event);
			}
		});
	}

	protected void handleSaveButton(ActionEvent event) {
		if (user == null) {
			user = new User();
		}
		System.out.println(fnameField.getText());
		user.setFirstname(fnameField.getText());
		user.setSurname(surnameField.getText());
		user.setLastname(lnameField.getText());
		user.setEmail(emailField.getText());
		user.setBirthDate(datePicker.getValue());
		user.setPassword(passwField.getText());
		user.setCity(citiesComboBox.getValue());

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
