package Controleur;

import Modele.Couple;
import Vue.Moteur;
import Vue.PanePrincipal;
import Vue.Moteur.FSA_state;
import Vue.Cadre.PlateauCadre;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClicVuePlateau implements EventHandler<MouseEvent> {

	PanePrincipal pp;

	public ClicVuePlateau(PanePrincipal pp) {
		this.pp = pp;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			PlateauCadre pc = pp.gameView;
			Moteur moteur = pp.moteur;
			float i, j;
			i = (float) ((event.getY() - pc.plateauGraphique().position().y) / pc.plateauGraphique().tailleCase());
			j =  (float) ((event.getX() - pc.plateauGraphique().position().x) / pc.plateauGraphique().tailleCase());
			if(moteur.etat_courant()==FSA_state.PLAYER ) {
				moteur.jouer_un_coup(new Couple((int)i,(int)j));
			}
		}

	}

}
