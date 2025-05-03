package programm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GuiMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/Scene1.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			primaryStage.setResizable(false);

			// Berechne und setze die Position erst nach dem Zeigen der Stage
			primaryStage.setOnShown(event -> {
				// Fenster in der Mitte des Bildschirms platzieren
				Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
				double x = (screenBounds.getWidth() - primaryStage.getWidth()) / 2;
				double y = (screenBounds.getHeight() - primaryStage.getHeight()) / 2;

				primaryStage.setX(x);
				primaryStage.setY(y);
			});

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
