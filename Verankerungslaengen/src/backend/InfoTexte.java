package backend;

public class InfoTexte {

	public static final String VERANKERUNGSLAENGE = """
			Hier allgemein erklären, was eine Verankerungs-
			länge ist und wieso diese wichtig ist, wo diese Anfängt etc.
			Schreiben, dass es x Einflussparamter gibt die diese beeinflusst.
			Erklären wie das Programm funktioniert
			(immer neu Berechnen, InfoButtons).
			""";
	public static final String ZUG_DRUCKSTAB = """
			INFO ZUGSTAB / DRUCKSTAB
			----------------------------------------------------------

			Zugstab:
			- alle Verankerungsarten sind zugelassen
			→ mögliche Abminderung der Verankerungslänge durch α1

			Druckstab:
			- nur gerades Stabende als Verankerungsart zugelassen
			→ keine Abminderung der Verankerungslänge durch α1 möglich
			""";
	public static final String VERANKERUNGSART = """
			INFO VERANKERUNGSARTEN
			----------------------------------------------------------

			Für bestimmte Verankerungsarten kann unter bestimmten
			Voraussetzungen der α1 Wert zu 0,7 bzw. 0,5 angenommen
			werden, was eine Verringerung der Verankerungslänge zur
			Folge hat.

			Verankerungsarten
			- gerades Stabende
				→ α1 = 1,0

			- Haken
				→ α1 = 0,7 wenn cd ≥ 3⌀, sonst α1 = 1,0

			- Winkelhaken
				→ α1 = 0,7 wenn cd ≥ 3⌀, sonst α1 = 1,0

			- Schlaufe
				→ α1 = 0,7 wenn cd ≥ 3⌀, sonst α1 = 1,0
				→ α1 = 0,5 wenn cd ≥ 3⌀ und Dmin ≥ 15⌀, sonst α1 = 1,0


				Bilder einfügen von Verankerungsarten
				und was genau dann die Verankerungslänge da ist
			""";
	public static final String DSCHLAUFE = """
			INFO DURCHMESSER DER SCHLAUFE
			----------------------------------------------------------

			Wenn Dmin ≥ 15⌀  und cd ≥ 3⌀ kann α1 zu 0,5 Abgemindert
			werden.

			Bild einfügen

			""";
	public static final String BETONKLASSE = """
			INFO BETONKLASSE
			----------------------------------------------------------

			Die Betonfestigkeitsklasse beeinflusst die Verbundspannung
			fbd, welche den Grundwert der Verankerungslänge lb,rqd,y
			beeinflusst.

			→ Je höher die Betonfestigkeitsklasse,
			desto gerinder die Verankerungslänge.

			""";
	public static final String VERBUNDBEDINGUNG = """
			INFO VERBUNDBEDINGUNG
			----------------------------------------------------------

			Die Verbundbedingung beeinflüsst - wie die Betonklasse - die
			Verbundspannung fbd und somit den Grundwert der
			Verankerungslänge.

			→ ein mäßiger Verbund hat eine Vergrößerung der
			Verankerungslänge zur Folge.


			Bilder einfügen von Verbundbedingungen

			""";
	public static final String STABDURCHMESSER = """
			INFO STABDURCHMESSER
			----------------------------------------------------------

			Der Stabdurchmesser beeinflusst den Grundwert der
			Verankerungslänge.

			→ Je größer der Stabdurchmesser, desto größer die
			Verankerungslänge.


			""";
	public static final String ANGESCHWEISSTE_QUERSTÄBE = """
			INFO ANGESCHWEISSTE QUERSTÄBE
			----------------------------------------------------------

			Wenn angeschweißte Querstäbe vorhanden sind, wird ein Teil der
			Zugkraft durch Umlenk- bzw. Kontanktpressung aufgenommen.

			→ angeschweißte Querstäbe führen zu einer verkürzung der
			Verankerungslänge

			Bilder mit Beispielen

			""";
	public static final String DIREKTE_LAGERUNG = """
			INFO DIREKTE LAGERUNG
			----------------------------------------------------------

			Querdruck, wie er im Bereich von Auflagern vorhanden ist,
			hat einen positiven (verkürzenden) Einfluss auf die
			Verankerungslänge, da hierdurch der Verbund zischen Beton
			und Bewehrungs erhöht wird.
			Außerdem können unter Querdruck größere Querzugspannungen
			durch den Beton aufgenommen werden.

			→ an direkten Auflagern kann die Verankerungslänge daher
			pauschal mit dem α5 Wert von 2/3 abgemindert werden.

			""";
	public static final String ENGERVERBÜGELUNG = """
			INFO ENGE VERBÜGELUNG
			----------------------------------------------------------

			Eine enge Verbügelung liegt vor, wenn im Auflagerbereich ein
			deutlich geringerer Bügelabstand vorliegt, als es rechnerisch
			zur Aufnahme der Querkraft erforderlich ist.

			Ein Abstand von ca. 10 cm gilt als enge Verbügelung.

			Durch enge Verbügelung kann bei Haken oder Winkelhaken α1 zu 0,7 angenommen werden,
			auch wenn cd < 3⌀.

			""";
	public static final String a = """
			INFO VERANKERUNGSARTEN
			----------------------------------------------------------

			Lichter Abstand der Längseisen.



			Bild einfügen

			""";
	public static final String BETONDECKUNG_SEITLICH = """
			INFO VERANKERUNGSARTEN
			----------------------------------------------------------

			Bild einfügen

			""";
	public static final String BETONDECKUNG_OBEN_UNTEN = """
			INFO VERANKERUNGSARTEN
			----------------------------------------------------------

			""";
	public static final String ERFORDERLICHE_BEWEHRUNG = """
			INFO VERANKERUNGSARTEN
			----------------------------------------------------------

			""";
	public static final String VORHANDENE_BEWEHRUNG = """
			INFO VERANKERUNGSARTEN
			----------------------------------------------------------

			""";

}
