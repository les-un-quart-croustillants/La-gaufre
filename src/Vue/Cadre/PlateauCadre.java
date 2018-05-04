package Vue.Cadre;

import Controleur.ClicVuePlateau;
import Controleur.SourisBougerCibleur;
import Modele.Plateau;
import Vue.PanePrincipal;
import Vue.GameObject.AnimationGraphique;
import Vue.GameObject.CibleurGraphique;
import Vue.GameObject.OnDestroyHandler;
import Vue.GameObject.PlateauGraphique;
import Vue.GameObject.PoisonGaphique;

public class PlateauCadre extends Cadre {
	Plateau plateau;
	PoisonGaphique poison;
	PlateauGraphique plateauGraphique;
	CibleurGraphique cibleurGraphique;
		
	private void Initialisation(PanePrincipal pp) {		
		plateau = pp.plateau;
		
		plateauGraphique = new PlateauGraphique(plateau,this,(int)this.getWidth()/2,(int)this.getHeight()/2,20);
		this.gameObjects.add(plateauGraphique);
		
		poison = new PoisonGaphique(plateauGraphique);
		this.gameObjects.add(poison);
		
		cibleurGraphique = new CibleurGraphique(plateauGraphique,0,0);
		this.gameObjects.add(cibleurGraphique);

		AnimationGraphique an = new AnimationGraphique(5000);//ceci est un exemple
		an.setOnDestroyHandler(new OnDestroyHandler() {
			@Override
			public void handle() {
			}
		});
		this.gameObjects.add(an);
				
		this.setOnMousePressed(new ClicVuePlateau(pp));
		this.setOnMouseMoved(new SourisBougerCibleur(pp, cibleurGraphique));
	}
	
	public PlateauCadre(PanePrincipal pp){
		super();
		Initialisation(pp);
	}
	
	public PlateauCadre(int wpref,int hpref, PanePrincipal pp){
		super(wpref,hpref);
		Initialisation(pp);
	}
	
	public Plateau plateau() {
		return plateau;
	}
	public PlateauGraphique plateauGraphique() {
		return plateauGraphique;
	}
	public CibleurGraphique cibleurGraphique() {
		return cibleurGraphique;
	}
	public PoisonGaphique getPoison() {
		return poison;
	}

}
