package backend;

public class Calculation {

	public static double berechneGrundmaß(double ds, double fyd, double fbd) {
		return Math.ceil((ds / 4) * (fyd / fbd));
	}

	public static double berechneVerankerungslaenge(double ds, double fyd, double fbd, double a1, double a2, double a3,
			double a4, double a5, double ausnutzung) {
		double lbrqdy = berechneGrundmaß(ds, fyd, fbd);
		return a1 * a2 * a3 * a4 * a5 * lbrqdy * ausnutzung;
	}

	/**
	 * @param va Verankerungsart (1-4)
	 * @param a  Abstand der Längsbewehrungseisen
	 * @param c1 Betondeckung zur Seite
	 * @param c  Betondeckung nach unten
	 * @return Betondeckung minimal
	 */
	public static double cd(int va, double a, double c1, double c) {
		if (va == 1) {
			return Math.min(a / 2, Math.min(c1, c));
		} else if (va == 2 || va == 3) {
			return Math.min(a / 2, c1);
		} else if (va == 4) {
			return c;
		}
		System.out.println("ungültiger Wert für Verankerungsart.");
		return -1.0;
	}

	/**
	 * 
	 * @param va          Index für Verankerungsart (1-4).
	 * @param geraderStab wenn true
	 * @param cd          Mindestbetondeckung in mm
	 * @param ds          Bewehrungsdurchmesser in mm
	 * @param dSchlaufe   Schlaufendurchmesser in mm
	 * @return double α1
	 */
	public static double a1(boolean zugstab, int va, boolean geraderStab, boolean engeVerbuegeblung, double cd,
			double ds, double dSchlaufe) {
		if (!zugstab) {
			return 1.0;
		}
		if (va < 1 || va > 4) {
			System.out.println("ungültiger Wert für Verankerungsart.");
			return -1.0;
		}
		if (va == 2 || va == 3 && (engeVerbuegeblung || cd > 3 * ds)) {
			return 0.7;
		}
		if (va == 4) {
			if (cd >= 3 * ds && dSchlaufe >= 15 * ds) {
				return 0.5;
			} else {
				return 0.7;
			}
		}
		return 1.0;
	}

	public static double a2(boolean zugstab, boolean geraderStab, double cd, double ds) {
		if (!zugstab) {
			return 1.0;
		}
		if (geraderStab) {
			return Math.max(0.7, Math.min(1.0, 1 - (0.15 * (cd - ds) / ds)));
		} else {
			return Math.max(0.7, Math.min(1.0, 1 - (0.15 * (cd - 3 * ds) / ds)));
		}
	}

	public static double a3() {
		// Hier bei bedarf noch anpassen.
		// Vorerst auf der sicheren Seite liegend a3 = 1.0;
		return 1.0;
	}

	public static double a4(boolean querstaebe) {
		if (querstaebe) {
			return 0.7;
		} else {
			return 1.0;
		}
	}

	public static double a5(boolean zugstab, boolean querdruck) {
		if (!zugstab) {
			return 1.0;
		}
		double a = 2.0 / 3.0;
		return querdruck ? a : 1.0;
	}

	public static double ausnutzung(double erf, double vorh) {
		return erf / vorh;
	}

	public static boolean guterVerbund(int gv) {
		if (gv == 1) {
			return true;
		}
		return false;
	}

	public static double berechnelbmin(boolean zugstab, boolean querdruck, double ds, double a1, double a4,
			double lbrqdy) {
		if (!zugstab) {
			return Math.max(0.6 * lbrqdy, 10 * ds);
		}
		return Math.max(0.3 * a1 * a4 * lbrqdy, querdruck ? 6.7 : 10 * ds);
	}

}
