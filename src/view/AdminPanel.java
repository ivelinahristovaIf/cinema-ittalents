package view;

import bean.User;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminPanel {

	public void start(User user) {
		Stage stage = new Stage();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
				
		Tab cinemaTab = new Tab("Прожекции");
		GridPane cinemaGrid = new GridPane();
		cinemaTab.setContent(cinemaGrid);
		tabPane.getTabs().add(cinemaTab);
		
		Tab movieTab = new Tab("Добави филм");
		MoviePanel movieGrid = new MoviePanel();
		movieTab.setContent(movieGrid);
		tabPane.getTabs().add(movieTab);
		
		// TODO Show all cinema information and theathers - editable for admin
		
		
		Tab adminManagementTab = new Tab("Admin management");//change username password
		GridPane adminManagementGrid = new GridPane();
		
		// TODO Page for managing administrators 
		
		adminManagementTab.setContent(adminManagementGrid);
		tabPane.getTabs().add(adminManagementTab);
		

		
		Tab personalTab = new Tab("Personal information");
		GridPane personalGrid = new GridPane();
		
		// TODO Personal information edit page
		
		personalTab.setContent(personalGrid);
		tabPane.getTabs().add(personalTab);
		
		Scene scene = new Scene(tabPane, 1000, 500);
		stage.setScene(scene);
		stage.show();
		
	}
	
}
