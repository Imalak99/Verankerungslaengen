package programm;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

import backend.Calculation;
import backend.Kenngrößen;
import backend.Strings;

public class Programm {

	public static void main(String[] args) {

		// Als infos zu den einzelnen Eingabewerten hinzufügen, was man machen sollte,
		// wenn man keine infos darüber hat. ZB. wenn man nicht weiss ob enge
		// Verbügelung
		// vorhaden ist: Annahme: keine enge Verbügelung.

		// Konstanten
		boolean zugstab = true;
		int va = 1; // Verankerungsart 1 falls Druckstab
		String verankerungsart = Kenngrößen.verankerungsart(va);
		double fyd = 435; // Streckgrenze des Stahls [N/mm2]
		boolean geraderStab = true;
		boolean engeVerbuegelung = true;
		boolean querstaebe = true;
		boolean querdruck = true;
		double a = 150; // Horizontaler Abstand der Längsbewehrungseisen in mm
		double c1 = 35; // Betondeckung zur Seite in mm
		double c = 35; // Betondeckung nach unten in mm
		double dSchlaufe = 100; // Durchmesser der Schlaufe in mm

		try (Scanner scanner = new Scanner(System.in)) {

			// Zug- oder Druckstab
			System.out.println("Zug- oder Druckstab (1 oder 0):");
			System.out.println("1 = Zugstab");
			System.out.println("0 = Druckstab");
			int auswahlZugstab = scanner.nextInt();
			if (auswahlZugstab < 0 || auswahlZugstab > 1) {
				System.out.println("ungültige Eingabe.");
				return;
			}
			if (auswahlZugstab == 0) {
				zugstab = false;
			}

			// Verbundbedingung wählen
			System.out.println("Wähle Verbundbedingung (1 oder 0): ");
			System.out.println("1 = guter Verbund");
			System.out.println("0 = mäßiger Verbund");
			int gv = scanner.nextInt();
			if (gv < 0 || gv > 1) {
				System.out.println("ungültige Verbundbedingung.");
				return;
			}
			boolean guterVerbund = Calculation.guterVerbund(gv);

			// Betonfestigkeit wählen
			System.out.println("Wähle die Betonfestigkeit (1-9): ");
			System.out.println("1 = C12/15");
			System.out.println("2 = C16/20");
			System.out.println("3 = C20/25");
			System.out.println("4 = C25/30");
			System.out.println("5 = C30/37");
			System.out.println("6 = C35/45");
			System.out.println("7 = C40/50");
			System.out.println("8 = C45/55");
			System.out.println("9 = C50/60");
			int betonwahl = scanner.nextInt();
			String betonguete = Kenngrößen.betonguete(betonwahl);
			if (betonwahl < 1 || betonwahl > 9) {
				System.out.println("Ungültige Betonfestigkeit!");
				return;
			}

			// fbd-Wert berechnen
			double fbd = Kenngrößen.fbdWert(betonwahl, guterVerbund);
			if (fbd < 0) {
				System.out.println("Ungültiger fbd-Wert!");
				return;
			}

			// Bewehrungsdurchmesser eingeben
			// Hier probeweise einmal mit whileschleife, sodass das Programm nicht
			// beendet wird wenn man eine ungültige Eingabe macht.
			double ds = 0;
			boolean gueltigeEingabe = false;
			while (!gueltigeEingabe) {
				System.out.println("Wähle Bewehrungsdurchmesser (1-9): ");
				System.out.println("1 =  6 mm");
				System.out.println("2 =  8 mm");
				System.out.println("3 = 10 mm");
				System.out.println("4 = 12 mm");
				System.out.println("5 = 14 mm");
				System.out.println("6 = 16 mm");
				System.out.println("7 = 20 mm");
				System.out.println("8 = 25 mm");
				System.out.println("9 = 28 mm");
				int ds_auswahl = scanner.nextInt();

				if (ds_auswahl < 1 || ds_auswahl > 9) {
					System.out.println("Ungültige Eigabe.");
				} else {
					gueltigeEingabe = true;
					ds = Kenngrößen.durchmesser(ds_auswahl);
				}
			}
			if (zugstab) {

				// Verankerungsart
				System.out.println("Wähle Verankerungsart (1-4):");
				System.out.println("1 = Gerades Stabende");
				System.out.println("2 = Haken");
				System.out.println("3 = Winkelhaken");
				System.out.println("4 = Schlaufe");
				va = scanner.nextInt();
				verankerungsart = Kenngrößen.verankerungsart(va);
				if (va < 1 || va > 4) {
					System.out.println("Ungültige Verankerungsart!");
					return;
				}
				if (va != 1) {
					geraderStab = false;
				}
				if (va == 2 || va == 3) {
					System.out.println("Enge Verbügelung (1 oder 2): ");
					System.out.println("1 = vorhanden ");
					System.out.println("0 = nicht vorhanden ");
					int ev = scanner.nextInt();
					if (ev < 0 || ev > 1) {
						System.out.println("Ungültiger eintrag.");
						return;
					}
					if (ev == 0) {
						engeVerbuegelung = false;
					}
				}
			}
			// Angeschweißte Querstäbe
			System.out.println("Angeschweißte Querstäbe: ");
			System.out.println("1 = vorhanden ");
			System.out.println("0 = nicht vorhanden ");
			int qs = scanner.nextInt();
			if (qs < 0 || qs > 1) {
				System.out.println("Ungültiger eintrag.");
				return;
			}
			if (qs == 0) {
				querstaebe = false;
			}
			if (zugstab) {
				// Querdruck
				System.out.println("Direkte Lagerung:");
				System.out.println("1 = vorhanden");
				System.out.println("0 = nicht vorhanden");
				int qd = scanner.nextInt();
				if (qd < 0 || qd > 1) {
					System.out.println("Ungültiger eintrag.");
					return;
				}
				if (qd == 0) {
					querdruck = false;
				}
			}

			// Ausnutzung
			System.out.println("Erf. Bewehrung in cm2 eingeben:");
			double erfBew = scanner.nextDouble();
			System.out.println("Vorh. Bewehrung in cm2 eingeben:");
			double vorhBew = scanner.nextDouble();

			double cd = Calculation.cd(va, a, c1, c);
			double a1 = Calculation.a1(zugstab, va, engeVerbuegelung, geraderStab, cd, ds, dSchlaufe);
			double a2 = Calculation.a2(zugstab, geraderStab, cd, ds);
			double a3 = Calculation.a3();
			double a4 = Calculation.a4(querstaebe);
			double a5 = Calculation.a5(zugstab, querdruck);
			double ausnutzung = Calculation.ausnutzung(erfBew, vorhBew);

			// Verankerungslänge berechnen
			double lbd = Calculation.berechneVerankerungslaenge(ds, fyd, fbd, a1, a2, a3, a4, a5, ausnutzung);
			double lbrqdy = Calculation.berechneGrundmaß(ds, fyd, fbd);
			double lbmin = Calculation.berechnelbmin(zugstab, querdruck, ds, a1, a4, lbrqdy);
			lbd = Math.max(lbd, lbmin);
			String uebersicht = new String(Strings.uebersichtsAusgabe(zugstab, va, geraderStab, engeVerbuegelung, fyd,
					betonguete, guterVerbund, fbd, ds, dSchlaufe, verankerungsart, cd, a, c1, c, querstaebe, querdruck,
					erfBew, vorhBew, lbd, a1, a2, a3, a4, a5, ausnutzung, lbrqdy, lbmin));
			System.out.println(uebersicht);
			System.out.println("Drücke c und bestätige mit Enter um Übersicht in Zwischenablage zu kopieren.");
			String kopie = scanner.next();
			if (kopie.equals("c")) {
				StringSelection selection = new StringSelection(uebersicht);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
				System.out.println("Übersicht wurde in die Zwischenablage kopiert.");
			} else {
				System.out.println("Übersicht wurde nicht in Zwischenablage kopiert.");
			}

		}
	}
}
