package Vue;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class Donnees {
	public static Color COULEUR_BG = new Color(225f/255f,225f/255f,220f/255f,1);
	public static Color COULEUR_TEXT = new Color(37f/255f,37f/255f,37f/255f,1);
	
	public static Font FONT_TEXT = Font.loadFont(ClassLoader.getSystemClassLoader().getResourceAsStream("LuckiestGuy.ttf"), 42);
	public static Font FONT_PLAY = Font.loadFont(ClassLoader.getSystemClassLoader().getResourceAsStream("LuckiestGuy.ttf"), 72);

	public static String GAUFRE_M = "gaufreM.png";
	public static String GAUFRE_BD = "gaufreBD.png";
	public static String GAUFRE_BG = "gaufreBG.png";
	public static String GAUFRE_HG = "gaufreHG.png";
	public static String GAUFRE_HD = "gaufreHD.png";
	public static String GAUFRE_CROQUE_COIN = "gaufreCroqueCoin.png";
	public static String GAUFRE_CROQUE_COIN_EXT = "gaufreCroqueCoinExt.png";
	public static String GAUFRE_CROQUE_COTE = "gaufreCroqueCote.png";
	
	public static String LOGO_FLECHE_DROITE ="logo_fleche_droite.png";
	public static String LOGO_FLECHE_GAUCHE ="logo_fleche_gauche.png";
	public static String LOGO_SAUVEGARDE ="logo_save.png";
	public static String LOGO_FERMER ="logo_fermer.png";
	
	public static Image IMG_GAUFRE_M = new Image("gaufreM.png");
	public static Image IMG_GAUFRE_BD = new Image("gaufreBD.png");
	public static Image IMG_GAUFRE_BG = new Image("gaufreBG.png");
	public static Image IMG_GAUFRE_HD = new Image("gaufreHD.png");
	public static Image IMG_GAUFRE_HG= new Image("gaufreHG.png");
	public static Image IMG_GAUFRE_CROQUE_COIN = new Image("gaufreCroqueCoin.png");
	public static Image IMG_GAUFRE_CROQUE_COIN_EXT = new Image("gaufreCroqueCoinExt.png");
	public static Image IMG_GAUFRE_CROQUE_COTE_DROIT = new Image("gaufreCroqueCoteDroit.png");
	public static Image IMG_GAUFRE_CROQUE_COTE_BAS = new Image("gaufreCroqueCoteBas.png");
	public static Image IMG_POISON = new Image("poison.png");


}
