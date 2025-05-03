package programm;

import backend.Calculation;

public class Tests {
	public static void main(String[] args) {
		double a5;
		a5 = Calculation.a5(true, true);
		System.out.println(a5);
		System.out.println(String.format("%-20s %10.2f", "Ergebnis:", 120.0));

	}
}
