package Joueurs;

import Modele.Couple;
import Modele.Plateau;

/**
 * 
 * @author Louka Soret
 *
 */
public abstract class Joueur {
	Plateau plateau;
	int num;

	
	public enum Difficulte {
		FACILE,
		MOYEN,
		DIFFICILE;
		
		static public String toString (Difficulte d) {
			switch(d) {
				case FACILE:
					return "facile";
				case MOYEN:
					return "moyen";
				case DIFFICILE:
					return "difficile";
				default:
					return "unknown";
			}
		}
		
		static public Difficulte fromString (String s) {
			switch(s) {
				case "facile":
					return Difficulte.FACILE;
				case "moyen":
					return Difficulte.MOYEN;
				case "difficile":
					return Difficulte.DIFFICILE;
				default:
					return Difficulte.FACILE;
			}
		}
	}
	/**
	 * Constructeur pour la classe Joueur
	 * 
	 * @param p un plateau de jeu
	 */
	Joueur(Plateau p) {
	        plateau = p;
	    }

	/**
	 * Méthode appelée lorsque le tour d'un joueur vient
	 * 
	 * @return un temps à attendre en millisecondes ou 0 si aucune temporisation requise
	 */
	int delai() {
		return 0;
	}

	/**
	 * Méthode appelée une fois le temps écoulé
	 * 
	 * @return True si le traitement a reussi, False sinon
	 */
	boolean delaiEcoule() {
		return false;
	}

	/**
	 * Méthode appelée lors d'un clic sur le plateau
	 * 
	 * @param i absisse
	 * @param j ordonnée
	 * @return True si le coup a bien été joué, False si le coup n'était pas jouable
	 */
	boolean jouerCoup(Couple pos) {
		return false;
	}
}
