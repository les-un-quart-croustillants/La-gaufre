package Controleur;

import Modele.Couple;
import Vue.Cadre.PlateauCadre;
import Vue.GameObject.CibleurGraphique;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SourisBougerCibleur implements EventHandler<MouseEvent>{

	CibleurGraphique cibleurGraphique;
	PlateauCadre pc;
	
	public SourisBougerCibleur(PlateauCadre pc,CibleurGraphique c) {
		this.cibleurGraphique = c;
		this.pc = pc;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			float i, j;
			i = (float) ((event.getY() - pc.plateauGraphique().position().y) / pc.plateauGraphique().tailleCase());
			j =  (float) ((event.getX() - pc.plateauGraphique().position().x) / pc.plateauGraphique().tailleCase());
			if(i>=0 && j>= 0 && pc.plateau().estMangeable(new Couple((int)i,(int)j))) {
				cibleurGraphique.setCaseCible(new Couple((int)i,(int)j));
			}
			else {
				cibleurGraphique.setCaseCible(new Couple(-1,-1));
			}
		}
	}

}
