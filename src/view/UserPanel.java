package view;

import bean.User;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

public class UserPanel {
	
	public void start(User user) {
		Stage stage = new Stage();
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
				
		Tab cinemaTab = new Tab("����");
		CinemaPanel cinemaGrid = new CinemaPanel();
		
		// TODO Show all cinema information and theathers
		
		cinemaTab.setContent(cinemaGrid);
		tabPane.getTabs().add(cinemaTab);
		
		
		Tab buyTicketTab = new Tab("���� �����");
		BuyTicketPanel buyTicket = new BuyTicketPanel();
		buyTicketTab.setContent(buyTicket);
		tabPane.getTabs().add(buyTicketTab);

		
		Tab userManagementTab = new Tab("������");//change username password
		PersonalInfoPanel userManagementGrid = new PersonalInfoPanel(user);
		
		// TODO Page for managing users
		
		userManagementTab.setContent(userManagementGrid);
		tabPane.getTabs().add(userManagementTab);
		
		
		Scene scene = new Scene(tabPane, 1400, 700);
		stage.setScene(scene);
		stage.show();
		
	}
}
