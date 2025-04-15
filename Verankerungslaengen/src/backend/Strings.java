package backend;

public class Strings {

	public static String uebersichtsAusgabe(boolean zugstab, int va, boolean geraderStab, boolean engeVerbuegelung,
			double fyd, String betonklasse, boolean guterVerbund, double fbd, double ds, double dSchlaufe,
			String verankerungsart, double cd, boolean querstaebe, boolean querdruck, double erfB, double vorhB,
			double lbd, double a1, double a2, double a3, double a4, double a5, double ausnutzung, double lbrqdy,
			double lbmin) {
		StringBuilder s = new StringBuilder();
		s.append("Ergebnisse:\n===========\n");
		s.append(String.format("Stahlfestigkeit fyd = 500/1,15 = %.2f [N/mm²]\n", fyd));
		s.append(String.format("Betongüte: %s\n", betonklasse));
		s.append(String.format("Verbundbedingung: %s\n", guterVerbund ? "guter Verbund" : "mäßiger Verbund"));
		s.append(String.format("Verbundspannung: fbd = %.2f [N/mm²]\n", fbd));
		s.append(String.format("Bewehrungsdurchmesser: %.0f [mm]\n", ds));
		s.append(String.format("Maßgebende Betondeckung: cd = %.2f [mm]\n", cd)); // Ausdruck richtig?
		s.append(String.format("Verankerung: %s (α1 = %.2f)\n", verankerungsart, a1));
		s.append(String.format("Mindestbetondeckung: %.2f [mm] (α2 = %.2f)\n", cd, a2));
		s.append(String.format("Querbewehrung: vernachlässigt (α3 = %.2f)\n", a3));
		s.append(String.format("Angeschweißte Querstäbe: %s (α4 = %.2f)\n",
				querstaebe ? "vorhanden" : "nicht vorhanden", a4));
		s.append(String.format("Querdruck: %s (α5 = %.2f)\n", querdruck ? "Direktes Auflager" : "", a5));
		s.append("\n");
		s.append(String.format(
				"Grundmaß:\nlb,rqd,y = (ds / 4) * (fyd / fbd)\n                   = (%.2f / 4) * (%.2f / %.2f)\n                   = %.2f [mm]\n",
				ds, fyd, fbd, lbrqdy));
		s.append("\n");
		s.append(String.format("As erf. = %.2f [cm²]\n", erfB));
		s.append(String.format("As vorh. = %.2f [cm²]\n", vorhB));
		s.append(String.format(
				"Ausnutzungsgrad = erf. As / vorh. As\n                = %.2f / %.2f\n                = %.2f\n", erfB,
				vorhB, ausnutzung));
		s.append("\n");
		s.append("Verankerungslänge lbd = α1 * α2 * α3 * α4 * α5 * (erf. As / vorh. As) * lb,rqd,y ≥ lb,min\n");

		s.append(String.format(
				"                      = %.2f * %.2f * %.2f * %.2f * %.2f * %.2f * %.2f\n                      = %.2f [mm]\n",
				a1, a2, a3, a4, a5, ausnutzung, lbrqdy, a1 * a2 * a3 * a4 * a5 * ausnutzung * lbrqdy));
		s.append("\n");
		if (!zugstab) {
			s.append(String.format(
					"lb,min = max {0,6 * lb,rqd,y ; 10 * ds} \n       = max {0,6 * %.2f ; 10 * %.2f } \n       = max { %.2f ; %.2f}\n       = %.2f [mm]\n",
					lbrqdy, ds, 0.6 * lbrqdy, 10 * ds, lbmin));
		} else {
			s.append(String.format(
					"lb,min = max {0,3 * α1 * α4 * lb,rqd,y ; 10 * ds} \n       = max {0,3 * %.2f * %.2f * %.2f ; 10 * %.2f } \n       = max { %.2f ; %.2f}\n       = %.2f [mm]\n",
					a1, a4, lbrqdy, ds, 0.3 * a1 * a4 * lbrqdy, 10 * ds, lbmin));
		}

		s.append("\n");
		s.append(String.format("gewählt: lbd = %.2f [cm]", Math.ceil(lbd / 10)));
		return s.toString();
	}

}
