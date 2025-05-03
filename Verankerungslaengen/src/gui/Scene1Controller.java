package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Scene1Controller {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToScene2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Scene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);

		// Fenster in der Mitte des Bildschirms platzieren
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		double x = (screenBounds.getWidth() - stage.getWidth()) / 2;
		double y = (screenBounds.getHeight() - stage.getHeight()) / 2;

		stage.setX(x);
		stage.setY(y);
		stage.show();
	}

}
