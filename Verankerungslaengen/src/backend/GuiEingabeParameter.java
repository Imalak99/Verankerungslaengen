package backend;

public class GuiEingabeParameter {

	public static boolean guterVerbundStringToBoolean(String verbundbedingungAuswahl) {
		if (verbundbedingungAuswahl == "guter Verbund") {
			return true;
		} else if (verbundbedingungAuswahl == "mäßiger Verbund") {
			return false;
		} else {
			System.out.println("ungültige Eingabe");
			return false;
		}
	}

	public static int betonwahlStringToInt(String betonklasse) {
		if (betonklasse == "C12/15") {
			return 1;
		} else if (betonklasse == "C16/20") {
			return 2;
		} else if (betonklasse == "C20/25") {
			return 3;
		} else if (betonklasse == "C25/30") {
			return 4;
		} else if (betonklasse == "C30/37") {
			return 5;
		} else if (betonklasse == "C35/45") {
			return 6;
		} else if (betonklasse == "C40/50") {
			return 7;
		} else if (betonklasse == "C45/55") {
			return 8;
		} else if (betonklasse == "C50/60") {
			return 9;
		} else {
			System.out.println("Ungültige Betonauswahl");
			return 0;
		}

	}

	public static boolean zugstabStringToBoolean(String zugDruckstabAuswahl) {
		if (zugDruckstabAuswahl == "Zugstab") {
			return true;
		} else if (zugDruckstabAuswahl == "Druckstab") {
			return false;
		}
		System.out.println("Falsche eingabe bei zugDruckstab");
		return false;
	}

	public static boolean geraderStabStringToBoolean(String verankerungsartAuswahl) {
		if (verankerungsartAuswahl == "Gerades Stabende") {
			return true;
		}
		return false;
	}

	public static double berechneCd(String verankerungsartAuswahl, double a, double c1, double c) {
		if (verankerungsartAuswahl == "Gerades Stabende") {
			return Math.min(a / 2, Math.min(c1, c));
		} else if (verankerungsartAuswahl == "Haken" || verankerungsartAuswahl == "Winkelhaken") {
			return Math.min(a / 2, c1);
		} else if (verankerungsartAuswahl == "Schlaufe") {
			return c;
		}
		System.out.println("berechnungCd: ungültiger Wert für Verankerungsart.");
		return -1.0;
	}

	public static int verankerungsartStringToInt(String verankerungsartAuswahl) {
		if (verankerungsartAuswahl == "Gerades Stabende") {
			return 1;
		} else if (verankerungsartAuswahl == "Haken") {
			return 2;
		} else if (verankerungsartAuswahl == "Winkelhaken") {
			return 3;
		} else if (verankerungsartAuswahl == "Schlaufe") {
			return 4;
		}
		System.out.println("Fehler in Methode verankerungsartAtringToInt");
		return 0;
	}

	public static boolean angeschweißteQuerstaebeStringToBoolean(String angeschweißteQuerstaebeAuswahl) {
		if (angeschweißteQuerstaebeAuswahl == "vorhanden") {
			return true;
		} else if (angeschweißteQuerstaebeAuswahl == "nicht vorhanden") {
			return false;
		} else {
			System.out.println("Fehler in Methode: angeschweißteQuerstaebeStringToBoolean");
			return false;
		}

	}

	public static boolean querdruckStringToBoolean(String lagerungAuswahl) {
		if (lagerungAuswahl == "vorhanden") {
			return true;
		} else if (lagerungAuswahl == "nicht vorhanden") {
			return false;
		} else {
			System.out.println("Fehler in Methode: querdruckStringToBoolean");
			return false;
		}

	}

	public static boolean engeVerbuegelungStringToBoolean(String engeVerbuegelungAuswahl) {
		if (engeVerbuegelungAuswahl == "vorhanden") {
			return true;
		} else if (engeVerbuegelungAuswahl == "nicht vorhanden") {
			return false;
		} else {
			System.out.println("Fehler in Methode: engeVerbuegelungStringToBoolean");
			return false;
		}
	}

}
