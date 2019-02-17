package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import bean.Cinema;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CinemaPanel extends GridPane {
	private Label name;
	private Label adress;
	private Label phoneNumber;

	private TextField nameField;
	private TextField adressField;
	private TextField phoneField;

	public CinemaPanel() {
		super();
		setAlignment(Pos.TOP_CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		this.init();
	}

	private void init() {
		// TODO cinema getInctance
		Cinema cinema = null;
		try {
			cinema = Cinema.getInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.name = new Label("Име: ");
		nameField = new TextField(cinema.getName());
		nameField.setDisable(true);
		;
		this.adress = new Label("Адрес: ");
		adressField = new TextField(cinema.getAddress());
		adressField.setDisable(true);
		this.phoneNumber = new Label("Телефон: ");
		phoneField = new TextField(cinema.getPhoneNumber());
		phoneField.setDisable(true);

		Image image = null;
		try {
			image = new Image(new FileInputStream("files\\Kino_arena_logo_BG.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView arenaView = new ImageView(image);

		// setting the fit height and width of the image view
		arenaView.setFitHeight(100);
		arenaView.setFitWidth(200);
		// Setting the preserve ratio of the image view
		arenaView.setPreserveRatio(true);

		Image imax = null;
		try {
			imax = new Image(new FileInputStream("files\\imax.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView imaxView = new ImageView(imax);

		// setting the fit height and width of the image view
//		imaxView.setFitHeight(50);
//		arenaView.setFitWidth(150);
		// Setting the preserve ratio of the image view
		arenaView.setPreserveRatio(true);
		
		Image premium = null;
		try {
			premium = new Image(new FileInputStream("files\\premium.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView premiumView = new ImageView(premium);
		premiumView.setPreserveRatio(true);
		
		Image luxe = null;
		try {
			luxe = new Image(new FileInputStream("files\\luxe.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView luxeView = new ImageView(luxe);
		luxeView.setPreserveRatio(true);
		
		Image vip = null;
		try {
			vip = new Image(new FileInputStream("files\\vip.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView vipView = new ImageView(vip);
		vipView.setPreserveRatio(true);

		Image image2 = null;
		try {
			image2 = new Image(new FileInputStream("files\\image.jpeg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitHeight(600);
		imageView2.setFitWidth(950);
		imageView2.setPreserveRatio(true);
		imageView2.setSmooth(true);

		add(imaxView, 0, 0, 1, 1);
		add(premiumView, 1, 0,1,1);
		add(arenaView, 2, 0, 2, 1);
		add(luxeView, 4, 0,1,1);
		add(vipView, 5, 0,1,1);
		add(name, 0, 1, 2, 1);
		add(nameField, 0, 2, 2, 1);
		add(adress, 4, 1, 3, 1);
		add(adressField, 4, 2, 3, 1);
		add(phoneNumber, 2, 1, 2, 1);
		add(phoneField, 2, 2, 2, 1);
		add(imageView2, 0, 3, 6, 1);

	}
}
