package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Controller {

	@FXML
	private ChoiceBox<String> choiseBoxZugDruckstab;

	private String[] zugDruckstab = { "Zugstab", "Druckstab" };

}
