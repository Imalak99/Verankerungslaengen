package backend;

public class Strings {

	public static String uebersichtsAusgabe(boolean zugstab, int va, boolean geraderStab, boolean engeVerbuegelung,
			double fyd, String betonklasse, boolean guterVerbund, double fbd, double ds, double dSchlaufe,
			String verankerungsart, double cd, double a, double c1, double c, boolean querstaebe, boolean querdruck,
			double erfB, double vorhB, double lbd, double a1, double a2, double a3, double a4, double a5,
			double ausnutzung, double lbrqdy, double lbmin) {
		StringBuilder s = new StringBuilder();
//		s.append("E R G E B N I S S E:\n===========\n");
		s.append("RANDBEDINGUNGEN\n");
		s.append(String.format(zugstab ? "Zugstab\n" : "Druckstab\n"));
		s.append(String.format("Stahlfestigkeit fyd = 500/1,15 = %.2f [N/mm²]\n", fyd));
		s.append(String.format("Betongüte: %s\n", betonklasse));
		s.append(String.format("Verbundbedingung: %s\n", guterVerbund ? "guter Verbund" : "mäßiger Verbund"));
		s.append(String.format("Verbundspannung: fbd = %.2f [N/mm²]\n", fbd));
		s.append(String.format("Bewehrungsdurchmesser: %.0f [mm]\n", ds));
		s.append(String.format("a = %.2f [mm]\n", a));
		s.append(String.format("c1 = %.2f [mm]\n", c1));
		s.append(String.format("c = %.2f [mm]\n", c));
		s.append(String.format("cd = %.2f [mm]\n", cd));
		s.append(String.format("Verankerung: %s\n", verankerungsart));
		s.append(String.format("Querbewehrung: vernachlässigt\n", a3));
		s.append(String.format("Angeschweißte Querstäbe: %s \n", querstaebe ? "vorhanden" : "nicht vorhanden"));
		s.append(String.format("Direkte Lagerung: %s\n", querdruck ? "Vorhanden" : "nicht Vorhanden"));
		s.append(String.format("Enge Verbügelung: %s\n", engeVerbuegelung ? "Vorhanden" : "nicht Vorhanden"));

		s.append("\n");

		s.append("ALPHA-WERTE\n");
		s.append(String.format(" α1 = %.2f\n", a1));
		s.append(String.format(" α2 = %.2f\n", a2));
		s.append(String.format(" α3 = %.2f\n", a3));
		s.append(String.format(" α4 = %.2f\n", a4));
		s.append(String.format(" α5 = %.2f\n", a5));
		s.append("\n");

//		s.append("GRUNDMAß:\n");
//		s.append(String.format(
//				"lb,rqd,y = (ds / 4) * (fyd / fbd)\n             = (%.2f / 4) * (%.2f / %.2f)\n             = %.2f [mm]\n",
//				ds, fyd, fbd, lbrqdy));
		s.append("GRUNDMAß\n");
		s.append(String.format("lb,rqd,y = (ds / 4) * (fyd / fbd) = (%.2f / 4) * (%.2f / %.2f) = %.2f [mm]\n", ds, fyd,
				fbd, lbrqdy));

		s.append("\n");
//		s.append(String.format("As erf. = %.2f [cm²]\n", erfB));
//		s.append(String.format("As vorh. = %.2f [cm²]\n", vorhB));
		s.append(String.format("AUSNUTZUNGSGRAD\nerf. As / vorh. As = %.2f / %.2f = %.2f\n", erfB, vorhB, ausnutzung));
		s.append("\n");
		s.append("MINDESTVERANKERUNGSLÄNGE\n");
		if (!zugstab) {
//			s.append(String.format(
//					"lb,min = max {0,6 * lb,rqd,y ; 10 * ds}\n                           = max {0,6 * %.2f ; 10 * %.2f } \n       = max { %.2f ; %.2f}\n       = %.2f [mm]\n",
//					lbrqdy, ds, 0.6 * lbrqdy, 10 * ds, lbmin));

			s.append("lb,min = max {0,6 * lb,rqd,y ; 10 * ds}\n");
			s.append(String.format("           = max {0,6 * %.2f ; 10 * %.2f }\n", lbrqdy, ds));
			s.append(String.format("           = max { %.2f ; %.2f}\n", 0.6 * lbrqdy, 10 * ds));
			s.append(String.format("           = %.2f [mm]\n", lbmin));

		} else {
			s.append(String.format(
					"lb,min = max {0,3 * α1 * α4 * lb,rqd,y ; 10 * ds} \n           = max {0,3 * %.2f * %.2f * %.2f ; 10 * %.2f } \n           = max { %.2f ; %.2f}\n           = %.2f [mm]\n",
					a1, a4, lbrqdy, ds, 0.3 * a1 * a4 * lbrqdy, 10 * ds, lbmin));
		}
		s.append("\n");
		s.append("VERANKERUNGSLÄNGE\n");
		s.append("lbd ≥ lb,min\n");
		s.append("lbd = α1 * α2 * α3 * α4 * α5 * (erf. As / vorh. As) * lb,rqd,y\n");

		s.append(String.format("      = %.2f * %.2f * %.2f * %.2f * %.2f * %.2f * %.2f\n      = %.2f [mm]\n", a1, a2,
				a3, a4, a5, ausnutzung, lbrqdy, a1 * a2 * a3 * a4 * a5 * ausnutzung * lbrqdy));
		s.append("\n");

		s.append(String.format("Gewählt: lbd = %.2f [cm]", Math.ceil(lbd / 10)));
		return s.toString();
	}

}
