package gui;

import java.io.IOException;

import backend.Calculation;
import backend.ClipboardUtil;
import backend.GuiEingabeParameter;
import backend.InfoTexte;
import backend.Kenngrößen;
import backend.Strings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Scene2Controller {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextArea ergebnisFeld;
	@FXML
	private TextArea infoFeld;
	@FXML
	private ImageView infoBild;
	@FXML
	private TextField textFieldErfBew;
	@FXML
	private TextField textFieldVorBew;
	@FXML
	private TextField textFieldHorizontalerAbstand;
	@FXML
	private TextField textFieldBetondeckungSeitlich;
	@FXML
	private TextField textFieldBetondeckungObenUnten;
	@FXML
	private TextField textFieldDSchlaufe;
	@FXML
	private Label labelDSchlaufe;
	@FXML
	private Label labelDSchlaufeEinheit;
	@FXML
	private Button infoButtonDSchlaufe;

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
	private String[] engeVerbuegelung = { "vorhanden", "nicht vorhanden" };

	private String zugDruckstabAuswahl = zugDruckstab[0];
	private String verankerungsartAuswahl = verankerungsart[0];
	private String betonklasseAuswahl = betonklasse[0];
	private String verbundbedingungAuswahl = verbundbedingung[0];
	private String stabdurchmesserAuswahl = stabdurchmesser[0];
	private String angeschweißteQuerstaebeAuswahl = angeschweißteQuerstaebe[1];
	private String lagerungAuswahl = lagerung[1];
	private String engeVerbuegelungAuswahl = engeVerbuegelung[1];
	private double vorhandeneBew = 1.;
	private double erforderlicheBew = 1.;
	private double fyd = 435;
	private double a = 150;
	private double c1 = 30;
	private double c = 30;
	private double dSchlaufe = 100;

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
	@FXML
	private ComboBox<String> choiceBoxEngeVerbuegelung;

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
		textFieldErfBew.setText("10");
		textFieldVorBew.setText("10");
		textFieldHorizontalerAbstand.setText("100");
		textFieldBetondeckungObenUnten.setText("30");
		textFieldBetondeckungSeitlich.setText("30");
		textFieldDSchlaufe.setDisable(true);

		infoButtonDSchlaufe.setDisable(true);
		infoButtonDSchlaufe.setTextFill(Color.GRAY);

		// Listener für die ComboBox
		choiceBoxVerankerungsart.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.equals("Schlaufe")) {
				textFieldDSchlaufe.setDisable(false); // Aktivieren bei Auswahl "Schlaufe"
				infoButtonDSchlaufe.setDisable(false);
				labelDSchlaufe.setTextFill(Color.BLACK);
				labelDSchlaufeEinheit.setTextFill(Color.BLACK);
				infoButtonDSchlaufe.setTextFill(Color.BLACK);
			} else {
				textFieldDSchlaufe.setDisable(true); // Deaktivieren bei Auswahl "Andere Art"
				infoButtonDSchlaufe.setDisable(true);
				labelDSchlaufe.setTextFill(Color.GRAY);
				labelDSchlaufeEinheit.setTextFill(Color.GRAY);
				infoButtonDSchlaufe.setTextFill(Color.GRAY);
			}
		});

		labelDSchlaufe.setTextFill(Color.GRAY);
		labelDSchlaufeEinheit.setTextFill(Color.GRAY);

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
		choiceBoxAngeschweißteQuerstäbe.setValue(angeschweißteQuerstaebe[1]);

		choiceBoxLagerung.getItems().addAll(lagerung);
		choiceBoxLagerung.setValue(lagerung[1]);

		choiceBoxEngeVerbuegelung.getItems().addAll(engeVerbuegelung);
		choiceBoxEngeVerbuegelung.setValue(engeVerbuegelung[1]);

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
		choiceBoxEngeVerbuegelung.setOnAction(e -> {
			this.engeVerbuegelungAuswahl = choiceBoxEngeVerbuegelung.getValue();
		});

	}

	public void berechneErgebnis(ActionEvent event) throws IOException {
		submitErfBew(event);
		submitVorBew(event);
		submitBetondeckungObenUnten(event);
		submitBetondeckungSeitlich(event);
		submitHorizontalerAbstand(event);
		submitDSchlaufe(event);

		int betonwahl = GuiEingabeParameter.betonwahlStringToInt(this.betonklasseAuswahl);
		boolean guterVerbund = GuiEingabeParameter.guterVerbundStringToBoolean(this.verbundbedingungAuswahl);
		double fbd = Kenngrößen.fbdWert(betonwahl, guterVerbund);
		System.out.println(fbd);

		boolean zugstab = GuiEingabeParameter.zugstabStringToBoolean(this.zugDruckstabAuswahl);
		boolean geraderStab = GuiEingabeParameter.geraderStabStringToBoolean(this.verankerungsartAuswahl);
		int verankerungsart = GuiEingabeParameter.verankerungsartStringToInt(verankerungsartAuswahl);
		boolean engeVerbuegelung = GuiEingabeParameter.engeVerbuegelungStringToBoolean(engeVerbuegelungAuswahl); // noch
																													// in
																													// der
																													// GUI
																													// ergänzen!!!
		double a = this.a; // Horizontaler Abstand der Längsbewehrungseisen in mm, noch vergänzen in
							// GUI
		double c1 = this.c1; // Betondeckung zur Seite in mm ----"-----
		double c = this.c; // Betondeckung nach unten in mm ----"-----
		double cd = GuiEingabeParameter.berechneCd(verankerungsartAuswahl, a, c1, c);
		double dSchlaufe = this.dSchlaufe; // Durchmesser der Schlaufe in mm ----"-----

		String stabdurchmesserOnlyNumber = this.stabdurchmesserAuswahl.replaceAll("[^\\d.]", "");
		double stabdurchmesser = Double.parseDouble(stabdurchmesserOnlyNumber);
		double a1 = Calculation.a1(zugstab, verankerungsart, geraderStab, engeVerbuegelung, cd, stabdurchmesser,
				dSchlaufe);
		double a2 = Calculation.a2(zugstab, geraderStab, cd, stabdurchmesser);
		double a3 = Calculation.a3();
		boolean angeschweißteQuerstaebe = GuiEingabeParameter
				.angeschweißteQuerstaebeStringToBoolean(this.angeschweißteQuerstaebeAuswahl); // in GUI ergänzen
		double a4 = Calculation.a4(angeschweißteQuerstaebe);
		boolean querdruck = GuiEingabeParameter.querdruckStringToBoolean(this.lagerungAuswahl);
		double a5 = Calculation.a5(zugstab, querdruck);
		double ausnutzung = this.erforderlicheBew / this.vorhandeneBew;

		double lbd = Calculation.berechneVerankerungslaenge(stabdurchmesser, this.fyd, fbd, a1, a2, a3, a4, a5,
				ausnutzung);

		double lbrqdy = Calculation.berechneGrundmaß(stabdurchmesser, fyd, fbd);
		double lbmin = Calculation.berechnelbmin(zugstab, querdruck, stabdurchmesser, a1, a4, lbrqdy);
		lbd = Math.max(lbd, lbmin);
		String uebersicht = new String(Strings.uebersichtsAusgabe(zugstab, verankerungsart, geraderStab,
				engeVerbuegelung, fyd, this.betonklasseAuswahl, guterVerbund, fbd, stabdurchmesser, dSchlaufe,
				this.verankerungsartAuswahl, cd, a, c1, c, angeschweißteQuerstaebe, querdruck, this.erforderlicheBew,
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

	public void submitHorizontalerAbstand(ActionEvent event) {
		String horizontalerAbstand = textFieldHorizontalerAbstand.getText().trim();
		horizontalerAbstand = horizontalerAbstand.replace(",", "."); // Kommas durch Punkte ersetzen
		try {
			double doubleHorizontalerAbstand = Double.parseDouble(horizontalerAbstand);
			this.a = doubleHorizontalerAbstand;
		} catch (NumberFormatException e) {
			// Fehlermeldung anzeigen oder behandeln
			System.out.println(" Methode submitHorizontalerAbstand Ungültige Eingabe: " + this.a);
		}
	}

	public void submitBetondeckungSeitlich(ActionEvent event) {
		String seitlicheBetondeckung = textFieldBetondeckungSeitlich.getText().trim();
		seitlicheBetondeckung = seitlicheBetondeckung.replace(",", "."); // Kommas durch Punkte ersetzen
		try {
			double doubleSeitlicheBetondeckung = Double.parseDouble(seitlicheBetondeckung);
			this.c1 = doubleSeitlicheBetondeckung;
//			System.out.println("Eingabe war: " + this.vorhandeneBew);
		} catch (NumberFormatException e) {
			// Fehlermeldung anzeigen oder behandeln
			System.out.println(" Methode submitBetondeckungSeitlich Ungültige Eingabe: " + this.c1);
		}
	}

	public void submitBetondeckungObenUnten(ActionEvent event) {
		String obenUntenBetondeckung = textFieldBetondeckungObenUnten.getText().trim();
		obenUntenBetondeckung = obenUntenBetondeckung.replace(",", "."); // Kommas durch Punkte ersetzen
		try {
			double doubleObenUntenBetondeckung = Double.parseDouble(obenUntenBetondeckung);
			this.c = doubleObenUntenBetondeckung;
//			System.out.println("Eingabe war: " + this.vorhandeneBew);
		} catch (NumberFormatException e) {
			// Fehlermeldung anzeigen oder behandeln
			System.out.println(" Methode submitBetondeckungObenUnten Ungültige Eingabe: " + this.c);
		}
	}

	public void submitDSchlaufe(ActionEvent event) {
		String dSchlaufe = textFieldDSchlaufe.getText().trim();
		dSchlaufe = dSchlaufe.replace(",", "."); // Kommas durch Punkte ersetzen
		System.out.println(dSchlaufe);
		try {
			double doubledSchlaufe = Double.parseDouble(dSchlaufe);
			this.dSchlaufe = doubledSchlaufe;
//			System.out.println("Eingabe war: " + this.vorhandeneBew);
		} catch (NumberFormatException e) {
			// Fehlermeldung anzeigen oder behandeln
			System.out.println(" Methode submitsubmitDSchlaufe Ungültige Eingabe: " + this.dSchlaufe);
		}
	}

	public void handleInfoFeld(ActionEvent event) throws IOException {
		Button clickedButton = (Button) event.getSource();
		String buttonId = clickedButton.getId();
//		System.out.println("Wir sind in der Methode handleInfoFeld");

		switch (buttonId) {
		case "infoButtonVerankerungslaenge":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.VERANKERUNGSLAENGE);
			break;
		case "infoButtonZugDruckstab":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.ZUG_DRUCKSTAB);
			break;
		case "infoButtonVerankerungsart":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.VERANKERUNGSART);
			break;
		case "infoButtonDSchlaufe":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.DSCHLAUFE);
			break;
		case "infoButtonBetonklasse":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.BETONKLASSE);
			break;
		case "infoButtonVerbundbedingung":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.VERBUNDBEDINGUNG);
			break;
		case "infoButtonStabdurchmesser":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.STABDURCHMESSER);
			break;
		case "infoButtonAngeschweißteQuerstaebe":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.ANGESCHWEISSTE_QUERSTÄBE);
			break;
		case "infoButtonDirekteLagerung":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.DIREKTE_LAGERUNG);
			break;
		case "infoButtonEngeverbuegelung":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.ENGERVERBÜGELUNG);
			break;
		case "infoButtonHorizontalerAbstandDerLaengseisen":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.a);
			Image img = new Image(getClass().getResource("/gui/Bilder/cd.PNG").toExternalForm());
			infoBild.setImage(img);
			break;
		case "infoButtonBetondeckungSeitlich":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.BETONDECKUNG_SEITLICH);
			break;
		case "infoButtonBetondeckungObenUnten":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.BETONDECKUNG_OBEN_UNTEN);
			break;
		case "infoButtonErfBewehrung":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.ERFORDERLICHE_BEWEHRUNG);
			break;
		case "infoButtonVorBewehrung":
			infoBild.setImage(null);
			infoFeld.setText(InfoTexte.VORHANDENE_BEWEHRUNG);
			break;
		default:
			infoFeld.setText("Kein Info-Text vorhanden.");
			break;
		}

	}

}
