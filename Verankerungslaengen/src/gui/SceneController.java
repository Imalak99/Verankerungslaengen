package gui;

import java.io.IOException;

import backend.ClipboardUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SceneController {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextArea ergebnisFeld;
	private String ergebnisStringsInTextFeld;

	@FXML
	private ComboBox<String> choiceBoxZugDruckstab;

	private String[] zugDruckstab = { "Zugstab", "Druckstab" };

	public void switchToScene1(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/gui/Scene1.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToScene2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Scene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void initialize() {
		if (choiceBoxZugDruckstab != null) {
			choiceBoxZugDruckstab.getItems().addAll(zugDruckstab);
		}
	}

	public void berechneErgebnis(ActionEvent event) throws IOException {
		String ergebnisString = "Hier werden die Ergebnisse angezeigt :)";
		ergebnisFeld.setText(ergebnisString);
		this.ergebnisStringsInTextFeld = ergebnisString;
		System.out.println("Berechnet ergebis");
	}

	public void ergebnisseInZwischenablageKopieren(ActionEvent event) {
		ClipboardUtil.inZwischenablageKopieren(this.ergebnisStringsInTextFeld);
		System.out.println("Ergebnisse wurden in zwischenablage Kopiert");
	}
}
