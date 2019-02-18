package view;


import bean.Admin;
import bean.ILogger;
import bean.User;
import bean.UserProfile;
import helper.InvalidPersonException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import writers.UserWriter;

public class AdminProfile extends GridPane{
	private Label username;
	private Label password;
	
	private TextField usernameField;
	private PasswordField passwordField;
	
	private Button save;
	private ILogger logger;
	
	public AdminProfile(ILogger logger) {
		super();
		this.logger =logger;
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.username = new Label("������������� ���:");
		this.password = new Label("������:");
		if(logger!=null) {
		this.usernameField = new TextField(this.logger.getEmail());
		String pass = this.logger.getPassword();
		this.passwordField = new PasswordField();
		this.passwordField.setText(pass);
		
		this.save = new Button("������");
		
		this.save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handleSaveButton(event);
			}
		});;
		
		add(this.username, 0, 0);
		add(this.usernameField, 1, 0);
		add(this.password, 0, 1);
		add(this.passwordField, 1, 1);
		add(this.save, 0, 2);
		}
		
	}

	protected void handleSaveButton(ActionEvent event) {
		if (logger == null) {
			System.out.println("new user");
			try {
				logger = Admin.getInstance();
			} catch (InvalidPersonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		((Admin) logger).setEmail(usernameField.getText());
		try {
			((Admin) logger).setPassword(passwordField.getText());
		} catch (InvalidPersonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			UserWriter writer = new UserWriter();
			writer.getUsersFromFile();
			writer.addUser(logger);
			writer.saveUsersToFile();
			Alert alert = new Alert(AlertType.INFORMATION, "���������� ������� �� �������!");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
}
