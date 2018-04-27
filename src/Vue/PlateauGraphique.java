package Vue;

import Modele.Plateau;

public class PlateauGraphique extends Cadre {
	Plateau p;
	/*public int tailleCase = 20;
	public Point origine;*/
		
	private void Initialisation() {		
		/*tailleCase = (int) (this.getHeight()*0.9/plateau.getHeight());
		origine = new Point((int) (this.getWidth()/2-(tailleCase*plateau.getWidth()/2)),(int) (this.getHeight()/2-(tailleCase*plateau.getHeight()/2)));
		
		this.setOnMousePressed(new ClicVuePlateau(this, plateau));*/
	}
	
	PlateauGraphique(Plateau p){
		super();
		this.p = p;
		Initialisation();
	}
	PlateauGraphique(int wpref,int hpref, Plateau p){
		super(wpref,hpref);
		this.p = p;
		Initialisation();
	}
	
	public void maj() {
		
	}
	
	void Draw() {
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
		gc.fillText("Plus tard, il y aura une gaufre ici", 10, 20);
		/*origine = new Point((int) (this.getWidth()/2-(tailleCase*plateau.getWidth()/2)),(int) (this.getHeight()/2-(tailleCase*plateau.getHeight()/2)));
		tailleCase = (int) (this.getHeight()*0.9/plateau.getHeight());
		for(int i=0;i<plateau.getHeight();i++) {
			for(int j=0;j<plateau.getWidth();j++) {
				gc.strokeRect(origine.x+j*tailleCase, origine.y+i*tailleCase, tailleCase, tailleCase);
				if(plateau.getCase(i, j)) {
					gc.fillRect(origine.x+j*tailleCase, origine.y+i*tailleCase, tailleCase, tailleCase);
				}
			}
		}*/
	}

}
