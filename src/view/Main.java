package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static String[] arguments;

	private LoginDialog loginDialog = new LoginDialog();

	public static void main(String[] args) {
		arguments = args;
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loginDialog.start(primaryStage, arguments);
	}

}
