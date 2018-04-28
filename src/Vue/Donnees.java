package Vue;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class Donnees {
	public static Color COULEUR_BG = new Color(225f/255f,225f/255f,220f/255f,1);
	public static Color COULEUR_TEXT = new Color(37f/255f,37f/255f,37f/255f,1);
	
	public static Font FONT_TEXT = Font.loadFont(ClassLoader.getSystemClassLoader().getResourceAsStream("LuckiestGuy.ttf"), 42);

	public static String GAUFRE_M = "gaufreM.png";
	public static String LOGO_FLECHE_DROITE ="logo_fleche_droite.png";
	public static String LOGO_FLECHE_GAUCHE ="logo_fleche_gauche.png";
	public static String LOGO_SAUVEGARDE ="logo_save.png";
	public static String LOGO_FERMER ="logo_fermer.png";




}
