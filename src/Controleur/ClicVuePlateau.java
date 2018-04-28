package Controleur;

import Modele.Couple;
import Modele.Plateau;
import Vue.PlateauGraphique;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClicVuePlateau implements EventHandler<MouseEvent> {

	PlateauGraphique gv;
	Plateau p;

	public ClicVuePlateau(PlateauGraphique gv, Plateau p) {
		this.gv = gv;
		this.p = p;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			float i, j;
			i = (float) ((event.getY() - gv.origine.y) / gv.tailleCase);
			j =  (float) ((event.getX() - gv.origine.x) / gv.tailleCase);
			p.manger(new Couple((int)i,(int)j));
			//p.notifier();
			//System.out.println(i+" "+j);
		}

	}

}
