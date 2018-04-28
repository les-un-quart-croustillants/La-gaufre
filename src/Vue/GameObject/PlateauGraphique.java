package Vue.GameObject;

import Modele.Couple;
import Modele.Plateau;
import Vue.Cadre.Cadre;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateauGraphique extends GameObject {
	Plateau plateau;
	Cadre cadre;
	int tailleCase;
	public PlateauGraphique(Plateau plateau,Cadre cadre,int x, int y,int tailleCase) {
		super(x, y);
		this.plateau = plateau;
		this.cadre = cadre;
		this.tailleCase = tailleCase;
	}
	
	@Override
	public void update() {
		tailleCase = (int) (cadre.getHeight()*0.9/plateau.hauteur());
		position.x = cadre.getWidth()/2-(tailleCase*plateau.largeur()/2);
		position.y = cadre.getHeight()/2-(tailleCase*plateau.hauteur()/2);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		for(int i=0;i<plateau.hauteur();i++) {
			for(int j=0;j<plateau.hauteur();j++) {
				if(!plateau.estMangee(new Couple(i,j))) {
					gc.setStroke(Color.BLACK);
					gc.strokeRect(position.x+j*tailleCase, position.y+i*tailleCase, tailleCase, tailleCase);
				}
			}
		}
	}
	
	public int tailleCase() {
		return tailleCase;
	}
	
	public Plateau plateau() {
		return plateau;
	}

}
