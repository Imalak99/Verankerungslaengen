package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//		scene.getStylesheets().add(getClass().getResource("/gui/styles.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

}
