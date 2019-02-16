package view;

import java.io.FileNotFoundException;

import bean.User;
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
import writers.UserWriter;

public class RegisterPanel extends GridPane {
	private Label firstname;
	private Label surname;
	private Label lastname;
	private Label email;
	private Label password;
	private Label birthDate;
	private Label city;
	private String choosenCity;

	private ComboBox<String> citiesComboBox;

	public RegisterPanel() {
		super();
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	public void init() {

		this.firstname = new Label("��� : ");
		this.surname = new Label("�������: ");
		this.lastname = new Label("������� : ");
		this.email = new Label("�-mail : ");
		this.password = new Label("������ : ");
		this.birthDate = new Label("���� �� �������: ");
		this.city = new Label("�������� ����: ");

		TextField fnameField = new TextField();
		TextField surnameField = new TextField();
		TextField lnameField = new TextField();
		TextField emailField = new TextField();
		TextField passwField = new TextField();
		DatePicker datePicker = new DatePicker();
		// LocalDate value = datePicker.getValue

		ObservableList<String> options = FXCollections.observableArrayList("�����", "�����", "������");
		citiesComboBox = new ComboBox<String>(options);
		citiesComboBox.setVisibleRowCount(5);

		Button register = new Button("����������� ��");

		// object row col
		add(firstname, 0, 0);
		add(fnameField, 1, 0);
		add(surname, 0, 1);
		add(surnameField, 1, 1);
		add(lastname, 0, 2);
		add(lnameField, 1, 2);
		add(email, 0, 3);
		add(emailField, 1, 3);
		add(password, 0, 4);
		add(passwField, 1, 4);
		add(birthDate, 0, 5);
		add(datePicker, 1, 5);
		add(city, 0, 6);
		add(citiesComboBox, 1, 6);
		add(register, 0, 7);

		citiesComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getChoosenCityComboBox(event);
			}
		});

		register.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				User consumer = new User(emailField.getText(), passwField.getText(), fnameField.getText(),
						surnameField.getText(), lnameField.getText(), datePicker.getValue(), choosenCity);
				try {
					UserWriter.getInstance().getUsersFromFile();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserWriter.getInstance().addUser(consumer);
				UserWriter.getInstance().saveUsersToFile();
				System.out.println(consumer);
				RegisterDialog.close();
				
			}
		});

	}

	protected void getChoosenCityComboBox(ActionEvent event) {
		this.choosenCity = citiesComboBox.getValue().toString();
	}

	
}
