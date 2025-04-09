package backend;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class ClipboardUtil {

	public static void inZwischenablageKopieren(String text) {
		if (text == null) {
			text = "es ist nichts in der zwischenablage"; // oder: return;
		}
		StringSelection selection = new StringSelection(text);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
	}

}
