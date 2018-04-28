package Vue.Cadre;

import Controleur.ClicVuePlateau;
import Controleur.SourisBougerCibleur;
import Modele.Plateau;
import Vue.GameObject.AnimationGraphique;
import Vue.GameObject.CibleurGraphique;
import Vue.GameObject.OnDestroyHandler;
import Vue.GameObject.PlateauGraphique;

public class PlateauCadre extends Cadre {
	Plateau plateau;
	PlateauGraphique plateauGraphique;
	CibleurGraphique cibleurGraphique;
		
	private void Initialisation(Plateau p) {		
		plateau = p;
		
		plateauGraphique = new PlateauGraphique(plateau,this,(int)this.getWidth()/2,(int)this.getHeight()/2,20);
		this.gameObjects.add(plateauGraphique);
		
		cibleurGraphique = new CibleurGraphique(plateauGraphique,0,0);
		this.gameObjects.add(cibleurGraphique);

		AnimationGraphique an = new AnimationGraphique(5000);//ceci est un exemple
		an.setOnDestroyHandler(new OnDestroyHandler() {
			@Override
			public void handle() {
				System.out.println("coucou");
			}
		});
		this.gameObjects.add(an);
		
		this.setOnMousePressed(new ClicVuePlateau(this));
		this.setOnMouseMoved(new SourisBougerCibleur(this, cibleurGraphique));
	}
	
	public PlateauCadre(Plateau p){
		super();
		Initialisation(p);
	}
	
	public PlateauCadre(int wpref,int hpref, Plateau p){
		super(wpref,hpref);
		Initialisation(p);
	}
	
	public Plateau plateau() {
		return plateau;
	}
	public PlateauGraphique plateauGraphique() {
		return plateauGraphique;
	}

}
