package view;

import bean.ILogger;
import bean.User;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserPanel {
	
	public void start(ILogger user) {
		Stage stage = new Stage();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
				
		Tab cinemaTab = new Tab(" ËÌÓ");
		GridPane cinemaGrid = new GridPane();
		
		// TODO Show all cinema information and theathers
		
		cinemaTab.setContent(cinemaGrid);
		tabPane.getTabs().add(cinemaTab);

		
		Tab userManagementTab = new Tab("œ–Œ‘»À");//change username password
		PersonalInfoPanel userManagementGrid = new PersonalInfoPanel((User) user);
		
		// TODO Page for managing users
		
		userManagementTab.setContent(userManagementGrid);
		tabPane.getTabs().add(userManagementTab);
		

		
//		Tab personalTab = new Tab("Personal information");
//		PersonalInfoPane personalGrid = new PersonalInfoPane(user);
		
		// TODO Personal information edit page
		
//		personalTab.setContent(personalGrid);
//		tabPane.getTabs().add(personalTab);
		
		Scene scene = new Scene(tabPane, 1000, 500);
		stage.setScene(scene);
		stage.show();
		
	}
}
