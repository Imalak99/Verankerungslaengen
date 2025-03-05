package backend;

public class Kenngrößen {

	// Array der Betonfestigkeiten
	private static final String[] BETONGUETEN = { "ungültig", "C12/15", "C16/20", "C20/25", "C25/30", "C30/37",
			"C35/45", "C40/50", "C45/55", "C50/60" };

	// fbd-Werte für guten Verbund
	private static final double[] FBD_GUT = { 0, 1.7, 2.0, 2.3, 2.7, 3.0, 3.4, 3.7, 4.0, 4.3 };

	// fbd-Werte für mäßigen Verbund
	private static final double[] FBD_MAESSIG = { 0, 1.2, 1.4, 1.6, 1.9, 2.1, 2.4, 2.6, 2.8, 3.0 };

	// Array der Verankerungsart
	private static final String[] VERANKERUNGSART = { "ungültig", "Gerades Stabende", "Haken", "Winkelhaken",
			"Schlaufe" };

	private static final double[] DURCHMESSER = { 0, 6, 8, 10, 12, 14, 16, 20, 25, 28 };

	/**
	 * Gibt die Bezeichnung der Betonfestigkeit zurück.
	 * 
	 * @param key Index der Betonfestigkeit (1-9)
	 * @return Betonfestigkeitsklasse oder "ungültig"
	 */
	public static String betonguete(int key) {
		if (key >= 1 && key < BETONGUETEN.length) {
			return BETONGUETEN[key];
		}
		return "ungültig";
	}

	/**
	 * Gibt den fbd-Wert basierend auf der Betonfestigkeit und den
	 * Verbundbedingungen zurück.
	 * 
	 * @param betonwahl    Index der Betonfestigkeit (1-9)
	 * @param guterVerbund true für guten Verbund, false für mäßigen Verbund
	 * @return fbd-Wert oder -1 bei ungültiger Eingabe
	 */
	public static double fbdWert(int betonwahl, boolean guterVerbund) {
		if (betonwahl >= 1 && betonwahl <= 9) {
			return guterVerbund ? FBD_GUT[betonwahl] : FBD_MAESSIG[betonwahl];
		}
		return -1; // -1 als Fehlerwert
	}

	/**
	 * Gibt die Bezeichnung der Verankerungsart zurück.
	 * 
	 * @param key Index der Verankerungsart (1-4)
	 * @return Verankerungsart oder "ungültig"
	 */
	public static String verankerungsart(int key) {
		if (key >= 1 && key < VERANKERUNGSART.length) {
			return VERANKERUNGSART[key];
		}
		return "ungültig";
	}

	public static double durchmesser(int ds_auswahl) {
		return DURCHMESSER[ds_auswahl];
	}

}
