package Controleur;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClicVuePlateau implements EventHandler<MouseEvent> {

	/*VuePlateau gv;
	Plateau p;

	public ClicVuePlateau(VuePlateau gv, Plateau p) {
		this.gv = gv;
		this.p = p;
	}*/

	@Override
	public void handle(MouseEvent event) {
		/*if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			float i, j;

			i = (float) ((event.getY() - gv.origine.y) / gv.tailleCase);
			j =  (float) ((event.getX() - gv.origine.x) / gv.tailleCase);
			if(i>=0 && i<p.getHeight() && j>=0 && j<p.getWidth()) {
				p.setCase((int)i, (int)j);
			}
		}*/

	}

}
