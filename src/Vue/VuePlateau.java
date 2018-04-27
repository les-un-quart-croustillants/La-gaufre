package Vue;

import java.awt.Point;

import Controleur.ClicVuePlateau;
import Modele.Couple;
import Modele.Plateau;

public class VuePlateau extends Cadre implements Vue {	
	public int tailleCase = 20;
	public Point origine;
	Plateau plateau;
		
	private void Initialisation(Plateau p) {		
		plateau = p;
		plateau.ajouter_observateur(this);
		tailleCase = (int) (this.getHeight()*0.9/plateau.hauteur());
		origine = new Point((int) (this.getWidth()/2-(tailleCase*plateau.largeur()/2)),(int) (this.getWidth()/2-(tailleCase*plateau.hauteur()/2)));
		
		this.setOnMousePressed(new ClicVuePlateau(this, plateau));
	}
	
	VuePlateau(Plateau p){
		super();
		Initialisation(p);
	}
	VuePlateau(int wpref,int hpref, Plateau p){
		super(wpref,hpref);
		Initialisation(p);
	}
	
	public void maj() {
		Draw();
		System.out.println("maj");
	}
	
	void Draw() {
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
		gc.fillText("Plus tard, il y aura une gaufre ici", 10, 20);
		origine = new Point((int) (this.getWidth()/2-(tailleCase*plateau.largeur()/2)),(int) (this.getHeight()/2-(tailleCase*plateau.hauteur()/2)));
		tailleCase = (int) (this.getHeight()*0.9/plateau.hauteur());
		for(int i=0;i<plateau.hauteur();i++) {
			for(int j=0;j<plateau.hauteur();j++) {
				if(!plateau.estMangee(new Couple(i,j))) {
					gc.strokeRect(origine.x+j*tailleCase, origine.y+i*tailleCase, tailleCase, tailleCase);
				}
			}
		}
	}

}
