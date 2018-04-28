package Vue.GameObject;

import com.sun.javafx.geom.Vec2d;
import Modele.Couple;
import Modele.Plateau;
import Vue.Donnees;
import Vue.Cadre.Cadre;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PlateauGraphique extends GameObject {
	Plateau plateau;
	Cadre cadre;
	int tailleCase;

	public PlateauGraphique(Plateau plateau, Cadre cadre, int x, int y, int tailleCase) {
		super(x, y);
		this.plateau = plateau;
		this.cadre = cadre;
		this.tailleCase = tailleCase;
	}

	@Override
	public void update() {
		tailleCase = (int) (cadre.getHeight() * 0.9 / plateau.hauteur());
		position.x = cadre.getWidth() / 2 - (tailleCase * plateau.largeur() / 2);
		position.y = cadre.getHeight() / 2 - (tailleCase * plateau.hauteur() / 2);
	}

	private void dessinerPlateau(GraphicsContext gc,Vec2d offset) {
		for (int i = 0; i < plateau.hauteur(); i++) {
			for (int j = 0; j < plateau.largeur(); j++) {
				if (!plateau.estMangee(new Couple(i, j))) {
					gc.setStroke(Color.BLACK);
					Image img;
					if (i == 0 && j == 0)
						img = Donnees.IMG_GAUFRE_HG;
					else if (i == 0 && j == plateau.largeur() - 1)
						img = Donnees.IMG_GAUFRE_HD;
					else if (i == plateau.hauteur() - 1 && j == 0)
						img = Donnees.IMG_GAUFRE_BG;
					else if (i == plateau.hauteur() - 1 && j == plateau.largeur() - 1)
						img = Donnees.IMG_GAUFRE_BD;
					else
						img = Donnees.IMG_GAUFRE_M;

					gc.drawImage(img, offset.x + position.x + j * tailleCase, offset.y + position.y + i * tailleCase, tailleCase, tailleCase);
				} else {
					if (plateau.estMangeable(new Couple(i - 1, j - 1)) && plateau.estMangeable(new Couple(i - 1, j))
							&& plateau.estMangeable(new Couple(i, j - 1))) {
						gc.drawImage(Donnees.IMG_GAUFRE_CROQUE_COIN,offset.x + position.x + j * tailleCase,
								 offset.y + position.y + i * tailleCase, tailleCase, tailleCase);
					} else if (plateau.estMangeable(new Couple(i, j - 1))) {
						gc.drawImage(Donnees.IMG_GAUFRE_CROQUE_COTE_BAS,offset.x + position.x + j * tailleCase,
								 offset.y + position.y + i * tailleCase, tailleCase, tailleCase);
					} else if (plateau.estMangeable(new Couple(i - 1, j))) {
						gc.drawImage(Donnees.IMG_GAUFRE_CROQUE_COTE_DROIT,offset.x + position.x + j * tailleCase,
								 offset.y + position.y + i * tailleCase, tailleCase, tailleCase);
					} else if (plateau.estMangeable(new Couple(i - 1, j - 1))) {
						gc.drawImage(Donnees.IMG_GAUFRE_CROQUE_COIN_EXT,offset.x + position.x + j * tailleCase,
								 offset.y + position.y + i * tailleCase, tailleCase, tailleCase);
					}
				}
			}
		}
		float poisonEchelle = 0.5f;
		gc.drawImage(Donnees.IMG_POISON, offset.x + position.x + tailleCase*poisonEchelle/2,  offset.y + position.y + tailleCase*poisonEchelle/2, tailleCase*poisonEchelle, tailleCase*poisonEchelle);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		Shadow shadow = new Shadow();
		shadow.setHeight(1);
		shadow.setWidth(1);
		gc.setEffect(shadow);
		gc.setGlobalAlpha(0.5);
		dessinerPlateau(gc, new Vec2d(20,20));
		gc.setEffect(null);
		gc.setGlobalAlpha(1);
		dessinerPlateau(gc, new Vec2d(0,0));

	}

	public int tailleCase() {
		return tailleCase;
	}

	public Plateau plateau() {
		return plateau;
	}

}
