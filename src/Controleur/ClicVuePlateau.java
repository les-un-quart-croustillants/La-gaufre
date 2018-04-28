package Controleur;

import Modele.Couple;
import Vue.Cadre.PlateauCadre;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClicVuePlateau implements EventHandler<MouseEvent> {

	PlateauCadre pc;

	public ClicVuePlateau(PlateauCadre pc) {
		this.pc = pc;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			float i, j;
			i = (float) ((event.getY() - pc.plateauGraphique().position().y) / pc.plateauGraphique().tailleCase());
			j =  (float) ((event.getX() - pc.plateauGraphique().position().x) / pc.plateauGraphique().tailleCase());
			pc.plateau().manger(new Couple((int)i,(int)j));
			//p.notifier();
			//System.out.println(i+" "+j);
		}

	}

}
