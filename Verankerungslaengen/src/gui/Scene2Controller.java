package gui;

import java.io.IOException;

import backend.Calculation;
import backend.ClipboardUtil;
import backend.GuiEingabeParameter;
import backend.Kenngrößen;
import backend.Strings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene2Controller {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextArea ergebnisFeld;
	@FXML
	private TextField textFieldErfBew;
	@FXML
	private TextField textFieldVorBew;
	private String ergebnisStringsInTextFeld;

	private String[] zugDruckstab = { "Zugstab", "Druckstab" };
	private String[] verankerungsart = { "Gerades Stabende", "Haken", "Winkelhaken", "Schlaufe" };
	private String[] betonklasse = { "C12/15", "C16/20", "C20/25", "C25/30", "C30/37", "C35/45", "C40/50", "C45/55",
			"C50/60" };
	private String[] verbundbedingung = { "guter Verbund", "mäßiger Verbund" };
	private String[] stabdurchmesser = { "6 mm", "8 mm", "10 mm", "12 mm", "14 mm", "16 mm", "20 mm", "25 mm",
			"28 mm" };
	private String[] angeschweißteQuerstaebe = { "vorhanden", "nicht vorhanden" };
	private String[] lagerung = { "vorhanden", "nicht vorhanden" };

	private String zugDruckstabAuswahl = zugDruckstab[0];
	private String verankerungsartAuswahl = verankerungsart[0];
	private String betonklasseAuswahl = betonklasse[0];
	private String verbundbedingungAuswahl = verbundbedingung[0];
	private String stabdurchmesserAuswahl = stabdurchmesser[0];
	private String angeschweißteQuerstaebeAuswahl = angeschweißteQuerstaebe[0];
	private String lagerungAuswahl = lagerung[0];
	private double vorhandeneBew = 1.;
	private double erforderlicheBew = 1.;
	private double fyd = 435;

	@FXML
	private ComboBox<String> choiceBoxZugDruckstab;
	@FXML
	private ComboBox<String> choiceBoxVerankerungsart;
	@FXML
	private ComboBox<String> choiceBoxBetonklasse;
	@FXML
	private ComboBox<String> choiceBoxVerbundbedingung;
	@FXML
	private ComboBox<String> choiceBoxStabdurchmesser;
	@FXML
	private ComboBox<String> choiceBoxAngeschweißteQuerstäbe;

	@FXML
	private ComboBox<String> choiceBoxLagerung;

	public void switchToScene1(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/gui/Scene1.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void initialize() {
		System.out.println("initialize aufgerufen");
		choiceBoxZugDruckstab.getItems().addAll(zugDruckstab);
		choiceBoxZugDruckstab.setValue(zugDruckstab[0]);

		choiceBoxVerankerungsart.getItems().addAll(verankerungsart);
		choiceBoxVerankerungsart.setValue(verankerungsart[0]);

		choiceBoxBetonklasse.getItems().addAll(betonklasse);
		choiceBoxBetonklasse.setValue(betonklasse[0]);

		choiceBoxVerbundbedingung.getItems().addAll(verbundbedingung);
		choiceBoxVerbundbedingung.setValue(verbundbedingung[0]);

		choiceBoxStabdurchmesser.getItems().addAll(stabdurchmesser);
		choiceBoxStabdurchmesser.setValue(stabdurchmesser[0]);

		choiceBoxAngeschweißteQuerstäbe.getItems().addAll(angeschweißteQuerstaebe);
		choiceBoxAngeschweißteQuerstäbe.setValue(angeschweißteQuerstaebe[0]);

		choiceBoxLagerung.getItems().addAll(lagerung);
		choiceBoxLagerung.setValue(lagerung[0]);

		// Event-Handler für Auswahl
		choiceBoxZugDruckstab.setOnAction(e -> {
			this.zugDruckstabAuswahl = choiceBoxZugDruckstab.getValue();
		});
		choiceBoxVerankerungsart.setOnAction(e -> {
			this.verankerungsartAuswahl = choiceBoxVerankerungsart.getValue();
		});
		choiceBoxBetonklasse.setOnAction(e -> {
			this.betonklasseAuswahl = choiceBoxBetonklasse.getValue();
		});
		choiceBoxVerbundbedingung.setOnAction(e -> {
			this.verbundbedingungAuswahl = choiceBoxVerbundbedingung.getValue();
		});
		choiceBoxStabdurchmesser.setOnAction(e -> {
			this.stabdurchmesserAuswahl = choiceBoxStabdurchmesser.getValue();
		});
		choiceBoxAngeschweißteQuerstäbe.setOnAction(e -> {
			this.angeschweißteQuerstaebeAuswahl = choiceBoxAngeschweißteQuerstäbe.getValue();
		});
		choiceBoxLagerung.setOnAction(e -> {
			this.lagerungAuswahl = choiceBoxLagerung.getValue();
		});

	}

//	DIESE ZWEI METHODEN DIEN ALS ALTERNATIVE ZU DER INIZIALIZE METHODE ALLEINE

//	private void initChoiceBox(ComboBox<String> comboBox, String[] values, Consumer<String> onChange) {
//	    comboBox.getItems().addAll(values);
//	    comboBox.setValue(values[0]);
//	    comboBox.setOnAction(e -> {
//	        String value = comboBox.getValue();
//	        onChange.accept(value);
//	    });
//	}
//	@FXML
//	public void initialize() {
//	    System.out.println("initialize aufgerufen");
//
//	    initChoiceBox(choiceBoxZugDruckstab, zugDruckstab, value -> this.zugDruckstabAuswahl = value);
//	    initChoiceBox(choiceBoxVerankerungsart, verankerungsart, value -> this.verankerungsartAuswahl = value);
//	    initChoiceBox(choiceBoxBetonklasse, betonklasse, value -> this.betonklasseAuswahl = value);
//	    initChoiceBox(choiceBoxVerbundbedingung, verbundbedingung, value -> this.verbundbedingungAuswahl = value);
//	    initChoiceBox(choiceBoxStabdurchmesser, stabdurchmesser, value -> this.stabdurchmesserAuswahl = value);
//	    initChoiceBox(choiceBoxAngeschweißteQuerstäbe, angeschweißteQuerstäbe, value -> this.angeschweißteQuerstäbeAuswahl = value);
//	    initChoiceBox(choiceBoxLagerung, lagerung, value -> this.lagerungAuswahl = value);
//	}

	public void berechneErgebnis(ActionEvent event) throws IOException {
		submitErfBew(event);
		submitVorBew(event);

		int betonwahl = GuiEingabeParameter.betonwahlStringToInt(this.betonklasseAuswahl);
		boolean guterVerbund = GuiEingabeParameter.guterVerbundStringToBoolean(this.verbundbedingungAuswahl);
		double fbd = Kenngrößen.fbdWert(betonwahl, guterVerbund);
		System.out.println(fbd);

		boolean zugstab = GuiEingabeParameter.zugstabStringToBoolean(this.zugDruckstabAuswahl);
		boolean geraderStab = GuiEingabeParameter.geraderStabStringToBoolean(this.verankerungsartAuswahl);
		int verankerungsart = GuiEingabeParameter.verankerungsartStringToInt(verankerungsartAuswahl);
		boolean engeVerbuegelung = true; // noch in der GUI ergänzen!!!
		double a = 150.; // Horizontaler Abstand der Längsbewehrungseisen in mm, noch vergänzen in GUI
		double c1 = 35; // Betondeckung zur Seite in mm ----"-----
		double c = 35; // Betondeckung nach unten in mm ----"-----
		double cd = GuiEingabeParameter.berechneCd(verankerungsartAuswahl, a, c1, c);
		double dSchlaufe = 100; // Durchmesser der Schlaufe in mm ----"-----

		String stabdurchmesserOnlyNumber = this.stabdurchmesserAuswahl.replaceAll("[^\\d.]", "");
		double stabdurchmesser = Double.parseDouble(stabdurchmesserOnlyNumber);
		double a1 = Calculation.a1(zugstab, verankerungsart, geraderStab, engeVerbuegelung, cd, stabdurchmesser,
				dSchlaufe);
		double a2 = Calculation.a2(zugstab, geraderStab, cd, stabdurchmesser);
		double a3 = Calculation.a3();
		boolean angeschweißteQuerstaebe = GuiEingabeParameter
				.angeschweißteQuerstaebeStringToBoolean(this.angeschweißteQuerstaebeAuswahl); // in GUI ergänzen
		double a4 = Calculation.a4(angeschweißteQuerstaebe);
		double a5 = 1.;
		double ausnutzung = this.erforderlicheBew / this.vorhandeneBew;

		double lbd = Calculation.berechneVerankerungslaenge(stabdurchmesser, this.fyd, fbd, a1, a2, a3, a4, a5,
				ausnutzung);
		boolean querdruck = GuiEingabeParameter.querdruckStringToBoolean(this.lagerungAuswahl);
		double lbrqdy = Calculation.berechneGrundmaß(stabdurchmesser, fyd, fbd);
		double lbmin = Calculation.berechnelbmin(zugstab, querdruck, stabdurchmesser, a1, a4, lbrqdy);
		lbd = Math.max(lbd, lbmin);
		String uebersicht = new String(Strings.uebersichtsAusgabe(zugstab, verankerungsart, geraderStab,
				engeVerbuegelung, fyd, this.betonklasseAuswahl, guterVerbund, fbd, stabdurchmesser, dSchlaufe,
				this.verankerungsartAuswahl, cd, angeschweißteQuerstaebe, querdruck, this.erforderlicheBew,
				this.vorhandeneBew, lbd, a1, a2, a3, a4, a5, ausnutzung, lbrqdy, lbmin));
//		String ergebnisString = "Hier werden die Ergebnisse angezeigt :)\n";

		ergebnisFeld.setText(uebersicht);
		this.ergebnisStringsInTextFeld = uebersicht;
	}

	public void zeigeErgbnisseInTextArea(ActionEvent event) {
		// berechnen Methode aufrufen...
	}

	public void ergebnisseInZwischenablageKopieren(ActionEvent event) {
		ClipboardUtil.inZwischenablageKopieren(this.ergebnisStringsInTextFeld);
		System.out.println("Ergebnisse wurden in zwischenablage Kopiert");
	}

	public void submitErfBew(ActionEvent event) {
		String erforderlicheBew = textFieldErfBew.getText().trim();
		erforderlicheBew = erforderlicheBew.replace(",", "."); // Kommas durch Punkte ersetzen
		try {
			double doubleErfBew = Double.parseDouble(erforderlicheBew);
			this.erforderlicheBew = doubleErfBew;
//			System.out.println("Eingabe war: " + this.erforderlicheBew);
		} catch (NumberFormatException e) {
			// Fehlermeldung anzeigen oder behandeln
			System.out.println("Methode: SbumitErfBew Ungültige Eingabe: " + this.erforderlicheBew);
		}
	}

	public void submitVorBew(ActionEvent event) {
		String vorhandeneBew = textFieldVorBew.getText().trim();
		vorhandeneBew = vorhandeneBew.replace(",", "."); // Kommas durch Punkte ersetzen
		try {
			double doubleVorBew = Double.parseDouble(vorhandeneBew);
			this.vorhandeneBew = doubleVorBew;
//			System.out.println("Eingabe war: " + this.vorhandeneBew);
		} catch (NumberFormatException e) {
			// Fehlermeldung anzeigen oder behandeln
			System.out.println(" Methode submitVorBew Ungültige Eingabe: " + this.vorhandeneBew);
		}
	}

}
