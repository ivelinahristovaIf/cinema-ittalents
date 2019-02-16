package view;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

public class RegisterDialog {
	private static Stage stage;
	
	public void start(Stage s, String[] args) {
		stage = new Stage();
		stage.setTitle("User");
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
				
		Tab registerTab = new Tab("Register");
		RegisterPanel registerPanel = new RegisterPanel();
		
		registerTab.setContent(registerPanel);
		tabPane.getTabs().add(registerTab);
		
		Scene scene = new Scene(tabPane, 1000, 500);
		stage.setScene(scene);
		stage.show();
	}
	public static void close() {
		stage.close();
		LoginDialog dialog = new LoginDialog();
		dialog.start(LoginDialog.getStage(), LoginDialog.getArguments());
	}
	public static Stage getStage() {
		return stage;
	}
}
