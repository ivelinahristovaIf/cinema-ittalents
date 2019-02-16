package view;


import bean.ILogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AdminProfile extends GridPane{
	private Label username;
	private Label password;
	
	private TextField usernameField;
	private PasswordField passwordField;
	
	private Button save;
	private ILogger logger;
	
	public AdminProfile(ILogger logger) {
		super();
		this.logger = logger;
		setAlignment(Pos.TOP_LEFT);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		this.username = new Label("Потребителско име:");
		this.password = new Label("Парола:");
		if(logger!=null) {
		this.usernameField = new TextField(this.logger.getEmail());
		String pass = this.logger.getPassword();
		this.passwordField = new PasswordField();
		this.passwordField.setText(pass);
		
		this.save = new Button("Запиши");
		
		this.save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO save into json
			}
		});;
		
		add(this.username, 0, 0);
		add(this.usernameField, 1, 0);
		add(this.password, 0, 1);
		add(this.passwordField, 1, 1);
		add(this.save, 0, 2);
		}
		
	}
	
}
