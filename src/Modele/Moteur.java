package Modele;

import Modele.Plateau;
import Vue.PlateauGraphique;

public class Moteur {
	public enum FSA_state {
		WAIT(0), PLAYER(1);
		
		public int value;
		
		FSA_state(int value) {
			this.value = value;
		}
	}
	
	PlateauGraphique pg;
	Plateau p;
	FSA_state etat_courant;
	int joueur_courant;
	
	// Joueur [] joueurs;
	
	public void jouer_un_coup(Couple c) {
		p.manger(c);
		joueur_courant = (joueur_courant + 1) % 2;
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
}