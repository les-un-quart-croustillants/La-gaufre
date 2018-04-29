package Vue;

import Modele.Couple;
import Modele.Plateau;
import Vue.GameObject.AnimationGraphique;
import Vue.GameObject.OnDestroyHandler;

public class Moteur {
	public enum FSA_state {
		WAIT(0), PLAYER(1);
		
		public int value;
		
		FSA_state(int value) {
			this.value = value;
		}
	}
	
	public Plateau plateau;
	public PanePrincipal panePrincipal;
	private FSA_state etat_courant;
	private int joueur_courant;
	
	// Joueur [] joueurs;
	
	public Moteur(PanePrincipal pp) {
		this.panePrincipal= pp;
		etat_courant=FSA_state.PLAYER;
	}
	
	public boolean jouer_un_coup(Couple c) {
		if(panePrincipal.plateau.manger(c)) {
			joueur_courant = (joueur_courant + 1) % 2;
			next_state();
			long delay = 10; //attente en ms
			AnimationGraphique an = new AnimationGraphique(delay);//ceci est un exemple
			an.setOnDestroyHandler(new OnDestroyHandler() {
				@Override
				public void handle() {
					next_state();
					panePrincipal.enteteView.label.setText("Joueur "+(joueur_courant()+1));
				}
			});
			panePrincipal.gameView.gameObjects.add(an);
			return true;
		}
		return false;
	}
	
	public void update() {
		switch(etat_courant) {
		case WAIT:
			// On attend que l'interface graphique fasse son animation
			break;
		case PLAYER:
			/* Pas encore utilisable, on n'a pas pour l'instant de moyen de discriminer un joueur IA ou joueur humain
			// Ici on remplacera l'ancien Plateau contenu dans PlateauGraphique par le Plateau actuel (modifié par le précedent coup)
			if(joueurs[joueur_courant].est_joueur_humain()) {
				// On attend que l'interface graphique fasse son animation
				// L'intergace graphique s'occupe dans ce cas de faire évoluer l'était du FSA
			} else {
				// On fait joueur l'IA
				jouer_un_coup(joueurs.prochain_coup());
				next_state();
			}*/
			break;
		}
	}
	
	public void next_state() {
		switch(etat_courant) {
		case WAIT:
			etat_courant = FSA_state.PLAYER;
			break;
		case PLAYER:
			etat_courant = FSA_state.WAIT;
			
			break;
		}
	}
	
	public int joueur_courant() {
		return joueur_courant;
	}
	public FSA_state etat_courant() {
		return etat_courant;
	}

}