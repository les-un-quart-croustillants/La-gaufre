package Vue.GameObject;

import com.sun.javafx.geom.Vec2d;

import Modele.Couple;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CibleurGraphique extends GameObject {
	private PlateauGraphique plateauGraphique;
	private Couple caseCible;
	private boolean visible;
	private Vec2d centre;
	private int largeurCibleur=0;
	private int hauteurCibleur=0;
	private Color couleur = new Color(1,1,1,1);

	public CibleurGraphique(PlateauGraphique plateauGraphique, int x, int y) {
		super(x, y);
		this.plateauGraphique = plateauGraphique;
		caseCible = new Couple(-1,-1);
		centre = new Vec2d();
	}

	public void setCaseCible(Couple c) {
		caseCible.i = c.i;
		caseCible.j = c.j;
	}

	public void setCouleur(Color c) {
		couleur = c;
	}
	
	int largeurCibleur() {
		int j=0;
		for(j=caseCible.j;j<plateauGraphique.plateau().largeur() && plateauGraphique.plateau().estMangeable(new Couple(caseCible.i,j));j++);
		return j-caseCible.j;
	}
	int hauteurCibleur() {
		int i=0;
		for(i=caseCible.i;i<plateauGraphique.plateau().hauteur() && plateauGraphique.plateau().estMangeable(new Couple(i,caseCible.j));i++);
		return i-caseCible.i;
	}
	
	public void update() {
		visible = plateauGraphique.plateau.estMangeable(caseCible);
		centre.x = (caseCible.j + 0.5) * plateauGraphique.tailleCase() + plateauGraphique.position().x;
		centre.y = (caseCible.i + 0.5) * plateauGraphique.tailleCase() + plateauGraphique.position().y;
		largeurCibleur = largeurCibleur();
		hauteurCibleur = hauteurCibleur();
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (visible) {
			gc.setStroke(couleur);
			gc.setFill(couleur);
			gc.setLineWidth(plateauGraphique.tailleCase()*0.05);
			gc.strokeRoundRect(caseCible.j * plateauGraphique.tailleCase() + plateauGraphique.position().x,
					caseCible.i * plateauGraphique.tailleCase() + plateauGraphique.position().y,
					plateauGraphique.tailleCase(), plateauGraphique.tailleCase(),plateauGraphique.tailleCase()*0.2,plateauGraphique.tailleCase()*0.2);
			gc.setGlobalAlpha(0.5);
			gc.fillRect(caseCible.j * plateauGraphique.tailleCase() + plateauGraphique.position().x,
					caseCible.i * plateauGraphique.tailleCase() + plateauGraphique.position().y, plateauGraphique.tailleCase() * largeurCibleur,plateauGraphique.tailleCase() * hauteurCibleur);
			gc.setGlobalAlpha(1);
			gc.setLineWidth(1);

		}
	}

}
