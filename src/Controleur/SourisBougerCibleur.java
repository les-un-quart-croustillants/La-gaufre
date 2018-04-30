package Controleur;

import Modele.Couple;
import Vue.Moteur.FSA_state;
import Vue.PanePrincipal;
import Vue.GameObject.CibleurGraphique;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SourisBougerCibleur implements EventHandler<MouseEvent>{

	CibleurGraphique cibleurGraphique;
	PanePrincipal pp;
	
	public SourisBougerCibleur(PanePrincipal pp,CibleurGraphique c) {
		this.cibleurGraphique = c;
		this.pp = pp;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			float i, j;
			i = (float) ((event.getY() - pp.gameView.plateauGraphique().position().y) / pp.gameView.plateauGraphique().tailleCase());
			j =  (float) ((event.getX() - pp.gameView.plateauGraphique().position().x) / pp.gameView.plateauGraphique().tailleCase());
			if(i>=0 && j>= 0 && pp.gameView.plateau().estMangeable(new Couple((int)i,(int)j)) && !pp.moteur.joueur_courant().estIA() && pp.moteur.etat_courant()==FSA_state.CHOISIR_COUP) {
				cibleurGraphique.setCaseCible(new Couple((int)i,(int)j));
			}
			else {
				cibleurGraphique.setCaseCible(new Couple(-1,-1));
			}
		}
	}

}
