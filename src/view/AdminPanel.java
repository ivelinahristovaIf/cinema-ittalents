package view;

import bean.ILogger;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminPanel {

	public void start(ILogger user) {
		Stage stage = new Stage();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
				
		Tab cinemaTab = new Tab("������ ��������");
		CinemaProgramPane cinemaGrid = new CinemaProgramPane();
		cinemaTab.setContent(cinemaGrid);
		tabPane.getTabs().add(cinemaTab);
		
//		Tab setMovieProgram = new Tab("����� �������� �� ����");
//		MovieProgram programGrid = new MovieProgram();
//		setMovieProgram.setContent(programGrid);
//		tabPane.getTabs().add(setMovieProgram);
		
		Tab movieTab = new Tab("������ ����");
		MoviePanel movieGrid = new MoviePanel();
		movieTab.setContent(movieGrid);
		tabPane.getTabs().add(movieTab);
		
		Tab theatherTab = new Tab("������ ����");
		MovieTheatherPanel theatherGrid = new MovieTheatherPanel();
		theatherTab.setContent(theatherGrid);
		tabPane.getTabs().add(theatherTab);
		
		// TODO Show all cinema information and theathers - editable for admin

		
		Tab personalTab = new Tab("������");
		System.out.println("admin "+ user);
		AdminProfile adminGrid = new AdminProfile(user);
		
		// TODO Personal information edit page
		
		personalTab.setContent(adminGrid);
		tabPane.getTabs().add(personalTab);
		
		Scene scene = new Scene(tabPane, 1000, 500);
		stage.setScene(scene);
		stage.show();
		
	}
	
}
