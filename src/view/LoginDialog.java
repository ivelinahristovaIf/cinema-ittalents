package view;

import java.security.NoSuchAlgorithmException;

import bean.ILogger;
import bean.User;
import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginDialog {
	private static String[] arguments;

	private TextField emailTextField = new TextField("ivelina@abv.bg");
	private PasswordField passBox = new PasswordField();
	private static Stage stage = null;
	
	public void start(Stage s, String[] args) {
		stage = s;
		arguments = args;
		stage.setTitle("Login");
		passBox.setText("12345678");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));

		Label userName = new Label("E-mail: ");
		grid.add(userName, 0, 0);

		grid.add(emailTextField, 1, 0);

		Label pass = new Label("������: ");
		grid.add(pass, 0, 1);

		grid.add(passBox, 1, 1);

		Button sign = new Button("����");
		Button register = new Button("����������� ��");
		
		HBox hbBtn = new HBox(10);//Za raztoqnieto mejdu butonite
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(sign);
		hbBtn.getChildren().add(register);
		grid.add(hbBtn, 1, 3);
		
		sign.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					handleSignInButton(e);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleRegisterButton(event);
			}
		});

		if (args.length > 1) {
			emailTextField.setText(args[0]);
			passBox.setText(args[1]);
		}

		Scene scene = new Scene(grid, 300, 200);
		stage.setScene(scene);
		stage.show();
	}
	

	private void handleSignInButton(ActionEvent event) throws NoSuchAlgorithmException {
		try {
		  ILogger logger =new LoginController().validateLogin(emailTextField.getText(),passBox.getText());
//		  System.out.println("logger "+ logger.getType());
			if (logger != null){
				if (logger.getType() == 2){
					new AdminPanel().start(logger);
				} 
				else {
					System.out.println("User panel");
					new UserPanel().start(logger);
				}
				stage.close();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "������ ������ �����!");
				alert.showAndWait();
			}
		} catch (Exception e){
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	private void handleRegisterButton(ActionEvent event) {
		RegisterDialog dialog = new RegisterDialog();
		dialog.start(stage, arguments);
		stage.close(); //TODO Error message
	}


	public static String[] getArguments() {
		return arguments;
	}


	public static Stage getStage() {
		return stage;
	}

}
